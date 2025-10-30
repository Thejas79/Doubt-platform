Doubt Platform (Student–Trainer Web App)

This is a simple Java-based web application that allows students to post doubts and trainers to view common doubts.
It uses HTML, CSS, Java (Spring Boot), and XML configuration to build and run a small local web app.

📁 Project Structure
doubt-platform/
│
├── backend/                # Java + Spring Boot backend
│   ├── src/main/java/...   # Java source code
│   ├── src/main/resources/ # application.properties, templates, etc.
│   ├── pom.xml             # Maven configuration file
│
├── frontend/               # HTML + CSS + JS files (web pages)
│   ├── index.html          # Login page (Student/Trainer)
│   ├── student.html        # Student dashboard to post doubts
│   ├── trainer.html        # Trainer dashboard to view common doubts
│   ├── style.css           # Page styling
│   └── script.js           # Frontend functionality
│
└── README.md

⚙️ Prerequisites (Install These First)

Before running the project, make sure you have the following installed:

Tool	Version	Download Link
Java JDK	21 or 24 (recommended)	Oracle JDK

Maven	3.9+	Maven Download

VS Code / IntelliJ IDEA	Latest	VS Code

Git	Latest	Git
🧰 Environment Setup
1. Clone the Repository

Open terminal (PowerShell or Git Bash) and run:

git clone https://github.com/<your-username>/doubt-platform.git
cd doubt-platform/backend

2. Verify Java Installation
java -version


You should see something like:

java version "24.0.1" or "21"

3. Verify Maven Installation
mvn -version

4. Check Folder Structure

Ensure your project contains a valid pom.xml inside the /backend folder.

🧩 Build and Run the Project

In the /backend folder:

mvn clean install
mvn spring-boot:run


If everything runs correctly, you’ll see something like:

Tomcat started on port 8080
Started Application in 4.123 seconds

🌐 Open the Web App

Now open your browser and visit:

http://localhost:8080/index.html


You should see the login page.
From here:

Student → can log in and post doubts

Trainer → can view and respond to common doubts

🧾 How It Works
1. Student Side

The student logs in using their ID.

Enters their question/doubt.

Submits it — it gets saved and visible to the trainer dashboard.

2. Trainer Side

The trainer logs in.

Views all the common or most-asked doubts.

Can mark them as answered or review them.

📦 Key Files
File	Description
index.html	Main login page for both users
student.html	Student dashboard for posting doubts
trainer.html	Trainer dashboard for viewing doubts
pom.xml	Maven dependencies and compiler setup
style.css	Webpage design and layout
script.js	Handles form submissions and interactivity
🧠 Common Issues
Problem	Solution
Port 8080 already in use	Stop other servers or change port in application.properties
Java version error	Update <maven.compiler.source> and <maven.compiler.target> in pom.xml to match your JDK
Cannot connect to localhost	Make sure spring-boot:run is still running in terminal
404 Not Found	Double-check the path of index.html
🧰 Optional Commands

If you want to stop the server:

Ctrl + C


To clean the project:

mvn clean


To rebuild everything:

mvn clean install

💡 Notes

No external database is required for this basic version.

✅ Final Setup Checklist (to avoid confusion)

Before running the project, make sure all these are ✅ checked:

Folder structure must be like this

doubt-platform/
├── backend/
│   ├── pom.xml
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       └── resources/
└── frontend/
    ├── index.html
    ├── student.html
    ├── trainer.html
    ├── style.css
    └── script.js


Use the correct Java version

Confirm Java is installed and matches what’s inside pom.xml:

<maven.compiler.source>24</maven.compiler.source>
<maven.compiler.target>24</maven.compiler.target>


If your JDK is version 21 or 17, replace 24 accordingly.

Ensure you’re in the correct folder when running Maven

cd doubt-platform/backend
mvn clean install
mvn spring-boot:run


You must be inside the folder containing pom.xml.

Frontend folder is in the same level as backend, not inside it.
Your HTML pages will be served directly by the backend (Spring Boot).

If you’re using VS Code:

Install the following extensions:

“Extension Pack for Java”

“Spring Boot Dashboard”

“Maven for Java”

Then open the whole doubt-platform folder in VS Code.

You can also press Ctrl + F5 to run the backend directly from VS Code.

Browser access

Once backend starts successfully, open:

http://localhost:8080/index.html


If you see “Whitelabel Error Page”, your HTML files might not be in the correct directory.
Move them to:

backend/src/main/resources/static/


Then rerun the server.

No database needed
All data is stored temporarily in memory (for demo purposes).
When you restart the app, old doubts are cleared.

Stopping the server

Press Ctrl + C in the terminal.

To start again:

mvn spring-boot:run

🚀 Optional Enhancements (for later)

If you want to expand the project in the future:

Feature	How to Add
Database	Add MySQL + JPA dependencies in pom.xml, and configure application.properties
Login authentication	Use Spring Security
Persistent storage	Replace in-memory data with a database table
UI improvements	Use Bootstrap CSS or React frontend
🧩 Example Commands Summary
Task	Command
Clone project	git clone https://github.com/<your-username>/doubt-platform.git
Move to backend	cd doubt-platform/backend
Build project	mvn clean install
Run backend	mvn spring-boot:run
Open web page	http://localhost:8080/index.html
Stop server	Ctrl + C
Clean project	mvn clean

All logic is handled in the Java backend using simple request mapping.

You can extend this app later with MySQL or Spring Data JPA if you want to persist real data.
