import java.io.IOException;

public class EventCommand extends Command{
    private String description;
    private String startDate;
    private String endDate;

    public EventCommand(String description, String startDate, String endDate) {
        this.description = description.trim();
        this.startDate = startDate.trim();
        this.endDate =  endDate.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (description.isEmpty()) {
            throw new SakutaException("Don't be stupid. Add a description to your task!");
        }

        tasks.add(new Event(description, startDate, endDate));
        storage.storeTasks(tasks.getAllTasks());

        ui.printResponse("I have added — " + description);
    }
}
