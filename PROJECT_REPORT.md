# Hospital Management System - Project Report

**Student Details:**

- **Name:**       Mamman Cornelius Ohiani
- **Matric No:**  2022010527
- **Department:** Computer Science
- **Faculty:**    Computing and Informatics

---

## 1. Executive Summary

The Hospital Management System is a comprehensive Java-based application designed to manage hospital operations in the South-Western region of Nigeria specifically Ogbomosho. The system incorporates local cultural context (Yoruba names and locations in the generated dataset) while providing robust functionality for hospital administration, patient management, and user access control.

## 2. System Features

### 2.1 Core Features

- **Hospital Data Management:** Complete CRUD operations for hospital records
- **Patient Records Management:** Comprehensive patient information system
- **User Authentication & Authorization:** Role-based access control system
- **File-based Persistence:** JSON data storage with automatic backup
- **Advanced Search & Filtering:** Multiple search criteria and sorting options
- **Comprehensive Reporting:** Statistical analysis and system reports

### 2.2 Advanced Features

- **Role-based Access Control:** Four user roles (Admin, Doctor, Nurse, Receptionist)
- **Data Validation & Error Handling:** Robust input validation throughout
- **Backup & Recovery System:** Automated data backup with timestamp
- **Local Context Integration:** South-West Nigeria Ogbomosho specifically
- **Multi-level Menu System:** Intuitive navigation structure
- **Real-time Data Persistence:** Automatic saving of all changes to the JSON files

## 3. --- System Architecture ---

### 3.1 File Structure

