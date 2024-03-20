package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandSort extends Command {
    public CommandSort(String[] args) {
        super(args);
    }
    @Override
    public String execute() {
        Context.getStructureStorage().sort();
        return "Ok";
    }

    @Override
    public String toString() {
        return "sort : отсортировать коллекцию в естественном порядке";
    }
}
