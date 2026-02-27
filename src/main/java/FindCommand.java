import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException {
        if (keyword.isEmpty()) {
            ui.printResponse("You finding air is it?");
        }

        ArrayList<Task> allTasks = tasks.getAllTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < allTasks.size(); i++) {
            Task task = allTasks.get(i);
            String formatedTask = task.getDescription().toLowerCase();
            if (formatedTask.contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.printResponse("There are no such tasks.");
        } else {
            ui.listMatchedTasks(matchingTasks);
        }
    }
}