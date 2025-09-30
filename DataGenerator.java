import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();

    // Yoruba names and local context for Ogbomosho
    private static final String[] FIRST_NAMES = {
        "Adebayo", "Adunni", "Akeem", "Amina", "Ayodeji", "Bukola", "Chioma", "Damilola",
        "Emeka", "Fatima", "Gbemisola", "Hakeem", "Ifeoma", "Jamiu", "Kemi", "Lanre",
        "Maryam", "Nkechi", "Olumide", "Patience", "Qudus", "Rasheed", "Sola", "Tunde",
        "Usman", "Victoria", "Wale", "Yusuf", "Zainab", "Abolaji", "Blessing", "Chukwuma",
        "Deborah", "Emmanuel", "Funmi", "Grace", "Habib", "Ibrahim", "Joy", "Kayode",
        "Lateef", "Mojisola", "Ngozi", "Omotola", "Peter", "Ramatu", "Samuel", "Taiwo",
        "Uzoma", "Vivian", "Wasiu", "Yakubu", "Zara", "Abisola", "Bunmi", "Chinonso"
    };

    private static final String[] SURNAMES = {
        "Adebayo", "Ogundimu", "Akinwale", "Babatunde", "Olaniyan", "Adeyemi", "Okafor", "Adeleke",
        "Ibrahim", "Mohammed", "Adeolu", "Ogundipe", "Adesanya", "Bamidele", "Ogunleye", "Adepoju",
        "Salami", "Afolabi", "Ogunbiyi", "Adeniyi", "Adesina", "Oguntola", "Ajayi", "Adebola",
        "Omolara", "Ogunsanya", "Adeniran", "Balogun", "Ogundele", "Adeoye", "Awolowo", "Adesola",
        "Oguntade", "Adedire", "Ogunbamike", "Adelusi", "Oguntebi", "Adewale", "Ogunbanjo", "Adesalu",
        "Oyekanmi", "Adejumo", "Ogundipe", "Adelaja", "Ogunshola", "Adesoji", "Oyebamiji", "Adebisi"
    };

    private static final String[] OGBOMOSHO_AREAS = {
        "Agbeni", "Oke Ado", "Iyana Church", "Caretaker", "Sabo", "Takie", "Oja Igbo", "Masifa",
        "Arowomole", "Ori Oke", "Oke Bode", "Ijeru", "Surulere", "Oke Ogi", "Oke Agbo", "Palace Area",
        "Ibrahim Taiwo Road", "Owode", "Oke Ebo", "Ajikobi", "Lagbedu", "Iyana Mortuary"
    };

    private static final String[] BLOOD_GROUPS = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    
    private static final String[] HOSPITAL_NAMES = {
        "Bowen University Teaching Hospital", "LAUTECH Teaching Hospital Ogbomosho", 
        "Baptist Medical Centre", "Ogbomosho General Hospital", "St. Mary's Catholic Hospital",
        "Unity Medical Centre", "Life Care Hospital", "Prime Medical Centre", "Hope Hospital",
        "Grace Medical Centre", "Faith Clinic", "Divine Mercy Hospital", "Good Shepherd Hospital",
        "Royal Medical Centre", "Crystal Hospital", "Golden Gate Medical Centre", "Peace Hospital",
        "Victory Medical Centre", "New Life Hospital", "Mercy Hospital", "Crown Medical Centre"
    };

    private static final String[] SPECIALIZATIONS = {
        "General Medicine", "Pediatrics", "Obstetrics & Gynecology", "Surgery", "Orthopedics",
        "Cardiology", "Neurology", "Dermatology", "Emergency Medicine", "Internal Medicine",
        "Family Medicine", "Ophthalmology", "ENT", "Urology", "Psychiatry"
    };

    private static final String[] DIAGNOSES = {
        "Hypertension", "Diabetes Type 2", "Malaria", "Typhoid Fever", "Gastroenteritis",
        "Respiratory Tract Infection", "Urinary Tract Infection", "Fracture", "Appendicitis",
        "Pneumonia", "Asthma", "Migraine", "Back Pain", "Arthritis", "Anemia", "Jaundice"
    };

    public static List<Hospital> generateHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        
        for (int i = 0; i < 25; i++) {
            String hospitalId = "HOS" + String.format("%03d", i + 1);
            String name = HOSPITAL_NAMES[i % HOSPITAL_NAMES.length];
            if (i >= HOSPITAL_NAMES.length) {
                name += " Branch " + ((i / HOSPITAL_NAMES.length) + 1);
            }
            
            String city = i < 15 ? "Ogbomosho" : (random.nextBoolean() ? "Ilorin" : "Ibadan");
            String address = OGBOMOSHO_AREAS[random.nextInt(OGBOMOSHO_AREAS.length)] + " Area, " + city;
            String phoneNumber = "0" + (700 + random.nextInt(90)) + String.format("%07d", random.nextInt(10000000));
            double roomPrice = 5000 + (random.nextDouble() * 45000); // ₦5,000 to ₦50,000
            int totalRooms = 20 + random.nextInt(180); // 20 to 200 rooms
            int beds = totalRooms + random.nextInt(200); // Beds > Rooms
            int availableRooms = random.nextInt(totalRooms / 2);
            String specialization = SPECIALIZATIONS[random.nextInt(SPECIALIZATIONS.length)];
            double rating = 2.5 + (random.nextDouble() * 2.5); // 2.5 to 5.0
            
            hospitals.add(new Hospital(hospitalId, name, city, address, phoneNumber,
                                     roomPrice, totalRooms, beds, availableRooms, specialization, rating));
        }
        
        return hospitals;
    }

    public static List<Patient> generatePatients(List<Hospital> hospitals) {
        List<Patient> patients = new ArrayList<>();
        
        for (int i = 0; i < 75; i++) {
            String patientId = "PAT" + String.format("%03d", i + 1);
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = SURNAMES[random.nextInt(SURNAMES.length)];
            String phoneNumber = "0" + (700 + random.nextInt(90)) + String.format("%07d", random.nextInt(10000000));
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + 
                          (random.nextBoolean() ? "gmail.com" : "yahoo.com");
            
            LocalDate dob = LocalDate.now().minusYears(18 + random.nextInt(62)); // 18-80 years old
            String gender = random.nextBoolean() ? "Male" : "Female";
            String address = OGBOMOSHO_AREAS[random.nextInt(OGBOMOSHO_AREAS.length)] + " Area, Ogbomosho";
            String emergencyContact = "0" + (700 + random.nextInt(90)) + String.format("%07d", random.nextInt(10000000));
            String bloodGroup = BLOOD_GROUPS[random.nextInt(BLOOD_GROUPS.length)];
            
            String assignedHospitalId = hospitals.get(random.nextInt(hospitals.size())).getHospitalId();
            String roomNumber = (100 + random.nextInt(300)) + (random.nextBoolean() ? "A" : "B");
            String diagnosis = DIAGNOSES[random.nextInt(DIAGNOSES.length)];
            
            LocalDateTime admissionDate = LocalDateTime.now().minusDays(random.nextInt(30));
            String status = random.nextInt(100) < 70 ? "ADMITTED" : 
                           (random.nextBoolean() ? "DISCHARGED" : "OUT_PATIENT");
            
            double totalBill = 10000 + (random.nextDouble() * 190000); // ₦10,000 to ₦200,000
            String tribe = "Yoruba"; // Most people in Ogbomosho are Yoruba
            
            patients.add(new Patient(patientId, firstName, lastName, phoneNumber, email,
                                   dob, gender, address, emergencyContact, bloodGroup,
                                   assignedHospitalId, roomNumber, diagnosis, admissionDate,
                                   status, totalBill, tribe));
        }
        
        return patients;
    }

    public static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        
        // Add default admin user
        users.add(new User("USR001", "admin", "admin123", "ADMIN", 
                          "System", "Administrator", "admin@hospital.com", 
                          "08012345678", "Administration", true));
        
        // Generate other users
        String[] roles = {"DOCTOR", "NURSE", "RECEPTIONIST"};
        String[] departments = {"General Medicine", "Pediatrics", "Surgery", "Emergency", 
                              "Cardiology", "Orthopedics", "Neurology", "Administration"};
        
        for (int i = 0; i < 15; i++) {
            String userId = "USR" + String.format("%03d", i + 2);
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = SURNAMES[random.nextInt(SURNAMES.length)];
            String username = (firstName + lastName).toLowerCase().replaceAll(" ", "");
            String password = "password123"; // In real system, this should be hashed
            String role = roles[random.nextInt(roles.length)];
            String email = username + "@hospital.com";
            String phoneNumber = "0" + (700 + random.nextInt(90)) + String.format("%07d", random.nextInt(10000000));
            String department = departments[random.nextInt(departments.length)];
            
            users.add(new User(userId, username, password, role, firstName, lastName,
                              email, phoneNumber, department, true));
        }
        
        return users;
    }
}