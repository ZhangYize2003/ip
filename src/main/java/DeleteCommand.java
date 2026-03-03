import java.io.IOException;

/**
 * Represents a command that deletes a task from the task list.
 * The task to be deleted is identified by its index in the list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new SakutaException("Are you trolling? This task doesn't exist!");
        }

        String deletedTaskDesc = tasks.get(index).toString();
        tasks.remove(index);
        storage.storeTasks(tasks.getAllTasks());
        ui.printResponse("I have deleted this task - " + deletedTaskDesc + "\n" +
                "\nYou now have " + tasks.size() + " tasks left");
    }
}
