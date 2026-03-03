import java.io.IOException;

/**
 * Represents a command that marks a task as completed
 * based on its index in the task list.
 */
public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new SakutaException("Are you trolling? This task doesn't exist!");
        }

        tasks.get(index).setDone(true);
        storage.storeTasks(tasks.getAllTasks());
        ui.printResponse("I have marked this task - " + tasks.get(index).getDescription());
    }
}
