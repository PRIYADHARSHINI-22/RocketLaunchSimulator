public class Rocket {
    private String stage = "Pre-Launch";
    private int fuel = 100; // percentage
    private int altitude = 0; // km
    private int speed = 0; // km/h

    private static final int ORBIT_ALTITUDE = 200; // km
    private static final int ORBIT_SPEED = 20000; // km/h

    public void preLaunchChecks() {
        if (stage.equals("Pre-Launch")) {
            System.out.println("All systems are 'Go' for launch.");
            stage = "Stage 1";
        } else {
            System.out.println("Launch sequence already initiated.");
        }
    }

    public void launch() {
        if (stage.equals("Pre-Launch")) {
            System.out.println("Mission started. Launching...");
            while (true) {
                simulateSecond();
                try {
                    Thread.sleep(1000); // Simulate each second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Launch sequence already initiated.");
        }
    }

    public void fastForward(int seconds) {
        if (stage.equals("Pre-Launch")) {
            System.out.println("Launch sequence not started. Type 'launch' to start the mission.");
            return;
        }

        for (int i = 0; i < seconds; i++) {
            simulateSecond();
        }
    }

    private void simulateSecond() {
        if (stage.equals("Pre-Launch")) {
            System.out.println("Launch sequence not started. Type 'launch' to start the mission.");
            return;
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

        if (fuel <= 0) {
            System.out.println("Mission Failed due to insufficient fuel.");
            System.exit(0);
        }
    }
}
