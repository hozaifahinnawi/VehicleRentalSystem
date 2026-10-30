
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final RentalSystem system = new RentalSystem();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            showMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        addVehicle();
                        break;

                    case 2:
                        system.displayAllVehicles();
                        break;

                    case 3:
                        searchVehicle();
                        break;

                    case 4:
                        system.displayAvailableVehicles();
                        break;

                    case 5:
                        addCustomer();
                        break;

                    case 6:
                        system.displayAllCustomers();
                        break;

                    case 7:
                        rentVehicle();
                        break;

                    case 8:
                        int rentalId = readInt("Enter rental ID: ");
                        system.returnVehicle(rentalId);
                        break;

                    case 9:
                        system.displayAllRentals();
                        break;

                    case 10:
                        int customerId = readInt("Enter customer ID: ");
                        system.displayCustomerHistory(customerId);
                        break;

                    case 11:
                        System.out.printf("Total revenue: $%.2f%n",
                                system.calculateTotalRevenue());
                        break;

                    case 12:
                        int maintenanceId =
                                readInt("Enter vehicle ID: ");
                        system.flagVehicleForMaintenance(maintenanceId);
                        break;

                    case 13:
                        system.displayVehiclesNeedingMaintenance();
                        break;

                    case 14:
                        int vehicleId =
                                readInt("Enter vehicle ID: ");
                        system.performVehicleMaintenance(vehicleId);
                        break;

                    case 15:
                        updateCustomerContact();
                        break;

                    case 16:
                        int rentedCustomerId =
                                readInt("Enter customer ID: ");
                        system.displayVehiclesRentedByCustomer(
                                rentedCustomerId);
                        break;

                    case 0:
                        running = false;
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (IllegalArgumentException |
                     IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        input.close();
    }

    private static void showMenu() {
        System.out.println("===== VEHICLE RENTAL SYSTEM =====");
        System.out.println("1.  Add Vehicle");
        System.out.println("2.  Display All Vehicles");
        System.out.println("3.  Search Vehicle by ID");
        System.out.println("4.  Display Available Vehicles");
        System.out.println("5.  Register Customer");
        System.out.println("6.  Display All Customers");
        System.out.println("7.  Rent Vehicle");
        System.out.println("8.  Return Vehicle");
        System.out.println("9.  Display All Rentals");
        System.out.println("10. Display Customer Rental History");
        System.out.println("11. Calculate Total Revenue");
        System.out.println("12. Flag Vehicle for Maintenance");
        System.out.println("13. Display Vehicles Needing Maintenance");
        System.out.println("14. Perform Vehicle Maintenance");
        System.out.println("15. Update Customer Contact");
        System.out.println("16. Display Customer's Rented Vehicles");
        System.out.println("0.  Exit");
        System.out.println("================================");
    }

    private static void addVehicle() {
        System.out.println("Choose vehicle type:");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");

        int type = readInt("Enter type: ");

        int id = readInt("Enter vehicle ID: ");
        String brand = readLine("Enter brand: ");
        String model = readLine("Enter model: ");
        int year = readInt("Enter year: ");
        double price = readDouble("Enter daily price: ");

        Vehicle vehicle;

        switch (type) {
            case 1:
                int seats = readInt("Enter number of seats: ");
                String transmission =
                        readLine("Enter transmission (Manual/Automatic): ");

                vehicle = new Car(id, brand, model, year,
                        price, seats, transmission);
                break;

            case 2:
                int engineCapacity =
                        readInt("Enter engine capacity (CC): ");

                vehicle = new Motorcycle(id, brand, model,
                        year, price, engineCapacity);
                break;

            case 3:
                double capacity =
                        readDouble("Enter maximum load capacity in tons: ");

                vehicle = new Truck(id, brand, model,
                        year, price, capacity);
                break;

            default:
                System.out.println("Invalid vehicle type.");
                return;
        }

        system.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    private static void searchVehicle() {
        int id = readInt("Enter vehicle ID: ");

        Vehicle vehicle = system.findVehicle(id);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            vehicle.displayInfo();
        }
    }

    private static void addCustomer() {
        int id = readInt("Enter customer ID: ");
        String name = readLine("Enter full name: ");
        String phone = readLine("Enter phone number: ");
        String email = readLine("Enter email: ");
        String license = readLine("Enter driving license: ");

        Customer customer = new Customer(
                id, name, phone, email, license);

        system.addCustomer(customer);
        System.out.println("Customer registered successfully.");
    }

    private static void rentVehicle() {
        int rentalId = readInt("Enter new rental ID: ");
        int customerId = readInt("Enter customer ID: ");
        int vehicleId = readInt("Enter vehicle ID: ");
        int days = readInt("Enter rental days: ");

        Vehicle vehicle = system.findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        boolean optionalService = false;

        if (vehicle instanceof Car) {
            String answer = readLine(
                    "Add insurance for $15 per day? (yes/no): ");
            optionalService = answer.equalsIgnoreCase("yes");

        } else if (vehicle instanceof Motorcycle) {
            String answer = readLine(
                    "Include helmet for $5 per day? (yes/no): ");
            optionalService = answer.equalsIgnoreCase("yes");
        }

        system.rentVehicle(rentalId, customerId,
                vehicleId, days, optionalService);
    }

    private static void updateCustomerContact() {
        int id = readInt("Enter customer ID: ");

        System.out.println("1. Update phone");
        System.out.println("2. Update email");

        int choice = readInt("Choose update type: ");

        if (choice == 1) {
            String phone = readLine("Enter new phone: ");
            system.updateCustomerPhone(id, phone);

        } else if (choice == 2) {
            String email = readLine("Enter new email: ");
            system.updateCustomerEmail(id, email);

        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static String readLine(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
