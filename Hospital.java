/**
 * Hospital Management System
 * Student: Mamman Cornelius Ohiani
 * Matric No: 2022010527
 * Department: Computer Science
 * Faculty: Computing and Informatics
 */

public class Hospital {
    private String hospitalId;
    private String name;
    private String city;
    private String address;
    private String phoneNumber;
    private double roomPrice;
    private int totalRooms;
    private int beds;
    private int availableRooms;
    private String specialization;
    private double rating;

    // Default constructor
    public Hospital() {}

    // Parameterized constructor
    public Hospital(String hospitalId, String name, String city, String address, 
                   String phoneNumber, double roomPrice, int totalRooms, int beds,
                   int availableRooms, String specialization, double rating) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.roomPrice = roomPrice;
        this.totalRooms = totalRooms;
        this.beds = beds;
        this.availableRooms = availableRooms;
        this.specialization = specialization;
        this.rating = rating;
    }

    // Getters and Setters
    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getRoomPrice() { return roomPrice; }
    public void setRoomPrice(double roomPrice) { this.roomPrice = roomPrice; }

    public int getTotalRooms() { return totalRooms; }
    public void setTotalRooms(int totalRooms) { this.totalRooms = totalRooms; }

    public int getBeds() { return beds; }
    public void setBeds(int beds) { this.beds = beds; }

    public int getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return String.format("""
                             Hospital ID: %s
                             Name: %s
                             City: %s
                             Address: %s
                             Phone: %s
                             Room Price: \u20a6%.2f
                             Total Rooms: %d
                             Available Rooms: %d
                             Specialization: %s
                             Rating: %.1f/5.0
                             \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500""",
            hospitalId, name, city, address, phoneNumber, 
            roomPrice, totalRooms, availableRooms, specialization, rating
        );
    }
}
