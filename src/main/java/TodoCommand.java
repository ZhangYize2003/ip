import java.io.IOException;

/**
 * Represents a command that creates and adds a Todo task to the
 * task list with the specified description without time constraint.
 */
public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (description.isEmpty()) {
            throw new SakutaException("Don't be stupid. Add a description to your task!");
        }

        tasks.add(new Todo(description));
        storage.storeTasks(tasks.getAllTasks());

        ui.printResponse("I have added - " + description);
    }
}
