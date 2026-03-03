/**
 * Represents a generic task with a description and its completion status.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task description.
     *
     * @param description New task description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param done Completion status.
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Formatted task string with completion status.
     */
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.getDescription();
        } else {
            return "[ ] " + this.getDescription();
        }
    }

    /**
     * Converts the task into a format suitable for saving to storage file.
     *
     * @return String representation for file storage.
     */
    public String toFileString() {
        return "";
    }
}


