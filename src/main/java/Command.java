import java.io.IOException;

/**
 * Represents an abstract command that can be executed by Sakuta chatbot.
 * All command types extend this class.
 */
public abstract class Command {

    /**
     * Executes the given command.
     *
     * @param tasks The list of tasks stored.
     * @param ui The user interface used to display messages.
     * @param storage The storage handler responsible for loading and saving tasks.
     * @throws SakutaException If an error occurs during execution.
     * @throws IOException If a file operation fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SakutaException, IOException;

    /**
     * Determines whether the program should continue running.
     *
     * @return true if the program should continue, false if it should terminate (in ExitCommand).
     */
    public boolean isRunning() {
        return true;
    }
}
