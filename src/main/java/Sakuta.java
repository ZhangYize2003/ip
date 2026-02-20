import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sakuta {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/sakuta.txt";

    /**
     * Prints out a formatted response
     *
     * @param line The message to display to the user
     */
    public static void printResponse(String line) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Sakuta: " + line);
        System.out.println("-----------------------------------------------------------");
    }

    /**
     * Displays a greeting when the chatbot starts
     */
    public static void greetUser() {
        printResponse("Hello! I'm Sakuta.\n"
                + "        Is there anything I can help you with?");
    }

    /**
     * Adds a task to the task list
     *
     * @param task The task object that the user inputted
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    public static void loadFromFile() {
        try {
            Path dirPath = Paths.get("./data");
            Path filePath = Paths.get(FILE_PATH);

            // Create directory if there is none
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }

            // No file means no data to load
            if (!Files.exists(filePath)) {
                return;
            }

            File file = new File(FILE_PATH);
            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                String line = s.nextLine();
                processFileLine(line);
            }

            s.close();
        } catch (IOException e) {
            printResponse("Woah what? There was an error loading the file.");
        }
    }

    public static void processFileLine(String line) {
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
            return;
        }

        task.setDone(isDone);
        addTask(task);
    }

    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileString());
                fw.write("\n");
            }

            fw.close();
        } catch(IOException e){
                printResponse("God dammit, the file failed to save.");
        }
    }

    /**
     * Checks if the task description is empty
     *
     * @param description The description string of the task object to check
     * @return true if description is empty, false otherwise
     */
    public static boolean isDescriptionEmpty(String description) {
        return description.isEmpty();
    }

    /**
     * Main loop of the chatbot Sakuta
     *
     * @param args The user input from CLI
     */
    public static void main(String[] args) {
        boolean isChatting = true;

        greetUser();
        loadFromFile();

        while (isChatting) {
            try {
                System.out.print("> ");
                Scanner in = new Scanner(System.in);
                String line = in.nextLine().trim();
                String[] partsBySpace = line.split("\\s+");
                String[] partsBySlash = line.split("/");
                String command = partsBySpace[0].toLowerCase();
                int numOfTasks = tasks.size();

                switch (command) {
                case "bye":
                    saveToFile();
                    isChatting = false;
                    break;

                case "todo":
                    String toDoDesc = partsBySlash[0].substring(4).trim();
                    if (isDescriptionEmpty(toDoDesc)) {
                        throw new SakutaException("Don't be stupid. Add a description to your task!");
                    }

                    addTask(new Todo(toDoDesc));
                    saveToFile();

                    printResponse("I have added — " + toDoDesc);
                    break;

                case "deadline":
                    String deadlineDesc = partsBySlash[0].substring(8).trim();
                    if (isDescriptionEmpty(deadlineDesc)) {
                        throw new SakutaException("Don't be stupid. Add a description to your task!");
                    }

                    if (partsBySlash.length < 2) {
                        throw new SakutaException("Bro, your deadline is missing a /by date...");
                    }
                    String dueDate = partsBySlash[1].trim();

                    addTask(new Deadline(deadlineDesc, dueDate));
                    saveToFile();

                    printResponse("I have added — " + deadlineDesc);
                    break;

                case "event":
                    String eventDesc = partsBySlash[0].substring(5).trim();
                    if (isDescriptionEmpty(eventDesc)) {
                        throw new SakutaException("Don't be stupid. Add a description to your task!");
                    }

                    if (partsBySlash.length < 3) {
                        throw new SakutaException("Bro, your event is missing /from and /to dates...");
                    }
                    String startDate = partsBySlash[1].trim();
                    String endDate = partsBySlash[2].trim();

                    addTask(new Event(eventDesc, startDate, endDate));
                    saveToFile();

                    printResponse("I have added — " + eventDesc);
                    break;

                case "list":
                    if (numOfTasks == 0) {
                        printResponse("You have not added any task!");
                        break;
                    }

                    System.out.println("-----------------------------------------------------------");
                    System.out.println("__Tasks__");
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                    System.out.println("\nLooks like you have " + numOfTasks + " Tasks. Better start grinding!");
                    System.out.println("-----------------------------------------------------------");
                    break;

                case "mark":
                    int markIndex;
                    try {
                        markIndex = Integer.parseInt(partsBySpace[1]) - 1;
                    } catch (IndexOutOfBoundsException e) {
                        throw new SakutaException("I think you forgot to put the task number.");
                    } catch (NumberFormatException e) {
                        throw new SakutaException("Use your brain and put a valid integer please...");
                    }

                    if (markIndex < 0 || markIndex >= numOfTasks) {
                        throw new SakutaException("Are you trolling? This task doesn't exist!");
                    }

                    tasks.get(markIndex).setDone(true);
                    saveToFile();

                    printResponse("I have marked this task — " + tasks.get(markIndex).toString());
                    break;

                case "unmark":
                    int unmarkIndex;
                    try {
                        unmarkIndex = Integer.parseInt(partsBySpace[1]) - 1;
                    } catch (IndexOutOfBoundsException e) {
                        throw new SakutaException("I think you forgot to put the task number.");
                    } catch (NumberFormatException e) {
                        throw new SakutaException("Use your brain and put a valid integer please...");
                    }

                    if (unmarkIndex < 0 || unmarkIndex >= numOfTasks) {
                        throw new SakutaException("Are you trolling? This task doesn't exist!");
                    }

                    tasks.get(unmarkIndex).setDone(false);
                    saveToFile();

                    printResponse("I have unmarked this task — " + tasks.get(unmarkIndex).toString() + "" +
                            "\nYou now have " + numOfTasks + " tasks left");
                    break;

                case "delete":
                    int deleteIndex;
                    try {
                        deleteIndex = Integer.parseInt(partsBySpace[1]) - 1;

                    } catch (IndexOutOfBoundsException e) {
                        throw new SakutaException("I think you forgot to put the task number.");
                    } catch (NumberFormatException e) {
                        throw new SakutaException("Use your brain and put a valid integer please...");
                    }

                    if (deleteIndex < 0 || deleteIndex >= numOfTasks) {
                        throw new SakutaException("Are you trolling? This task doesn't exist!");
                    }

                    String taskDesc = tasks.get(deleteIndex).toString();
                    tasks.remove(deleteIndex);
                    saveToFile();

                    printResponse("I have deleted this task — " + taskDesc);
                    break;

                default:
                    // Handles any incorrect inputs
                    printResponse("Huh? What are you even talking about?");
                    break;
                }
            } catch (SakutaException e) {
                printResponse(e.getMessage());
            }
        }

        printResponse("See ya. It's nice talking to you.");
    }
}
