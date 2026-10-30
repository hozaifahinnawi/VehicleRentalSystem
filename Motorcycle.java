
public class Motorcycle extends Vehicle implements Maintainable {

    private int engineCapacity;
    private boolean helmetIncluded;
    private boolean maintenanceRequired;

    public Motorcycle(int id, String brand, String model, int year,
                      double dailyPrice, int engineCapacity) {
        super(id, brand, model, year, dailyPrice);
        this.engineCapacity = engineCapacity;
        this.helmetIncluded = false;
        this.maintenanceRequired = false;
    }

    public void setHelmetIncluded(boolean helmetIncluded) {
        this.helmetIncluded = helmetIncluded;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException(
                "Rental days must be positive."
            );
        }

        double total = getDailyPrice() * days;

        if (helmetIncluded) {
            total += 5 * days;
        }

        return total;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Engine Capacity: " + engineCapacity + " CC");
        System.out.println("Helmet Included: " + helmetIncluded);
        System.out.println("Needs Maintenance: " + maintenanceRequired);
    }

    public void flagForMaintenance() {
        maintenanceRequired = true;
    }

    @Override
    public void performMaintenance() {
        if (!isAvailable()) {
            System.out.println("Cannot maintain a rented motorcycle.");
            return;
        }

        maintenanceRequired = false;
        System.out.println("Motorcycle maintenance completed.");
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceRequired;
    }
}
