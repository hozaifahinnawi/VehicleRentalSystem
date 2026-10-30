
public class Customer {

    private final int id;
    private String fullName;
    private String phone;
    private String email;
    private String drivingLicense;

    public Customer(int id, String fullName, String phone,
                    String email, String drivingLicense) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        setEmail(email);
        this.drivingLicense = drivingLicense;
    }

    // Overloaded constructor
    public Customer(int id, String fullName, String phone,
                    String email) {
        this(id, fullName, phone, email, "Not provided");
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Phone cannot be empty."
            );
        }
        this.phone = phone;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")
                || !email.contains(".")) {
            throw new IllegalArgumentException(
                "Invalid email address."
            );
        }
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + fullName);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Driving License: " + drivingLicense);
    }
}
