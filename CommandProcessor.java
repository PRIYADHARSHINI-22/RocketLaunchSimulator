public class CommandProcessor {
    private final Rocket rocket;

    public CommandProcessor() {
        rocket = new Rocket();
    }

    // Processes the user command
    public boolean processCommand(String command) {
        if (command.equals("start_checks")) {
            rocket.preLaunchChecks();
        } else if (command.equals("launch")) {
            rocket.launch();
        } else if (command.startsWith("fast_forward")) {
            try {
                int seconds = Integer.parseInt(command.split(" ")[1]);
                rocket.fastForward(seconds);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid command format. Use fast_forward X where X is an integer.");
            }
        } else if (command.equals("exit")) {
            System.out.println("Exiting simulator.");
            return true;
        } else {
            System.out.println("Invalid command.");
        }
        return false;
    }
}
