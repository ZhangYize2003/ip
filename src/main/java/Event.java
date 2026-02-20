public class Event extends Task {

    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

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
