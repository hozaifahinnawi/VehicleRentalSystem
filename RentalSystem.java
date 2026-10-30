
import java.util.ArrayList;

public class RentalSystem {

    private ArrayList<Vehicle> vehicles;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    public RentalSystem() {
        vehicles = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    // Add a vehicle with a unique ID
    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null || findVehicle(vehicle.getId()) != null) {
            System.out.println("Invalid vehicle or duplicate ID.");
            return;
        }

        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    // Add a customer with a unique ID
    public void addCustomer(Customer customer) {
        if (customer == null || findCustomer(customer.getId()) != null) {
            System.out.println("Invalid customer or duplicate ID.");
            return;
        }

        customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    // Overloaded search: by vehicle ID
    public Vehicle findVehicle(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    // Overloaded search: by brand
    public ArrayList<Vehicle> findVehicle(String brand) {
        ArrayList<Vehicle> result = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (v.getBrand().equalsIgnoreCase(brand)) {
                result.add(v);
            }
        }
        return result;
    }

    public Customer findCustomer(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    private Rental findRental(int id) {
        for (Rental r : rentals) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicles) {
            v.displayInfo();
            System.out.println("--------------------");
        }
    }

    public void displayAvailableVehicles() {
        for (Vehicle v : vehicles) {
            if (v.isAvailable()
                    && !((Maintainable) v).needsMaintenance()) {
                v.displayInfo();
                System.out.println("--------------------");
            }
        }
    }

    public void displayAllCustomers() {
        for (Customer c : customers) {
            c.displayInfo();
            System.out.println("--------------------");
        }
    }

    public void displayAllRentals() {
        for (Rental r : rentals) {
            r.displayInfo();
            System.out.println("--------------------");
        }
    }

    // Rent a vehicle
    public void rentVehicle(int rentalId, int customerId,
                            int vehicleId, int days,
                            boolean optionalService) {

        if (days <= 0) {
            System.out.println("Rental days must be positive.");
            return;
        }

        if (findRental(rentalId) != null) {
            System.out.println("Duplicate rental ID.");
            return;
        }

        Customer customer = findCustomer(customerId);
        Vehicle vehicle = findVehicle(vehicleId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        Maintainable maintenance = (Maintainable) vehicle;

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is not available.");
            return;
        }

        if (maintenance.needsMaintenance()) {
            System.out.println("Vehicle requires maintenance.");
            return;
        }

        double cost;

        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            car.setInsurance(optionalService);
            cost = car.calculateRentalCost(days);
            car.setInsurance(false);

        } else if (vehicle instanceof Motorcycle) {
            Motorcycle bike = (Motorcycle) vehicle;
            bike.setHelmetIncluded(optionalService);
            cost = bike.calculateRentalCost(days);
            bike.setHelmetIncluded(false);

        } else {
            cost = vehicle.calculateRentalCost(days);
        }

        Rental rental = new Rental(
            rentalId, customer, vehicle, days, cost
        );

        rentals.add(rental);
        vehicle.setAvailable(false);

        System.out.println("Rental created successfully.");
        System.out.println("Total cost: $" + cost);
    }

    // Return a vehicle using the rental ID
    public void returnVehicle(int rentalId) {
        Rental rental = findRental(rentalId);

        if (rental == null) {
            System.out.println("Rental not found.");
            return;
        }

        if (rental.isCompleted()) {
            System.out.println("Rental already completed.");
            return;
        }

        rental.completeRental();
        rental.getVehicle().setAvailable(true);

        System.out.println("Vehicle returned successfully.");
    }

    public void displayCustomerHistory(int customerId) {
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        boolean found = false;

        for (Rental r : rentals) {
            if (r.getCustomer().getId() == customerId) {
                r.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rental history.");
        }
    }

    public void displayVehiclesRentedByCustomer(int customerId) {
        boolean found = false;

        for (Rental r : rentals) {
            if (r.getCustomer().getId() == customerId
                    && !r.isCompleted()) {
                r.getVehicle().displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No active rentals for this customer.");
        }
    }

    // Completed rentals only
    public double calculateTotalRevenue() {
        double revenue = 0;

        for (Rental r : rentals) {
            if (r.isCompleted()) {
                revenue += r.getTotalCost();
            }
        }

        return revenue;
    }

    public void updateCustomerPhone(int customerId, String phone) {
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        customer.setPhone(phone);
        System.out.println("Phone updated successfully.");
    }

    public void updateCustomerEmail(int customerId, String email) {
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        customer.setEmail(email);
        System.out.println("Email updated successfully.");
    }

    public void updateVehiclePrice(int vehicleId, double price) {
        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        vehicle.setDailyPrice(price);
        System.out.println("Price updated successfully.");
    }

    // Flag a vehicle for maintenance
    public void flagVehicleForMaintenance(int vehicleId) {
        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (vehicle instanceof Car) {
            ((Car) vehicle).flagForMaintenance();
        } else if (vehicle instanceof Motorcycle) {
            ((Motorcycle) vehicle).flagForMaintenance();
        } else if (vehicle instanceof Truck) {
            ((Truck) vehicle).flagForMaintenance();
        }

        System.out.println("Vehicle flagged for maintenance.");
    }

    // Interface-based polymorphism
    public void displayVehiclesNeedingMaintenance() {
        for (Maintainable m : getMaintainableVehicles()) {
            if (m.needsMaintenance()) {
                ((Vehicle) m).displayInfo();
                System.out.println("--------------------");
            }
        }
    }

    private ArrayList<Maintainable> getMaintainableVehicles() {
        ArrayList<Maintainable> result = new ArrayList<>();

        for (Vehicle v : vehicles) {
            result.add((Maintainable) v);
        }

        return result;
    }

    public void performVehicleMaintenance(int vehicleId) {
        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Cannot maintain a rented vehicle.");
            return;
        }

        Maintainable maintenance = (Maintainable) vehicle;

        if (!maintenance.needsMaintenance()) {
            System.out.println("Vehicle does not require maintenance.");
            return;
        }

        maintenance.performMaintenance();
    }
}
