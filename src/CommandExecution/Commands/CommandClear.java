package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandClear extends Command {
    public CommandClear(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        Context.getStructureStorage().clearCollection();
        return "Ok";
    }

    @Override
    public String toString() {
        return "clear : очистить коллекцию";
    }
}
