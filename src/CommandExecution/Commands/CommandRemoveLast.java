package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandRemoveLast extends Command {

    public CommandRemoveLast(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        Context.getStructureStorage().removeLastFlat();
        return "Ok";
    }

    @Override
    public String toString() {
        return "remove_last : удалить последний элемент из коллекции";
    }
}
