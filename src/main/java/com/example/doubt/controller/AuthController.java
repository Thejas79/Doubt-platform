package com.example.doubt.controller;

import com.example.doubt.model.SessionEntity;
import com.example.doubt.repo.SessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SessionRepository sessionRepository;

    public AuthController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/student")
    public ResponseEntity<?> studentLogin(@RequestBody StudentLogin req) {
        // create a session record with anon tag
        String id = UUID.randomUUID().toString();
        String anonTag = makeAnonTag();
        SessionEntity s = new SessionEntity(id, anonTag);
        sessionRepository.save(s);
        return ResponseEntity.ok(new StudentLoginResp(id, anonTag));
    }

    @PostMapping("/trainer")
    public ResponseEntity<?> trainerLogin(@RequestBody TrainerLogin req) {
        // simple fixed credential
        if ("trainer".equals(req.username) && "pass123".equals(req.password)) {
            return ResponseEntity.ok(new TrainerResp("trainer-token-abc"));
        }
        return ResponseEntity.status(401).body("invalid");
    }

    // DTOs
    public static record StudentLogin(String name) {}
    public static record StudentLoginResp(String sessionId, String anonTag) {}
    public static record TrainerLogin(String username, String password) {}
    public static record TrainerResp(String token) {}

    private String makeAnonTag() {
        int n = 100 + (int)(Math.random()*900);
        return "S" + n;
    }
}
