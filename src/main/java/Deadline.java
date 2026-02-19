public class Deadline extends Task {

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate.replaceFirst("by", "by:") + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1 | " : " 0 | " ) + getDescription() + " | " + dueDate;
    }
}
