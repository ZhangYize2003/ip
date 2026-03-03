/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    private String dueDate;

    /**
     * Class constructor that creates a deadline task.
     *
     * @param description Task description.
     * @param dueDate Deadline of the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the due date of the deadline task.
     *
     * @return Due date of the task.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the deadline task.
     *
     * @param dueDate New due date.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate.replaceFirst("by", "by:") + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1 | " : "0 | " ) + getDescription() + " | " + dueDate;
    }
}
