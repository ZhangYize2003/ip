public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void printTask() {
        if (this.isDone) {
            System.out.println("[✔] " + this.getDescription());
        } else {
            System.out.println("[ ] " + this.getDescription());
        }

    }
}


