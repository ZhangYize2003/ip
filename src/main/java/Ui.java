import java.util.ArrayList;

/**
 * Handles user interface interactions for Sakuta chatbot.
 * Responsible for displaying messages, greetings, and task lists.
 */
public class Ui {

    /**
     * Class constructor that creates a new Ui object.
     */
    public Ui() {
    }

    /**
     * Prints out a formatted response.
     *
     * @param line The message to display to the user.
     */
    public void printResponse(String line) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Sakuta: " + line);
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Displays a greeting when the chatbot starts.
     */
    public void greetUser() {
        printResponse("Hello! I'm Sakuta.\n"
                + "        Is there anything I can help you with?");
    }

    /**
     * Displays the list of all tasks stored in the task list.
     *
     * @param tasks The list of tasks to display.
     */
    public void listTasks(ArrayList<Task> tasks) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("__Tasks__");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
        System.out.println("\nLooks like you have " + tasks.size() + " Tasks. Better start grinding!");
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Displays tasks that match the user's specific search keyword.
     *
     * @param tasks The list of matched tasks.
     */
    public void listMatchedTasks(ArrayList<Task> tasks) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("__Tasks__");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
        System.out.println("\nLooks like you have " + tasks.size() + " matching tasks.");
        System.out.println("-----------------------------------------------------------");
    }
}
