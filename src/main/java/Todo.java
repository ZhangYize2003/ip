/**
 * Represents a todo task without any time constraint.
 */
public class Todo extends Task {

    /**
     * Class Constructor that creates a todo task.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone() ? "1 | " : "0 | " ) + getDescription();
    }
}