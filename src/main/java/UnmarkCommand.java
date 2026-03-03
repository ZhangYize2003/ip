import java.io.IOException;

/**
 * Represents a command that unmarks a task as completed
 * based on its index in the task list.
 */
public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new SakutaException("Are you trolling? This task doesn't exist!");
        }

        tasks.get(index).setDone(false);
        storage.storeTasks(tasks.getAllTasks());
        ui.printResponse("I have unmarked this task - " + tasks.get(index).getDescription());
    }
}
