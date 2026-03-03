import java.io.IOException;

/**
 * Represents a command that creates and adds a Deadline task to the
 * task list with the specified description and due date.
 */
public class DeadlineCommand extends Command{
    private String description;
    private String dueDate;

    public DeadlineCommand(String description, String dueDate) {
        this.description = description.trim();
        this.dueDate = dueDate.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (description.isEmpty()) {
            throw new SakutaException("Don't be stupid. Add a description to your task!");
        }

        tasks.add(new Deadline(description, dueDate));
        storage.storeTasks(tasks.getAllTasks());

        ui.printResponse("I have added - " + description);
    }
}
