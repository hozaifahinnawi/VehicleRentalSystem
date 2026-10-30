
public abstract class Vehicle {

    private final int id;
    private String brand;
    private String model;
    private int year;
    private double dailyPrice;
    private boolean available;

    public Vehicle(int id, String brand, String model,
                   int year, double dailyPrice) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setDailyPrice(double dailyPrice) {
        if (dailyPrice <= 0) {
            throw new IllegalArgumentException(
                "Daily price must be positive."
            );
        }
        this.dailyPrice = dailyPrice;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateRentalCost(int days);

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Daily Price: $" + dailyPrice);
        System.out.println("Available: " + available);
    }
}
