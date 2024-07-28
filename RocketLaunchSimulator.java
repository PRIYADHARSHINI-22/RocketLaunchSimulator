import java.util.Scanner;

// Entry point for the Rocket Launch Simulator
public class RocketLaunchSimulator {

    public static void main(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            if (commandProcessor.processCommand(command)) {
                break;
            }
        }

        scanner.close();
    }
}
