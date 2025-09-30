import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String HOSPITALS_FILE = "data/hospitals.json";
    private static final String PATIENTS_FILE = "data/patients.json";
    private static final String USERS_FILE = "data/users.json";
    
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    // Ensure data directory exists
    static {
        new File("data").mkdirs();
    }

    // Save hospitals to JSON file
    public static void saveHospitals(List<Hospital> hospitals) {
        try (FileWriter writer = new FileWriter(HOSPITALS_FILE)) {
            gson.toJson(hospitals, writer);
        } catch (IOException e) {
            System.err.println("Error saving hospitals: " + e.getMessage());
        }
    }

    // Load hospitals from JSON file
    public static List<Hospital> loadHospitals() {
        try (FileReader reader = new FileReader(HOSPITALS_FILE)) {
            Type hospitalListType = new TypeToken<ArrayList<Hospital>>(){}.getType();
            List<Hospital> hospitals = gson.fromJson(reader, hospitalListType);
            return hospitals != null ? hospitals : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Hospitals file not found. Creating new dataset...");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading hospitals: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save patients to JSON file
    public static void savePatients(List<Patient> patients) {
        try (FileWriter writer = new FileWriter(PATIENTS_FILE)) {
            gson.toJson(patients, writer);
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
        }
    }

    // Load patients from JSON file
    public static List<Patient> loadPatients() {
        try (FileReader reader = new FileReader(PATIENTS_FILE)) {
            Type patientListType = new TypeToken<ArrayList<Patient>>(){}.getType();
            List<Patient> patients = gson.fromJson(reader, patientListType);
            return patients != null ? patients : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Patients file not found. Creating new dataset...");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading patients: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save users to JSON file
    public static void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    // Load users from JSON file
    public static List<User> loadUsers() {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> users = gson.fromJson(reader, userListType);
            return users != null ? users : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("Users file not found. Creating new dataset...");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Custom adapter for LocalDateTime serialization
    private static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }

        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), formatter);
        }
    }

    // Custom adapter for LocalDate serialization
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);
        }
    }

    // Backup data files
    public static void backupData() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        
        try {
            copyFile(HOSPITALS_FILE, "data/backup/hospitals_" + timestamp + ".json");
            copyFile(PATIENTS_FILE, "data/backup/patients_" + timestamp + ".json");
            copyFile(USERS_FILE, "data/backup/users_" + timestamp + ".json");
            System.out.println("Data backup completed successfully!");
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }

    private static void copyFile(String source, String destination) throws IOException {
        new File("data/backup").mkdirs();
        
        File sourceFile = new File(source);
        if (!sourceFile.exists()) return;
        
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
}
