/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {

    private String startDate;
    private String endDate;

    /**
     * Class constructor that creates an event task.
     *
     * @param description Event description.
     * @param startDate Start date/time of event.
     * @param endDate End date/time of event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start date of the event task.
     *
     * @return Start date of the task.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the event task.
     *
     * @param startDate New start date.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the end date of the event task.
     *
     * @return End date of the task.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the event task.
     *
     * @param endDate New end date.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + startDate.replaceFirst("from", "from:")
                + endDate.replaceFirst("to", " to:") + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone() ? "1 | " : "0 | " ) + getDescription() + " | " + startDate + " | " + endDate;
    }
}
