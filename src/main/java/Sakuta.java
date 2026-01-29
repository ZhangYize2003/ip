import java.util.Scanner;

public class Sakuta {
    public static void printResponse(String line) {
        System.out.println("-------------------------------------------------");
        System.out.println("Sakuta: " + line);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        boolean isChatting = true;
        Task[] tasks = new Task[100];
        int taskNumber = 0;

        printResponse("Hello! I'm Sakuta.\n"
                + "        Is there something you want from me?");

        while (isChatting) {
            System.out.print("> ");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equalsIgnoreCase("bye")) {
                isChatting = false;
            } else if (line.equalsIgnoreCase("list")) {
                if (taskNumber == 0) {
                    printResponse("You have not added any task!");
                    continue;
                }

                System.out.println("-------------------------------------------------");
                System.out.println("__Tasks__");
                for (int i = 0; i < taskNumber; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].getDescription());
                }
                System.out.println("-------------------------------------------------");
            } else {
                Task task = new Task(line);
                tasks[taskNumber] = task;
                taskNumber++;

                printResponse("I have added — " + line);
            }
        }

        printResponse("See ya. It's nice talking to you.");
    }
}
