import java.util.ArrayList;

/**
 * Represents a list of tasks stored in the Sakuta chatbot.
 * Provides methods to add, remove, retrieve tasks, and check list size.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Class constructor that creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Class constructor that creates a task list with existing tasks.
     *
     * @param tasks The list of tasks to initialise the task list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskIndex Index of the task to remove.
     */
    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Retrieves a task from the list.
     *
     * @param taskIndex Index of the task.
     * @return The task at the specified index.
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns all tasks stored in the task list.
     *
     * @return ArrayList containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
