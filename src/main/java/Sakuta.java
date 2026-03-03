import java.io.IOException;

/**
 * Main class for Sakuta chatbot.
 * The class handles initialisation, command processing, and interaction between the
 * chatbot UI, task storage, and task list.
 */
public class Sakuta {

    private TaskList tasks = new TaskList();
    private Storage storage = new Storage("./data/sakuta.txt");
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    /**
     * Loads tasks from the storage file (sakuta.txt) into the task list.
     * If the file cannot be loaded, an error message is shown to the user.
     */
    public void loadFromStorage() {
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.printResponse("Woah what? There was an error loading the task file.");
        }
    }

    /**
     * Runs the main execution loop of Sakuta chatbot.
     * <p>
     * The chatbot greets the user, loads tasks from storage,
     * and continuously reads commands until the command
     * "bye" signals the program to terminate.
     */
    public void run() {
        ui.greetUser();
        loadFromStorage();

        boolean isRunning = true;

        while (isRunning) {
            try {
                Command command = parser.readCommand();
                command.execute(tasks, ui, storage);
                isRunning = command.isRunning();
            } catch (SakutaException e) {
                ui.printResponse(e.getMessage());
            } catch (IOException e) {
                ui.printResponse("God dammit, the task file failed to save.");
            }
        }
    }

    /**
     * Entry point of Sakuta chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Sakuta().run();
    }
}