HospitalManagementSystem/
**├── Hospital.java**                 # Hospital entity model
**├── Patient.java**                  # Patient entity model
**├── User.java**                     # User entity model
**├── DataManager.java**              # JSON persistence manager
**├── DataGenerator.java**            # Local data generator
**├── HospitalService.java**          # Business logic layer
**├── HospitalManagementSystem.java** # Main application
**├── data/**                         # Data directory
**│   ├── hospitals.json**            # Hospital data storage
**│   ├── patients.json**             # Patient data storage
**│   ├── users.json**                # User data storage
**│   └── backup/**                   # Backup files directory
**├── lib/**                          # External libraries
**│   └── gson-2.10.1.jar**           # JSON processing library
**└── README.md**                     # Project documentation

Setup instructions

### 3.2 Design Patterns Used

- **Model-View-Controller (MVC):** Clear separation of concerns
- **Data Access Object (DAO):** DataManager handles all file operations
- **Service Layer Pattern:** HospitalService contains business logic
- **Factory Pattern:** DataGenerator creates sample data
- **Singleton Pattern:** Single instance of data management

## 4. Data Model

### 4.1 Hospital Entity

- Hospital ID, Name, City, Address, Phone
- Room pricing, capacity, and availability
- Specialization and rating system
- Ogbomosho-focused location data

### 4.2 Patient Entity

- Personal information (with Yoruba naming conventions)
- Medical details (diagnosis, blood group, tribe)
- Hospital assignment and room allocation
- Financial information (billing system)
- Status tracking (admitted, discharged, outpatient)

### 4.3 User Entity

- Authentication credentials
- Role-based permissions
- Contact information and department
- Activity tracking (last login, creation date)

## 5. Technical Implementation

### 5.1 Technologies Used

- **Language:** Java 8+
- **Data Storage:** JSON files with GSON library
- **Architecture:** Layered architecture with separation of concerns
- **UI:** Console-based terminal interface
- **Data Processing:** Stream API for filtering and sorting

### 5.2 Key Technical Features

- **JSON Serialization:** Custom adapters for LocalDate and LocalDateTime
- **Error Handling:** Comprehensive try-catch blocks with user-friendly messages
- **Input Validation:** Multiple validation layers for data integrity
- **Memory Management:** Efficient data loading and caching
- **File I/O Operations:** Robust file handling with backup mechanisms

### 5.3 Security Features

- **Password Protection:** Basic password authentication
- **Role-based Access:** Permission checks for sensitive operations
- **Data Validation:** Input sanitization and format checking
- **Session Management:** Secure login/logout functionality

## 6. Local Context Integration

### 6.1 Ogbomosho Regional Focus

- **Local Hospitals:** Including Bowen University Teaching Hospital, LAUTECH Teaching Hospital
- **Geographic Areas:** 22+ local areas within Ogbomosho
- **Cultural Names:** 50+ authentic Yoruba first names and surnames
- **Local Demographics:** Yoruba tribe designation as primary ethnicity

### 6.2 Nigerian Context

- **Currency:** Naira (₦) pricing for all financial data
- **Phone Numbers:** Nigerian mobile number format (0XXXXXXXXXX)
- **Healthcare System:** Aligned with Nigerian hospital structure

## 7. Data Generation

### 7.1 Sample Data Size(added to JSON files after first run)

- **Hospitals:** 25 hospital records
- **Patients:** 75 patient records  
- **Users:** 16 user accounts (1 admin + 15 staff)

## 8. System Capabilities

### 8.1 Hospital Management

- View all hospitals with detailed information
- Sort by name, room price, or rating
- Filter by specific city
- Search by hospital name or ID
- Add new hospital records (admin/authorized users)

### 8.2 Patient Management  

- Comprehensive patient record viewing
- Sort by name, age, or admission date
- Filter by status (admitted, discharged, outpatient)
- Filter by assigned hospital
- Add and update patient information
- Track medical history and billing

### 8.3 User Administration

- Complete user account management (admin only)
- Role assignment and permission control
- User activity monitoring
- Password management system
- Account activation/deactivation

### 8.4 Reporting System

- Hospital statistics (capacity, occupancy rates, pricing analysis)
- Patient demographics and status reports
- System overview with technical details
- Real-time data analysis and insights

## 9. User Roles & Permissions

| Feature         | Admin  | Doctor | Nurse  | Receptionist |
|-----------------|--------|--------|--------|--------------|
| View Hospitals  |    ✓   |   ✓    |    ✓   |      ✓       |
| Add Hospitals   |    ✓   |   ✗    |    ✗   |      ✗       |
| View Patients   |    ✓   |   ✓    |    ✓   |      ✓       |
| Add Patients    |    ✓   |   ✗    |    ✗   |      ✓       |
| Edit Patients   |    ✓   |   ✓    |    ✗   |      ✗       |
| User Management |    ✓   |   ✗    |    ✗   |      ✗       |
| Data Backup     |    ✓   |   ✗    |    ✗   |      ✗       |
| System Reports  |    ✓   |   ✓    |    ✓   |      ✓       |

## 10. Installation & Setup

### 10.1 Prerequisites

- Java Development Kit (JDK) 8 or higher
- GSON library (gson-2.10.1.jar)
- Terminal/Command prompt access

### 10.2 Setup Instructions

1. Create project directory: `mkdir HospitalManagementSystem`
2. Copy all Java files to the project directory
3. Create `lib` directory and add `gson-2.10.1.jar`
4. Create `data` directory for JSON storage
5. Compile with: `javac -cp "lib/gson-2.10.1.jar" *.java`
6. Run with: `java -cp ".:lib/gson-2.10.1.jar" HospitalManagementSystem`

### 10.3 Default Login Credentials

- **Username:** admin
- **Password:** admin123
- **Role:** ADMIN (full system access)

## 11. System Advantages

### 11.1 Robustness

- **Error Handling:** Comprehensive exception management
- **Data Integrity:** Multiple validation layers
- **Backup System:** Automatic data backup with timestamps
- **Recovery:** Easy data restoration from backup files

### 11.2 Competitive Features

- **Local Context:** Culturally relevant data and naming
- **Role-based Security:** Granular permission system
- **Scalable Architecture:** Easy to extend and modify
- **User-friendly Interface:** Intuitive menu-driven system
- **Real-time Persistence:** Immediate data saving

### 11.3 Performance

- **Efficient Data Structures:** Java Collections for optimal performance
- **Memory Optimization:** Efficient data loading and caching
- **Fast Search:** Stream API for quick filtering and sorting
- **Minimal Dependencies:** Only GSON library required

## 12. Conclusion

The Hospital Management System successfully demonstrates comprehensive software engineering principles while incorporating local Nigerian context. The system provides robust functionality for hospital administration with a focus on Ogbomosho and its environs, making it practically relevant and culturally appropriate for the local healthcare context.

- ### 12.1 Technical Implementation

- Clean code architecture with proper separation of concerns
- Demonstrates multiple programming concepts and design patterns
