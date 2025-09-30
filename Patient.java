import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {
    private String patientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String emergencyContact;
    private String bloodGroup;
    private String assignedHospitalId;
    private String roomNumber;
    private String diagnosis;
    private LocalDateTime admissionDate;
    private LocalDateTime dischargeDate;
    private String status; // ADMITTED, DISCHARGED, OUT_PATIENT
    private double totalBill;
    private String tribe; // Added for Yoruba cultural context

    // Default constructor
    public Patient() {}

    // Parameterized constructor
    public Patient(String patientId, String firstName, String lastName, String phoneNumber,
                  String email, LocalDate dateOfBirth, String gender, String address,
                  String emergencyContact, String bloodGroup, String assignedHospitalId,
                  String roomNumber, String diagnosis, LocalDateTime admissionDate,
                  String status, double totalBill, String tribe) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.bloodGroup = bloodGroup;
        this.assignedHospitalId = assignedHospitalId;
        this.roomNumber = roomNumber;
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.status = status;
        this.totalBill = totalBill;
        this.tribe = tribe;
    }

    // Getters and Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getAssignedHospitalId() { return assignedHospitalId; }
    public void setAssignedHospitalId(String assignedHospitalId) { this.assignedHospitalId = assignedHospitalId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public LocalDateTime getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDateTime admissionDate) { this.admissionDate = admissionDate; }

    public LocalDateTime getDischargeDate() { return dischargeDate; }
    public void setDischargeDate(LocalDateTime dischargeDate) { this.dischargeDate = dischargeDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalBill() { return totalBill; }
    public void setTotalBill(double totalBill) { this.totalBill = totalBill; }

    public String getTribe() { return tribe; }
    public void setTribe(String tribe) { this.tribe = tribe; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    @Override
    public String toString() {
        return String.format("""
                             Patient ID: %s
                             Name: %s %s
                             Phone: %s
                             Email: %s
                             Date of Birth: %s (Age: %d)
                             Gender: %s
                             Tribe: %s
                             Blood Group: %s
                             Address: %s
                             Emergency Contact: %s
                             Assigned Hospital: %s
                             Room Number: %s
                             Diagnosis: %s
                             Admission Date: %s
                             Status: %s
                             Total Bill: \u20a6%.2f
                             \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500""",
            patientId, firstName, lastName, phoneNumber, email, 
            dateOfBirth, getAge(), gender, tribe, bloodGroup, address, 
            emergencyContact, assignedHospitalId, roomNumber, diagnosis, 
            admissionDate != null ? admissionDate.toString() : "N/A", 
            status, totalBill
        );
    }
}