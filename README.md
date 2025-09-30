# Hospital Management System - Setup Instructions

Quick Start Guide
Prerequisites

Java Development Kit (JDK) 8 or higher
GSON library for JSON processing

Step 1: Download Required Files
Option 1: Direct Download (Recommended)

Open your browser and go to: <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/>
Click on gson-2.10.1.jar to download it
Save it to your Downloads folder

Option 2: Using wget (Linux/Mac)
bashwget <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar>
Option 3: Using curl (Linux/Mac)
bashcurl -O <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar>
Step 2: Project Setup

Create project directory:

bash   mkdir HospitalManagementSystem
   cd HospitalManagementSystem

Create required directories:

bash   mkdir lib
   mkdir data
   mkdir data/backup

Move the downloaded GSON jar file to the lib directory:
If you downloaded to Downloads folder:

bash   # Linux/Mac
   mv ~/Downloads/gson-2.10.1.jar lib/

## Windows (in Command Prompt)

   move %USERPROFILE%\Downloads\gson-2.10.1.jar lib\
If you downloaded using wget/curl in current directory:
bash   mv gson-2.10.1.jar lib/

Save all Java files in the main project directory:

Copy all the Java code from the artifacts above
Create files: Hospital.java, Patient.java, User.java, DataManager.java, DataGenerator.java, HospitalService.java, HospitalManagementSystem.java

Verify your setup:

bash   ls lib/

### Should show: gson-2.10.1.jar

   ls *.java

#### Should show all 7 Java files

Step 3: Compilation
bashjavac -cp "lib/gson-2.10.1.jar" *.java

For Windows:
cmdjavac -cp "lib\gson-2.10.1.jar"*.java

Step 4: Running the Application
bashjava -cp ".:lib/gson-2.10.1.jar" HospitalManagementSystem

For Windows:
cmdjava -cp ".;lib\gson-2.10.1.jar" HospitalManagementSystem

Default Login Credentials:
Username: admin
Password: admin123

Directory Structure After Setup
HospitalManagementSystem/
├── Hospital.java
├── Patient.java
├── User.java
├── DataManager.java
├── DataGenerator.java
├── HospitalService.java
├── HospitalManagementSystem.java
├── Hospital.class
├── Patient.class
├── User.class
├── DataManager.class
├── DataGenerator.class
├── HospitalService.class
├── HospitalManagementSystem.class
├── lib/
│   └── gson-2.10.1.jar
├── data/
│   ├── hospitals.json (generated on first run)
│   ├── patients.json (generated on first run)
│   ├── users.json (generated on first run)
│   └── backup/ (backup files stored here)
└── README.md
Troubleshooting
GSON Library Issues:

Cannot download GSON jar

Alternative method: Search "gson jar download" in Google
Maven Repository: Go to <https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1>
Manual download: Right-click the jar link and "Save As"

File not found after download

Check your Downloads folder: ls ~/Downloads/gson*(Linux/Mac) or dir %USERPROFILE%\Downloads\gson* (Windows)
Make sure the file name is exactly: gson-2.10.1.jar
If file name is different, rename it: mv downloaded-file.jar gson-2.10.1.jar

Permission denied when moving file

Linux/Mac: sudo mv ~/Downloads/gson-2.10.1.jar lib/
Windows: Run Command Prompt as Administrator

Common Compilation Issues:

"ClassNotFoundException: com.google.gson"

Verify GSON jar exists: ls lib/gson-2.10.1.jar
Check classpath syntax (: for Linux/Mac, ; for Windows)
Make sure jar file isn't corrupted (try re-downloading)

"Cannot find or load main class"

Ensure all Java files are compiled: ls *.class should show 7 class files
Check that you're running from the correct directory
Verify main class name: HospitalManagementSystem (no .java extension)

Compilation errors

Make sure you're using JDK 8 or higher: java -version
Verify all Java files are present: ls *.java should show 7 files
Check for typos in file names (case-sensitive)

File Permission Issues:

Permission errors on data files

Ensure write permissions: chmod 755 . (Linux/Mac)
Check that data directory exists: ls -la data/
Try running from a directory you have full access to

JSON parsing errors

Delete corrupted files: rm data/*.json
System will regenerate sample data on next run
Check disk space: df -h . (Linux/Mac)

System Requirements:

RAM: Minimum 512MB available memory
Storage: At least 50MB free space
Java Version: JDK 8 or higher
Operating System: Windows, Linux, or macOS

Features Overview
User Roles:

ADMIN: Full system access, user management, data backup
DOCTOR: View/edit patients, view hospitals
NURSE: View patients and hospitals
RECEPTIONIST: View/add patients, view hospitals

Data Management:

Automatic JSON file creation and management
Real-time data persistence
Backup functionality with timestamps
Data validation and error handling

Search & Filter Options:

Sort hospitals by name, price, rating
Filter hospitals by city
Sort patients by name, age
Filter patients by status or hospital
Search by name or ID

Sample Data
On first run, the system generates:

25 hospitals (focused on Ogbomosho region)
75 patients (with Yoruba names and local context)
16 users (various roles and departments)

Security Features:

Password-protected user accounts
Role-based access control
Input validation and sanitization
Secure session management

Support
For technical issues or questions about this project, please refer to the complete PROJECT_REPORT.md documentation.

Developed by: Mamman Cornelius Ohiani
Matric No: 2022010527
Department: Computer Science
Faculty: Computing and Informatics
