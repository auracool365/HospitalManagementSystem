import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class HospitalService {
    private List<Hospital> hospitals;
    private List<Patient> patients;
    private List<User> users;
    private User currentUser;

    public HospitalService() {
        loadData();
    }

    // Load data from JSON files or generate if not exists
    public void loadData() {
        hospitals = DataManager.loadHospitals();
        patients = DataManager.loadPatients();
        users = DataManager.loadUsers();

        // Generate initial data if files don't exist
        if (hospitals.isEmpty()) {
            hospitals = DataGenerator.generateHospitals();
            DataManager.saveHospitals(hospitals);
            System.out.println("Generated " + hospitals.size() + " hospitals.");
        }

        if (patients.isEmpty()) {
            patients = DataGenerator.generatePatients(hospitals);
            DataManager.savePatients(patients);
            System.out.println("Generated " + patients.size() + " patients.");
        }

        if (users.isEmpty()) {
            users = DataGenerator.generateUsers();
            DataManager.saveUsers(users);
            System.out.println("Generated " + users.size() + " users.");
        }
    }

    // Authentication
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && 
                user.getPassword().equals(password) && user.isActive()) {
                currentUser = user;
                user.setLastLogin(java.time.LocalDateTime.now());
                saveData();
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Save all data
    public void saveData() {
        DataManager.saveHospitals(hospitals);
        DataManager.savePatients(patients);
        DataManager.saveUsers(users);
    }

    // Hospital operations
    public List<Hospital> getAllHospitals() {
        return new ArrayList<>(hospitals);
    }

    public List<Hospital> getHospitalsByCity(String city) {
        return hospitals.stream()
                .filter(hospital -> hospital.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Hospital> sortHospitalsByName() {
        return hospitals.stream()
                .sorted(Comparator.comparing(Hospital::getName))
                .collect(Collectors.toList());
    }

    public List<Hospital> sortHospitalsByRoomPrice() {
        return hospitals.stream()
                .sorted(Comparator.comparing(Hospital::getRoomPrice))
                .collect(Collectors.toList());
    }

    public List<Hospital> sortHospitalsByRating() {
        return hospitals.stream()
                .sorted(Comparator.comparing(Hospital::getRating).reversed())
                .collect(Collectors.toList());
    }

    public Hospital findHospitalById(String hospitalId) {
        return hospitals.stream()
                .filter(hospital -> hospital.getHospitalId().equals(hospitalId))
                .findFirst()
                .orElse(null);
    }

    public List<Hospital> searchHospitalsByName(String name) {
        return hospitals.stream()
                .filter(hospital -> hospital.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean addHospital(Hospital hospital) {
        if (currentUser != null && currentUser.hasPermission("ADD_HOSPITALS")) {
            hospitals.add(hospital);
            saveData();
            return true;
        }
        return false;
    }

    // Patient operations
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public List<Patient> getPatientsByHospital(String hospitalId) {
        return patients.stream()
                .filter(patient -> patient.getAssignedHospitalId().equals(hospitalId))
                .collect(Collectors.toList());
    }

    public List<Patient> getPatientsByStatus(String status) {
        return patients.stream()
                .filter(patient -> patient.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public Patient findPatientById(String patientId) {
        return patients.stream()
                .filter(patient -> patient.getPatientId().equals(patientId))
                .findFirst()
                .orElse(null);
    }

    public List<Patient> searchPatientsByName(String name) {
        return patients.stream()
                .filter(patient -> (patient.getFirstName() + " " + patient.getLastName())
                        .toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Patient> sortPatientsByName() {
        return patients.stream()
                .sorted(Comparator.comparing(Patient::getLastName)
                        .thenComparing(Patient::getFirstName))
                .collect(Collectors.toList());
    }

    public List<Patient> sortPatientsByAge() {
        return patients.stream()
                .sorted(Comparator.comparing(Patient::getAge).reversed())
                .collect(Collectors.toList());
    }

    public boolean addPatient(Patient patient) {
        if (currentUser != null && currentUser.hasPermission("ADD_PATIENTS")) {
            patients.add(patient);
            saveData();
            return true;
        }
        return false;
    }

    public boolean updatePatient(Patient updatedPatient) {
        if (currentUser != null && currentUser.hasPermission("EDIT_PATIENTS")) {
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getPatientId().equals(updatedPatient.getPatientId())) {
                    patients.set(i, updatedPatient);
                    saveData();
                    return true;
                }
            }
        }
        return false;
    }

    // User management
    public List<User> getAllUsers() {
        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            return new ArrayList<>(users);
        }
        return new ArrayList<>();
    }

    public boolean addUser(User user) {
        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            users.add(user);
            saveData();
            return true;
        }
        return false;
    }

    // Public user registration (no login required)
    public boolean registerNewUser(User user) {
        users.add(user);
        saveData();
        return true;
    }

    // Check if username already exists
    public boolean usernameExists(String username) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    // Get next available user ID
    public int getNextUserId() {
        if (users.isEmpty()) {
            return 1;
        }
        return users.stream()
                .mapToInt(user -> {
                    String id = user.getUserId().replace("USR", "");
                    try {
                        return Integer.parseInt(id);
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max()
                .orElse(0) + 1;
    }

    public boolean updateUser(User updatedUser) {
        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserId().equals(updatedUser.getUserId())) {
                    users.set(i, updatedUser);
                    saveData();
                    return true;
                }
            }
        }
        return false;
    }

    // Statistics and reports
    public void generateHospitalReport() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("           HOSPITAL STATISTICS");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total Hospitals: " + hospitals.size());
        
        long ogbomoshoHospitals = hospitals.stream()
                .filter(h -> h.getCity().equalsIgnoreCase("Ogbomosho"))
                .count();
        System.out.println("Hospitals in Ogbomosho: " + ogbomoshoHospitals);
        
        double avgRating = hospitals.stream()
                .mapToDouble(Hospital::getRating)
                .average()
                .orElse(0.0);
        System.out.println("Average Hospital Rating: " + String.format("%.2f", avgRating));
        
        double avgRoomPrice = hospitals.stream()
                .mapToDouble(Hospital::getRoomPrice)
                .average()
                .orElse(0.0);
        System.out.println("Average Room Price: ₦" + String.format("%.2f", avgRoomPrice));
        
        int totalRooms = hospitals.stream()
                .mapToInt(Hospital::getTotalRooms)
                .sum();
        System.out.println("Total Rooms Available: " + totalRooms);
        
        int availableRooms = hospitals.stream()
                .mapToInt(Hospital::getAvailableRooms)
                .sum();
        System.out.println("Currently Available Rooms: " + availableRooms);
        System.out.println("Occupancy Rate: " + 
                         String.format("%.1f%%", ((totalRooms - availableRooms) * 100.0 / totalRooms)));
    }

    public void generatePatientReport() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("           PATIENT STATISTICS");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total Patients: " + patients.size());
        
        long admittedPatients = patients.stream()
                .filter(p -> p.getStatus().equals("ADMITTED"))
                .count();
        System.out.println("Currently Admitted: " + admittedPatients);
        
        long dischargedPatients = patients.stream()
                .filter(p -> p.getStatus().equals("DISCHARGED"))
                .count();
        System.out.println("Discharged Patients: " + dischargedPatients);
        
        long outPatients = patients.stream()
                .filter(p -> p.getStatus().equals("OUT_PATIENT"))
                .count();
        System.out.println("Out Patients: " + outPatients);
        
        double avgAge = patients.stream()
                .mapToInt(Patient::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Average Patient Age: " + String.format("%.1f", avgAge) + " years");
        
        double avgBill = patients.stream()
                .mapToDouble(Patient::getTotalBill)
                .average()
                .orElse(0.0);
        System.out.println("Average Total Bill: ₦" + String.format("%.2f", avgBill));
    }

    // Backup functionality
    public void backupData() {
        if (currentUser != null && currentUser.getRole().equals("ADMIN")) {
            DataManager.backupData();
        } else {
            System.out.println("Only administrators can perform data backup.");
        }
    }
}