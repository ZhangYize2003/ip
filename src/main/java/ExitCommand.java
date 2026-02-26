import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        storage.storeTasks(tasks.getAllTasks());
        ui.printResponse("See ya. It's nice talking to you.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
