import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }

    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty(Task task) {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
