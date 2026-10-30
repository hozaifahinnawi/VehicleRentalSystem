
public class Rental {

    private final int id;
    private final Customer customer;
    private final Vehicle vehicle;
    private final int days;
    private final double totalCost;
    private boolean completed;

    public Rental(int id, Customer customer, Vehicle vehicle,
                  int days, double totalCost) {

        if (customer == null || vehicle == null) {
            throw new IllegalArgumentException(
                "Customer and vehicle are required."
            );
        }

        if (days <= 0) {
            throw new IllegalArgumentException(
                "Rental days must be positive."
            );
        }

        if (totalCost < 0) {
            throw new IllegalArgumentException(
                "Rental cost cannot be negative."
            );
        }

        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCost = totalCost;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeRental() {
        if (completed) {
            throw new IllegalStateException(
                "Rental is already completed."
            );
        }

        completed = true;
    }

    public void displayInfo() {
        System.out.println("Rental ID: " + id);
        System.out.println("Customer: " + customer.getFullName());
        System.out.println("Vehicle ID: " + vehicle.getId());
        System.out.println("Rental Days: " + days);
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("Status: "
                + (completed ? "Completed" : "Active"));
    }
}
