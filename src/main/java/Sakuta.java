import java.util.Scanner;

public class Sakuta {

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskNumber = 0;

    public static void printResponse(String line) {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Sakuta: " + line);
        System.out.println("-----------------------------------------------------------");
    }

    public static void greetUser() {
        printResponse("Hello! I'm Sakuta.\n"
                + "        Is there anything I can help you with?");
    }

    public static void addTask(Task task) {
        tasks[taskNumber] = task;
        taskNumber++;
    }

    public static void main(String[] args) {
        boolean isChatting = true;

        greetUser();

        while (isChatting) {
            System.out.print("> ");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine().trim();
            String[] partsBySpace = line.split("\\s+");
            String[] partsBySlash = line.split("/");
            String command = partsBySpace[0].toLowerCase();

            switch (command) {
            case "bye":
                isChatting = false;
                break;

            case "todo":
                String toDoDesc = partsBySlash[0].substring(4).trim();
                addTask(new Todo(toDoDesc));

                printResponse("I have added — " + toDoDesc);
                break;

            case "deadline":
                String deadlineDesc = partsBySlash[0].substring(8).trim();
                String dueDate = partsBySlash[1].trim();
                addTask(new Deadline(deadlineDesc, dueDate));

                printResponse("I have added — " + deadlineDesc);
                break;

            case "event":
                String eventDesc = partsBySlash[0].substring(5).trim();
                String startDate = partsBySlash[1].trim();
                String endDate = partsBySlash[2].trim();
                addTask(new Event(eventDesc, startDate, endDate));

                printResponse("I have added — " + eventDesc);
                break;

            case "list":
                if (taskNumber == 0) {
                    printResponse("You have not added any task!");
                    break;
                }

                System.out.println("-----------------------------------------------------------");
                System.out.println("__Tasks__");
                for (int i = 0; i < taskNumber; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].toString());
                }
                System.out.println("\nLooks like you have " + taskNumber + " Tasks. Better start grinding!");
                System.out.println("-----------------------------------------------------------");
                break;

            case "mark":
                int markIndex = Integer.parseInt(partsBySpace[1]) - 1;
                if (markIndex >= taskNumber) {
                    printResponse("Are you trolling? This task doesn't exist!");
                    break;
                }
                tasks[markIndex].setDone(true);

                printResponse("I have marked this task — " + tasks[markIndex].toString());
                break;

            case "unmark":
                int unmarkIndex = Integer.parseInt(partsBySpace[1]) - 1;
                if (unmarkIndex >= taskNumber) {
                    printResponse("Are you trolling? This task doesn't exist!");
                    break;
                }
                tasks[unmarkIndex].setDone(false);

                printResponse("I have unmarked this task — " + tasks[unmarkIndex].toString());
                break;

            default:
                printResponse(line); // Echoes the user
                break;
            }
        }

        printResponse("See ya. It's nice talking to you.");
    }
}
