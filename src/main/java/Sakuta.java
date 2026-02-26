import java.io.IOException;

public class Sakuta {

    private TaskList tasks = new TaskList();
    private Storage storage = new Storage("./data/sakuta.txt");
    private Ui ui = new Ui();
    private Parser parser = new Parser();

    public void loadFromStorage() {
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.printResponse("Woah what? There was an error loading the task file.");
        }
    }

    /**
     * Running loop of the chatbot Sakuta
     *
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

    public static void main(String[] args) {
        new Sakuta().run();
    }
}
