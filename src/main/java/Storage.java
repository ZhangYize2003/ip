import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to the storage file (sakuta.txt).
 */
public class Storage {

    private final String filePath;

    /**
     * Creates a storage object with the given file path.
     *
     * @param filePath Path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes a line from the storage file and converts it into a Task object.
     *
     * @param line Line read from the file.
     * @return Corresponding Task object.
     */
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

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the data directory or file does not exist, it will create the directory
     * and return an empty task list.
     *
     * @return An ArrayList containing all loaded tasks.
     * @throws IOException If an error occurs while accessing the storage file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        Path dirPath = Paths.get("./data");
        Path path = Paths.get(filePath);

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

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

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks List of tasks to be stored.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void storeTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toFileString());
            fw.write("\n");
        }

        fw.close();
    }
}
