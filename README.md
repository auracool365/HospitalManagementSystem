# Hospital Management System - Setup Instructions

Quick Start Guide
Prerequisites

Java Development Kit (JDK) 8 or higher installed
GSON library for JSON processing

## Step 1: Download Required Files

- Option 1: Direct Download (Recommended)
go to: <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/>
Click on gson-2.10.1.jar to download it
Save it to your Downloads folder

- Option 2: Using wget (Linux/Mac)
bashwget <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar>

- Option 3: Using curl (Linux/Mac)
bashcurl -O <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar>

### Step 2: Compilation

bashjavac -cp "lib/gson-2.10.1.jar" *.java

For Windows:
cmdjavac -cp "lib\gson-2.10.1.jar"*.java

#### Step 4: Running the Application

bashjava -cp ".:lib/gson-2.10.1.jar" HospitalManagementSystem

For Windows:
cmdjava -cp ".;lib\gson-2.10.1.jar" HospitalManagementSystem

**Should show all the Java files below**
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
