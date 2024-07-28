import java.util.Scanner;

public class RocketLaunchSimulator {
    // Initial state variables
    private static String stage = "Pre-Launch";
    private static int fuel = 100; // percentage
    private static int altitude = 0; // km
    private static int speed = 0; // km/h

    // Constants
    private static final int ORBIT_ALTITUDE = 200; // km
    private static final int ORBIT_SPEED = 20000; // km/h

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "start_checks":
                    preLaunchChecks();
                    break;
                case "launch":
                    launch();
                    break;
                case "exit":
                    System.out.println("Exiting simulator.");
                    scanner.close();
                    return;
                default:
                    if (command.startsWith("fast_forward")) {
                        handleFastForward(command);
                    } else {
                        System.out.println("Invalid command.");
                    }
                    break;
            }
        }
    }

    private static void preLaunchChecks() {
        if (stage.equals("Pre-Launch")) {
            System.out.println("All systems are 'Go' for launch.");
            stage = "Stage 1";
        } else {
            System.out.println("Launch sequence already initiated.");
        }
    }

    private static void simulateSecond() {
        if (fuel <= 0) {
            System.out.println("Mission Failed due to insufficient fuel.");
            System.exit(0);
        }

        fuel -= 5;
        altitude += 10;
        speed += 1000;

        System.out.printf("Stage: %s, Fuel: %d%%, Altitude: %d km, Speed: %d km/h%n", stage, fuel, altitude, speed);

        if (altitude >= ORBIT_ALTITUDE && speed >= ORBIT_SPEED) {
            System.out.println("Orbit achieved! Mission Successful.");
            System.exit(0);
        }

        if (altitude >= 100 && stage.equals("Stage 1")) {
            System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
            stage = "Stage 2";
        }
    }

    private static void launch() {
        if (stage.equals("Pre-Launch")) {
            System.out.println("Mission started. Launching...");
            stage = "Stage 1";
            runSimulation();
        } else {
            System.out.println("Launch sequence already initiated.");
        }
    }

    private static void runSimulation() {
        while (true) {
            simulateSecond();
            try {
                Thread.sleep(1000); // Simulate each second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleFastForward(String command) {
        if (stage.equals("Pre-Launch")) {
            System.out.println("Launch sequence not started. Type 'launch' to start the mission.");
            return;
        }

        try {
            int seconds = Integer.parseInt(command.split(" ")[1]);
            for (int i = 0; i < seconds; i++) {
                simulateSecond();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid command format. Use fast_forward X where X is an integer.");
        }
    }
}
