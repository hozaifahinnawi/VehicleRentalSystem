
public class Car extends Vehicle implements Maintainable {

    private int seats;
    private String transmission;
    private boolean insurance;
    private boolean maintenanceRequired;

    public Car(int id, String brand, String model, int year,
               double dailyPrice, int seats, String transmission) {
        super(id, brand, model, year, dailyPrice);
        this.seats = seats;
        this.transmission = transmission;
        this.insurance = false;
        this.maintenanceRequired = false;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException(
                "Rental days must be positive."
            );
        }

        double total = getDailyPrice() * days;

        if (insurance) {
            total += 15 * days;
        }

        return total;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Seats: " + seats);
        System.out.println("Transmission: " + transmission);
        System.out.println("Insurance: " + insurance);
        System.out.println("Needs Maintenance: " + maintenanceRequired);
    }

    public void flagForMaintenance() {
        maintenanceRequired = true;
    }

    @Override
    public void performMaintenance() {
        if (!isAvailable()) {
            System.out.println("Cannot maintain a rented car.");
            return;
        }

        maintenanceRequired = false;
        System.out.println("Car maintenance completed.");
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceRequired;
    }
}
