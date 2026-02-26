import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Task processFileLine(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task;

        switch (taskType) {
        case "T":
            task = new Todo(desc);
            break;
        case "D":
            task = new Deadline(desc, parts[3]);
            break;
        case "E":
            task = new Event(desc, parts[3], parts[4]);
            break;
        default:
            return null;
        }

        task.setDone(isDone);
        return task;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        Path dirPath = Paths.get("./data");
        Path path = Paths.get(filePath);

        // Create directory if there is none
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        // No file means no data to load
        if (!Files.exists(path)) {
            return loadedTasks;
        }

        File file = new File(filePath);
        Scanner s = new Scanner(file);

        while (s.hasNext()) {
            String line = s.nextLine();
            Task task = processFileLine(line);
            if (task != null) {
                loadedTasks.add(task);
            }
        }

        s.close();
        return loadedTasks;
    }

    public void storeTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toFileString());
            fw.write("\n");
        }

        fw.close();
    }
}
