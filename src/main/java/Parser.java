import java.util.Scanner;

public class Parser {

    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
