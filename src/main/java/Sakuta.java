import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Sakuta {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storage = new Storage("./data/sakuta.txt");

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

    public static void loadFromStorage() {
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            printResponse("Woah what? There was an error loading the file.");
        }
    }

    public static void saveToStorage() {
        try {
            storage.storeTasks(tasks);
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
        loadFromStorage();

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
                    saveToStorage();
                    isChatting = false;
                    break;

                case "todo":
                    String toDoDesc = partsBySlash[0].substring(4).trim();
                    if (isDescriptionEmpty(toDoDesc)) {
                        throw new SakutaException("Don't be stupid. Add a description to your task!");
                    }

                    tasks.add(new Todo(toDoDesc));
                    saveToStorage();

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

                    tasks.add(new Deadline(deadlineDesc, dueDate));
                    saveToStorage();

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

                    tasks.add(new Event(eventDesc, startDate, endDate));
                    saveToStorage();

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
                    saveToStorage();

                    printResponse("I have marked this task - " + tasks.get(markIndex).toString());
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
                    saveToStorage();

                    printResponse("I have unmarked this task - " + tasks.get(unmarkIndex).toString() + "\n" +
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
                    saveToStorage();

                    printResponse("I have deleted this task - " + taskDesc);
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
