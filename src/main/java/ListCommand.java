import java.io.IOException;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (tasks.size() == 0) {
            ui.printResponse("You have not added any task!");
        }

        ui.listTasks(tasks.getAllTasks());
    }
}
