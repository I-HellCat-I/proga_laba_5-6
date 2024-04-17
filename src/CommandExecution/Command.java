package CommandExecution;

/**
 * База для всех команд.
 */
public abstract class Command {

    protected String[] args;

    public Command(String[] args) {
        this.args = args;
    }

    public abstract String execute();

    public abstract String toString();
}
