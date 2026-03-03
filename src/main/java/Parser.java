import java.io.IOException;
import java.util.Scanner;

/**
 * Parses user input and converts it into executable commands.
 */
public class Parser {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads user input from the console and converts it into a Command object.
     *
     * @return The command corresponding to the user input.
     * @throws SakutaException If the user input is invalid or missing required arguments.
     * @throws IOException If an input or output error occurs.
     */
    public Command readCommand() throws SakutaException, IOException {
        System.out.print("> ");
        String line = scanner.nextLine().trim();
        String[] partsBySpace = line.split("\\s+");
        String[] partsBySlash = line.split("/");
        String command = partsBySpace[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "todo":
            String toDoDesc = partsBySlash[0].substring(4).trim();
            return new TodoCommand(toDoDesc);

        case "deadline":
            if (partsBySlash.length < 2) {
                throw new SakutaException("Bro, your deadline is missing a /by date...");
            }

            String deadlineDesc = partsBySlash[0].substring(8).trim();
            String dueDate = partsBySlash[1].trim();
            return new DeadlineCommand(deadlineDesc, dueDate);

        case "event":
            if (partsBySlash.length < 3) {
                throw new SakutaException("Bro, your event is missing /from and /to dates...");
            }

            String eventDesc = partsBySlash[0].substring(5).trim();
            String startDate = partsBySlash[1].trim();
            String endDate = partsBySlash[2].trim();

            return new EventCommand(eventDesc, startDate, endDate);

        case "mark":
            try {
                int markIndex = Integer.parseInt(partsBySpace[1]) - 1;
                return new MarkCommand(markIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new SakutaException("I think you forgot to put the task number.");
            } catch (NumberFormatException e) {
                throw new SakutaException("Use your brain and put a valid integer please...");
            }

        case "unmark":
            try {
                int unmarkIndex = Integer.parseInt(partsBySpace[1]) - 1;
                return new UnmarkCommand(unmarkIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new SakutaException("I think you forgot to put the task number.");
            } catch (NumberFormatException e) {
                throw new SakutaException("Use your brain and put a valid integer please...");
            }

        case "delete":
            try {
                int deleteIndex = Integer.parseInt(partsBySpace[1]) - 1;
                return new DeleteCommand(deleteIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new SakutaException("I think you forgot to put the task number.");
            } catch (NumberFormatException e) {
                throw new SakutaException("Use your brain and put a valid integer please...");
            }

        case "find":
            if (partsBySpace.length < 2) {
                String keyword = line.substring(4).trim();
                throw new SakutaException("You finding air is it?");
            }

            String keyword = line.substring(4).trim();
            return new FindCommand(keyword);

        default:
            // Handles any incorrect inputs
            throw new SakutaException("Huh? What are you even talking about?");
        }
    }

}
