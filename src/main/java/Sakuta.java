import java.util.Scanner;

public class Sakuta {
    public static void printLine(String line) {
        System.out.println("-------------------------------------------------");
        System.out.println("Sakuta: " + line);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        Boolean isChatting = true;

        printLine("Hello! I'm Sakuta.\n"
                + "        Is there something you want from me?");

        while (isChatting) {
            System.out.print("> ");
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if (line.equals("bye")) {
                isChatting = false;
            } else {
                printLine(line);
            }
        }

        printLine("See ya. It's nice talking to you.");
    }
}
