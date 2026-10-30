
public class Truck extends Vehicle implements Maintainable {

    private double maxLoadCapacity;
    private boolean maintenanceRequired;

    public Truck(int id, String brand, String model, int year,
                 double dailyPrice, double maxLoadCapacity) {
        super(id, brand, model, year, dailyPrice);
        this.maxLoadCapacity = maxLoadCapacity;
        this.maintenanceRequired = false;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException(
                "Rental days must be positive."
            );
        }

        return getDailyPrice() * days + 50;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Maximum Load: " + maxLoadCapacity + " tons");
        System.out.println("Needs Maintenance: " + maintenanceRequired);
    }

    public void flagForMaintenance() {
        maintenanceRequired = true;
    }

    @Override
    public void performMaintenance() {
        if (!isAvailable()) {
            System.out.println("Cannot maintain a rented truck.");
            return;
        }

        maintenanceRequired = false;
        System.out.println("Truck maintenance completed.");
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceRequired;
    }
}
