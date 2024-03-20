package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandRemoveAt extends Command {
    public CommandRemoveAt(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        Context.getStructureStorage().removeFlatAt(Integer.parseInt(args[0]));
        return "Ok";
    }

    @Override
    public String toString() {
        return "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }
}
