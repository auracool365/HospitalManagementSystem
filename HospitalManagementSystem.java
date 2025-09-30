import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Hospital Management System
 * 
 * Student: Mamman Cornelius Ohiani
 * Matric No: 2022010527
 * Department: Computer Science
 * Faculty: Computing and Informatics
 * 
 * *** Welcome to OASIS, a comprehensive hospital management system with: 
 * User authentication
 * File-based persistence 
 * Role-based access
 * Data backup and recovery
 * Reporting and Statistics
 * Search functionality
 * Hospital and patient data management
 */
public class HospitalManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HospitalService hospitalService = new HospitalService();

    public static void main(String[] args) {

        // Main application loop
        try (scanner) {
            System.out.println("\n═══════════════════════════════════════════════════════");
            System.out.println("    OASIS HOSPITAL MANAGEMENT SYSTEM (Version 1.0.0)");
            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("    Student: Mamman Cornelius Ohiani");
            System.err.println("    Institution: Ladoke Akintola University of Technology(LAUTECH)");
            System.out.println("    Matric No: 2022010527");
            System.out.println("    Department: Computer Science");
            System.out.println("    Faculty: Computing and Informatics");
            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println();
            
            // Authentication loop
            while (!hospitalService.isLoggedIn()) {
                showLoginMenu();
            }
            
            // Main application loop
            while (hospitalService.isLoggedIn()) {
                showMainMenu();
            }
            
            System.out.println("\nThank you for choosing OASIS Hospital Management System!");
        }
    }

    // Show login/create account menu
    private static void showLoginMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│     OASIS HOSPITAL MANAGEMENT SYSTEM   │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("1. Login");
        System.out.println("2. Create New Account");
        System.out.println("3. Exit");
        System.out.print("\nSelect option (1-3): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> performLogin();
                case 2 -> createNewAccount();
                case 3 -> {
                    System.out.println("\nThank you for using OASIS Hospital Management System!");
                    System.out.println("Mamman Cornelius Ohiani");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid option! Please select 1-3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // Login existing user
    private static void performLogin() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│              LOGIN                 │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("Default Admin: username=admin, password=admin123");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (hospitalService.login(username, password)) {
            User currentUser = hospitalService.getCurrentUser();
            System.out.println("\n✅ Login successful!");
            System.out.println("Welcome, " + currentUser.getFullName() + " (" + currentUser.getRole() + ")");
        } else {
            System.out.println("\n❌ Invalid credentials! Please try again.");
        }
    }

    // Create new user account
    private static void createNewAccount() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        CREATE NEW ACCOUNT          │");
        System.out.println("└─────────────────────────────────────┘");
        
        try {
            System.out.print("First Name: ");
            String firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("❌ First name cannot be empty!");
                return;
            }

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("❌ Last name cannot be empty!");
                return;
            }

            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("❌ Username cannot be empty!");
                return;
            }

            // Check if username already exists
            if (hospitalService.usernameExists(username)) {
                System.out.println("❌ Username already exists! Please choose a different username.");
                return;
            }

            System.out.print("Password (minimum 6 characters): ");
            String password = scanner.nextLine();
            if (password.length() < 6) {
                System.out.println("❌ Password must be at least 6 characters long!");
                return;
            }

            System.out.print("Confirm Password: ");
            String confirmPassword = scanner.nextLine();
            if (!password.equals(confirmPassword)) {
                System.out.println("❌ Passwords do not match!");
                return;
            }

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty() || !email.contains("@")) {
                System.out.println("❌ Please enter a valid email address!");
                return;
            }

            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.println("❌ Phone number cannot be empty!");
                return;
            }

            System.out.println("\nSelect Role:");
            System.out.println("1. Doctor");
            System.out.println("2. Nurse");
            System.out.println("3. Receptionist");
             System.out.println("\nNote: Admin role cannot be selected during self-registration");
            System.out.println("      for security reasons. Contact an existing administrator");
            System.out.println("      to upgrade your account to Admin privileges.");
            System.out.print("\nSelect option (1-3): ");
            int roleChoice = Integer.parseInt(scanner.nextLine());
            
            String role;
            switch (roleChoice) {
                case 1 -> role = "DOCTOR";
                case 2 -> role = "NURSE";
                case 3 -> role = "RECEPTIONIST";
                default -> {
                    System.out.println("❌ Invalid role selection! Defaulting to RECEPTIONIST.");
                    role = "RECEPTIONIST";
                }
            }

            System.out.print("Department (e.g., General Medicine, Pediatrics): ");
            String department = scanner.nextLine().trim();
            if (department.isEmpty()) {
                department = "General";
            }

            // Generate new user ID
            String userId = "USR" + String.format("%03d", hospitalService.getNextUserId());

            // Create new user
            User newUser = new User(userId, username, password, role, firstName, lastName,
                                   email, phoneNumber, department, true);

            if (hospitalService.registerNewUser(newUser)) {
                System.out.println("\n✅ Account created successfully!");
                System.out.println("User ID: " + userId);
                System.out.println("Username: " + username);
                System.out.println("Role: " + role);
                System.out.println("\nYou can now login with your credentials.");
            } else {
                System.out.println("❌ Failed to create account. Please try again.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input! Please enter valid data.");
        } catch (Exception e) {
            System.out.println("❌ Error creating account: " + e.getMessage());
        }
    }

    // System main menu
    private static void showMainMenu() {
        User currentUser = hospitalService.getCurrentUser();
        
        System.out.println("\n┌───────────────────────────────────────────────────────┐");
        System.out.println("│                    MAIN MENU                         │");
        System.out.println("│  Logged in as: " + String.format("%-35s", currentUser.getFullName()) + "│");
        System.out.println("│  Role: " + String.format("%-43s", currentUser.getRole()) + "│");
        System.out.println("└───────────────────────────────────────────────────────┘");
        
        System.out.println("1.  Hospital Management");
        System.out.println("2.  Patient Management");
        System.out.println("3.  User Management (Admin Only)");
        System.out.println("4.  Reports & Statistics");
        System.out.println("5.  Search Functions");
        System.out.println("6.  Data Backup (Admin Only)");
        System.out.println("7.  Change Password");
        System.out.println("8.  Logout");
        System.out.println("9.  Exit System");
        System.out.print("\nSelect option (1-9): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> showHospitalMenu();
                case 2 -> showPatientMenu();
                case 3 -> showUserMenu();
                case 4 -> showReportsMenu();
                case 5 -> showSearchMenu();
                case 6 -> backupData();
                case 7 -> changePassword();
                case 8 -> logout();
                case 9 -> exitSystem();
                default -> System.out.println("❌ Invalid option! Please select 1-9.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // Hospital Management screen
    private static void showHospitalMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        HOSPITAL MANAGEMENT         │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("1. View All Hospitals");
        System.out.println("2. Sort Hospitals by Name");
        System.out.println("3. Sort Hospitals by Room Price");
        System.out.println("4. Sort Hospitals by Rating");
        System.out.println("5. Filter Hospitals by City");
        System.out.println("6. Add New Hospital");
        System.out.println("7. Back to Main Menu");
        System.out.print("\nSelect option (1-7): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> displayHospitals(hospitalService.getAllHospitals());
                case 2 -> displayHospitals(hospitalService.sortHospitalsByName());
                case 3 -> displayHospitals(hospitalService.sortHospitalsByRoomPrice());
                case 4 -> displayHospitals(hospitalService.sortHospitalsByRating());
                case 5 -> filterHospitalsByCity();
                case 6 -> addNewHospital();
                case 7 -> {
                }

                default -> System.out.println("❌ Invalid option! Please select 1-7.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // Patient management screen
    private static void showPatientMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        PATIENT MANAGEMENT          │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("1. View All Patients");
        System.out.println("2. Sort Patients by Name");
        System.out.println("3. Sort Patients by Age");
        System.out.println("4. Filter Patients by Status");
        System.out.println("5. Filter Patients by Hospital");
        System.out.println("6. Add New Patient");
        System.out.println("7. Update Patient Info");
        System.out.println("8. Back to Main Menu");
        System.out.print("\nSelect option (1-8): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> displayPatients(hospitalService.getAllPatients());
                case 2 -> displayPatients(hospitalService.sortPatientsByName());
                case 3 -> displayPatients(hospitalService.sortPatientsByAge());
                case 4 -> filterPatientsByStatus();
                case 5 -> filterPatientsByHospital();
                case 6 -> addNewPatient();
                case 7 -> updatePatient();
                case 8 -> {
                   
                }
                default -> System.out.println("❌ Invalid option! Please select 1-8.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // User management menu
    private static void showUserMenu() {
        if (!hospitalService.getCurrentUser().getRole().equals("ADMIN")) {
            System.out.println("❌ Access denied! Admin privileges required.");
            return;
        }

        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         USER MANAGEMENT             │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("1. View All Users");
        System.out.println("2. Add New User");
        System.out.println("3. Update User");
        System.out.println("4. Back to Main Menu");
        System.out.print("\nSelect option (1-4): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> displayUsers(hospitalService.getAllUsers());
                case 2 -> addNewUser();
                case 3 -> updateUser();
                case 4 -> {
                }
                default -> System.out.println("❌ Invalid option! Please select 1-4.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // Show hospital reports and statistics
    private static void showReportsMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       REPORTS & STATISTICS         │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("1. Hospital Statistics Report");
        System.out.println("2. Patient Statistics Report");
        System.out.println("3. System Overview");
        System.out.println("4. Back to Main Menu");
        System.out.print("\nSelect option (1-4): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> hospitalService.generateHospitalReport();
                case 2 -> hospitalService.generatePatientReport();
                case 3 -> showSystemOverview();
                case 4 -> {
                }
                default -> System.out.println("❌ Invalid option! Please select 1-4.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // Show search operations menu
    private static void showSearchMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        SEARCH FUNCTIONS            │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("1. Search Hospitals by Name");
        System.out.println("2. Search Patients by Name");
        System.out.println("3. Find Hospital by ID");
        System.out.println("4. Find Patient by ID");
        System.out.println("5. Back to Main Menu");
        System.out.print("\nSelect option (1-5): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> searchHospitalsByName();
                case 2 -> searchPatientsByName();
                case 3 -> findHospitalById();
                case 4 -> findPatientById();
                case 5 -> {
                   
                }
                default -> System.out.println("❌ Invalid option! Please select 1-5.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number!");
        }
    }

    // Display methods
    // Display hospitals
    private static void displayHospitals(List<Hospital> hospitals) {
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals found.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    HOSPITALS (" + hospitals.size() + " found)");
        System.out.println("=".repeat(70));
        
        for (Hospital hospital : hospitals) {
            System.out.println(hospital);
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Display patients
    private static void displayPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    PATIENTS (" + patients.size() + " found)");
        System.out.println("=".repeat(70));
        
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Display users
    private static void displayUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    USERS (" + users.size() + " found)");
        System.out.println("=".repeat(70));
        
        for (User user : users) {
            System.out.println(user);
        }
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Filter methods
    // Filter hospitals by city
    private static void filterHospitalsByCity() {
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();
        List<Hospital> filteredHospitals = hospitalService.getHospitalsByCity(city);
        displayHospitals(filteredHospitals);
    }

    // Filter hospitals by status
    private static void filterPatientsByStatus() {
        System.out.println("Available statuses: ADMITTED, DISCHARGED, OUT_PATIENT");
        System.out.print("Enter patient status: ");
        String status = scanner.nextLine();
        List<Patient> filteredPatients = hospitalService.getPatientsByStatus(status);
        displayPatients(filteredPatients);
    }

    // filter patients by Hospital
    private static void filterPatientsByHospital() {
        System.out.print("Enter hospital ID: ");
        String hospitalId = scanner.nextLine();
        List<Patient> filteredPatients = hospitalService.getPatientsByHospital(hospitalId);
        displayPatients(filteredPatients);
    }

    // Search methods
    // Search hospitals by Name
    private static void searchHospitalsByName() {
        System.out.print("Enter hospital name to search: ");
        String name = scanner.nextLine();
        List<Hospital> results = hospitalService.searchHospitalsByName(name);
        displayHospitals(results);
    }

    // Search patients by name
    private static void searchPatientsByName() {
        System.out.print("Enter patient name to search: ");
        String name = scanner.nextLine();
        List<Patient> results = hospitalService.searchPatientsByName(name);
        displayPatients(results);
    }

    // Search hospital by id
    private static void findHospitalById() {
        System.out.print("Enter hospital ID: ");
        String id = scanner.nextLine();
        Hospital hospital = hospitalService.findHospitalById(id);
        if (hospital != null) {
            System.out.println("\n" + hospital);
        } else {
            System.out.println("❌ Hospital not found with ID: " + id);
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Search patient by id
    private static void findPatientById() {
        System.out.print("Enter patient ID: ");
        String id = scanner.nextLine();
        Patient patient = hospitalService.findPatientById(id);
        if (patient != null) {
            System.out.println("\n" + patient);
        } else {
            System.out.println("❌ Patient not found with ID: " + id);
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Add methods 
    // Add new hospital to the system
    private static void addNewHospital() {
        if (!hospitalService.getCurrentUser().hasPermission("ADD_HOSPITALS")) {
            System.out.println("❌ Access denied! You don't have permission to add hospitals.");
            return;
        }
        
        System.out.println("\n--- Add New Hospital ---");
        System.out.print("Hospital ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Room Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Total Rooms: ");
        int totalRooms = Integer.parseInt(scanner.nextLine());
        System.out.print("Beds: ");
        int beds = Integer.parseInt(scanner.nextLine());
        System.out.print("Available Rooms: ");
        int availableRooms = Integer.parseInt(scanner.nextLine());
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Rating (1.0-5.0): ");
        double rating = Double.parseDouble(scanner.nextLine());
        
        Hospital newHospital = new Hospital(id, name, city, address, phone, price, 
                                          totalRooms, beds, availableRooms, specialization, rating);
        
        if (hospitalService.addHospital(newHospital)) {
            System.out.println("✅ Hospital added successfully!");
        } else {
            System.out.println("❌ Failed to add hospital!");
        }
    }

    // Add new patient
    private static void addNewPatient() {
        if (!hospitalService.getCurrentUser().hasPermission("ADD_PATIENTS")) {
            System.out.println("❌ Access denied! You don't have permission to add patients.");
            return;
        }
        
        System.out.println("\n--- Add New Patient ---");
        System.out.print("Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Emergency Contact: ");
        String emergencyContact = scanner.nextLine();
        System.out.print("Blood Group: ");
        String bloodGroup = scanner.nextLine();
        System.out.print("Assigned Hospital ID: ");
        String hospitalId = scanner.nextLine();
        System.out.print("Room Number: ");
        String roomNumber = scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Status (ADMITTED/DISCHARGED/OUT_PATIENT): ");
        String status = scanner.nextLine();
        System.out.print("Total Bill: ");
        double totalBill = Double.parseDouble(scanner.nextLine());
        System.out.print("Tribe: ");
        String tribe = scanner.nextLine();
        
        Patient newPatient = new Patient(id, firstName, lastName, phone, email, dob,
                                       gender, address, emergencyContact, bloodGroup,
                                       hospitalId, roomNumber, diagnosis, LocalDateTime.now(),
                                       status, totalBill, tribe);
        
        if (hospitalService.addPatient(newPatient)) {
            System.out.println("✅ Patient added successfully!");
        } else {
            System.out.println("❌ Failed to add patient!");
        }
    }

    // Add new user (Admin only)
    private static void addNewUser() {
        System.out.println("\n--- Add New User ---");
        System.out.print("User ID: ");
        String id = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (ADMIN/DOCTOR/NURSE/RECEPTIONIST): ");
        String role = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        
        User newUser = new User(id, username, password, role, firstName, lastName,
                               email, phone, department, true);
        
        if (hospitalService.addUser(newUser)) {
            System.out.println("✅ User added successfully!");
        } else {
            System.out.println("❌ Failed to add user!");
        }
    }

    // Update patient's status
    private static void updatePatient() {
        if (!hospitalService.getCurrentUser().hasPermission("EDIT_PATIENTS")) {
            System.out.println("❌ Access denied! You don't have permission to edit patients.");
            return;
        }
        
        System.out.print("Enter Patient ID to update: ");
        String patientId = scanner.nextLine();
        Patient patient = hospitalService.findPatientById(patientId);
        
        if (patient == null) {
            System.out.println("❌ Patient not found!");
            return;
        }
        
        System.out.println("Current patient info:");
        System.out.println(patient);
        
        System.out.println("\nEnter new values (press Enter to keep current value):");
        
        System.out.print("Status [" + patient.getStatus() + "]: ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) {
            patient.setStatus(status);
        }
        
        System.out.print("Diagnosis [" + patient.getDiagnosis() + "]: ");
        String diagnosis = scanner.nextLine();
        if (!diagnosis.isEmpty()) {
            patient.setDiagnosis(diagnosis);
        }
        
        System.out.print("Room Number [" + patient.getRoomNumber() + "]: ");
        String roomNumber = scanner.nextLine();
        if (!roomNumber.isEmpty()) {
            patient.setRoomNumber(roomNumber);
        }
        
        System.out.print("Total Bill [" + patient.getTotalBill() + "]: ");
        String billStr = scanner.nextLine();
        if (!billStr.isEmpty()) {
            try {
                double totalBill = Double.parseDouble(billStr);
                patient.setTotalBill(totalBill);
            } catch (NumberFormatException e) {
                System.out.println("Invalid bill amount, keeping current value.");
            }
        }
        
        if (hospitalService.updatePatient(patient)) {
            System.out.println("✅ Patient updated successfully!");
        } else {
            System.out.println("❌ Failed to update patient!");
        }
    }

    // Update user role and status (Admin only)
    private static void updateUser() {
        System.out.print("Enter User ID to update: ");
        String userId = scanner.nextLine();
        List<User> users = hospitalService.getAllUsers();
        User user = users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
        
        if (user == null) {
            System.out.println("❌ User not found!");
            return;
        }
        
        System.out.println("Current user info:");
        System.out.println(user);
        
        System.out.println("\nEnter new values (press Enter to keep current value):");
        
        System.out.print("Active status [" + user.isActive() + "] (true/false): ");
        String activeStr = scanner.nextLine();
        if (!activeStr.isEmpty()) {
            user.setActive(Boolean.parseBoolean(activeStr));
        }

        System.out.println("Current Role [" + user.getRole() + "]: ");
        System.out.print("Enter New Role: ");
        String role = scanner.nextLine();
        if (!role.isEmpty()) {
            user.setRole(role);
        }
        
        System.out.print("Department [" + user.getDepartment() + "]: ");
        String department = scanner.nextLine();
        if (!department.isEmpty()) {
            user.setDepartment(department);
        }
        
        if (hospitalService.updateUser(user)) {
            System.out.println("✅ User updated successfully!");
        } else {
            System.out.println("❌ Failed to update user!");
        }
    }

    // Reports and system overview
    private static void showSystemOverview() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("           SYSTEM OVERVIEW");
        System.out.println("═══════════════════════════════════════");
        System.out.println("System Name:  Hospital Management System");
        System.out.println("Version:      1.0.0");
        System.out.println("Developer:    Mamman Cornelius Ohiani");
        System.out.println("Matric No:    2022010527");
        System.err.println("Institution:  Ladoke Akintola University of Technology(LAUTECH)");
        System.out.println("Faculty:      Computing and Informatics");
        System.out.println("Department:   Computer Science");
        System.out.println("Address:      Ogbomosho, Oyo State, Nigeria");
        System.out.println();

        // Hospital management system features
        System.out.println("System Features:");
        System.out.println("• User Authentication & Role Management");
        System.out.println("• Hospital Data Management");
        System.out.println("• Patient Records Management");
        System.out.println("• JSON File-based Persistence(For immediate storage, no online database Required)");
        System.out.println("• Advanced Search & Filtering");
        System.out.println("• Comprehensive Reporting");
        System.out.println("• Data Backup & Recovery");
        System.out.println("• Dataset focus on local Population, hence the overwhelming use of Yoruba names");
        System.out.println();

        // Current session info
        System.out.println("Current Session:");
        User currentUser = hospitalService.getCurrentUser();
        System.out.println("• Logged in as: " + currentUser.getFullName());
        System.out.println("• Role: " + currentUser.getRole());
        System.out.println("• Department: " + currentUser.getDepartment());
        System.out.println("• Last Login: " + (currentUser.getLastLogin() != null ? 
                         currentUser.getLastLogin().toString() : "Current session"));
        
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Data backup and user actions
    private static void backupData() {
        if (!hospitalService.getCurrentUser().getRole().equals("ADMIN")) {
            System.out.println("❌ Access denied! Only administrators can backup data.");
            return;
        }
        
        System.out.print("Are you sure you want to backup all data? (y/N): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
            hospitalService.backupData();
            System.out.println("✅ Data backup completed successfully!");
        } else {
            System.out.println("Backup cancelled.");
        }
    }

    // Change user password
    private static void changePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();
        
        if (!hospitalService.getCurrentUser().getPassword().equals(currentPassword)) {
            System.out.println("❌ Current password is incorrect!");
            return;
        }
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm new password: ");
        String confirmPassword = scanner.nextLine();
        
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("❌ Passwords do not match!");
            return;
        }
        
        if (newPassword.length() < 6) {
            System.out.println("❌ Password must be at least 6 characters long!");
            return;
        }
        
        hospitalService.getCurrentUser().setPassword(newPassword);
        hospitalService.saveData();
        System.out.println("✅ Password changed successfully!");
    }

    // Logout current user
    private static void logout() {
        System.out.print("Are you sure you want to logout? (y/N): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Logging out...");
            hospitalService.logout();
            System.out.println("✅ Logout successful!");
        }
    }

    // Exit system
    private static void exitSystem() {
        System.out.print("Are you sure you want to exit the system? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
            hospitalService.saveData(); // Ensure all data is saved before exit
            hospitalService.logout(); // Logout current user
            System.out.println("\n🏥 Thank you for using OASIS Hospital Management System!🏥");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("   Institution:  Ladoke Akintola University of Technology(LAUTECH)");
            System.out.println("   Faculty:      Computing and Informatics");
            System.out.println("   Department:   Computer Science");
            System.out.println("   Developed by: Mamman Cornelius Ohiani");
            System.err.println("   Matric No:    2022010527");
            System.err.println("   Version:      1.0.0");
            System.exit(0);
        }
    }
}