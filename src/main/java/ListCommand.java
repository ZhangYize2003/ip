import java.io.IOException;

/**
 * Represents a command that displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (tasks.isEmpty()) {
            ui.printResponse("You have not added any task!");
        }

        ui.listTasks(tasks.getAllTasks());
    }
}
