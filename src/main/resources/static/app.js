const API = '';

let sessionId = null;
let anonTag = null;
let trainerToken = null;

document.getElementById('student-login').addEventListener('click', async ()=>{
  const name = document.getElementById('student-name').value.trim();
  const res = await fetch('/api/auth/student', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({name})});
  const j = await res.json();
  sessionId = j.sessionId; anonTag = j.anonTag;
  document.getElementById('auth').classList.add('hidden');
  document.getElementById('student-view').classList.remove('hidden');
  document.getElementById('student-messages').textContent = 'You are ' + anonTag;
});

document.getElementById('trainer-login').addEventListener('click', async ()=>{
  const user = document.getElementById('trainer-user').value;
  const pass = document.getElementById('trainer-pass').value;
  const res = await fetch('/api/auth/trainer', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({username:user,password:pass})});
  if (res.status === 200){
    const j = await res.json();
    trainerToken = j.token;
    document.getElementById('auth').classList.add('hidden');
    document.getElementById('trainer-view').classList.remove('hidden');
    loadGroups();
    loadRaw();
  } else {
    alert('invalid trainer credentials');
  }
});

document.getElementById('post-doubt').addEventListener('click', async ()=>{
  const text = document.getElementById('doubt-text').value.trim();
  if (!text) return alert('type a doubt');
  if (!sessionId) return alert('login as student first');
  const res = await fetch('/api/posts', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({sessionId, content: text})});
  if (res.ok){
    document.getElementById('doubt-text').value = '';
    document.getElementById('student-messages').textContent = 'Posted (anonymous)';
    setTimeout(()=>document.getElementById('student-messages').textContent = '',1200);
  } else {
    alert('post failed');
  }
});

document.getElementById('refresh-groups').addEventListener('click', loadGroups);
document.getElementById('logout-student').addEventListener('click', ()=>{
  sessionId = null; anonTag = null;
  document.getElementById('student-view').classList.add('hidden');
  document.getElementById('auth').classList.remove('hidden');
});
document.getElementById('logout-trainer').addEventListener('click', ()=>{
  trainerToken = null;
  document.getElementById('trainer-view').classList.add('hidden');
  document.getElementById('auth').classList.remove('hidden');
});

async function loadGroups(){
  const res = await fetch('/api/groups');
  const arr = await res.json();
  const el = document.getElementById('groups'); el.innerHTML = '';
  if (!arr || arr.length===0){ el.textContent = 'No groups yet'; return; }
  for (const g of arr){
    const d = document.createElement('div'); d.className='group';
    d.innerHTML = `<div style="font-weight:700">${escapeHtml(g.title)}</div><div style="color:#9aa">${(g.keywords||[]).join(', ')}</div><div style="color:#889">${g.count} posts</div><div><button data-id="${g.id}">Open examples</button></div>`;
    el.appendChild(d);
    d.querySelector('button').addEventListener('click', ()=>openExamples(g.id));
  }
}

async function openExamples(groupId){
  const res = await fetch('/api/groups/' + groupId + '/messages');
  const arr = await res.json();
  const txt = arr.map(m => `Anon ${m.anonTag} · ${new Date(m.createdAt).toLocaleString()}\n${m.content}`).join('\n\n---\n\n');
  alert(txt || 'No examples');
}

async function loadRaw(){
  const res = await fetch('/api/posts');
  const arr = await res.json();
  const el = document.getElementById('raw'); el.innerHTML = '';
  for (const p of arr){
    const d = document.createElement('div');
    d.style.padding = '8px'; d.style.marginBottom='6px'; d.style.background='#071426'; d.style.borderRadius='6px';
    d.innerHTML = `<div style="color:#8ba">Anon ${p.anonTag} · ${new Date(p.createdAt).toLocaleString()}</div><div>${escapeHtml(p.content)}</div>`;
    el.appendChild(d);
  }
}

function escapeHtml(s){ return String(s||'').replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c])); }
