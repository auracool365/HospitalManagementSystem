import java.time.LocalDateTime;

public class User {
    private String userId;
    private String username;
    private String password;
    private String role; // ADMIN, DOCTOR, NURSE, RECEPTIONIST
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String department;
    private LocalDateTime lastLogin;
    private boolean isActive;
    private LocalDateTime createdAt;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String userId, String username, String password, String role,
               String firstName, String lastName, String email, String phoneNumber,
               String department, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.isActive = isActive;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean hasPermission(String permission) {
        return switch (role.toLowerCase()) {
            case "admin" -> true;
            case "doctor" -> permission.equals("VIEW_PATIENTS") || permission.equals("EDIT_PATIENTS") 
                || permission.equals("VIEW_HOSPITALS");
            case "nurse" -> permission.equals("VIEW_PATIENTS") || permission.equals("VIEW_HOSPITALS");
            case "receptionist" -> permission.equals("VIEW_PATIENTS") || permission.equals("ADD_PATIENTS") 
                || permission.equals("VIEW_HOSPITALS");
            default -> false;
        }; // Admin has all permissions
    }

    @Override
    public String toString() {
        return String.format("""
                             User ID: %s
                             Username: %s
                             Name: %s %s
                             Role: %s
                             Email: %s
                             Phone: %s
                             Department: %s
                             Status: %s
                             Last Login: %s
                             Created: %s
                             \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500""",
            userId, username, firstName, lastName, role, email, phoneNumber,
            department, isActive ? "Active" : "Inactive",
            lastLogin != null ? lastLogin.toString() : "Never",
            createdAt != null ? createdAt.toString() : "Unknown"
        );
    }
}