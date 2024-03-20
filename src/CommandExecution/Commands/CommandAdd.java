package CommandExecution.Commands;

import Classes.Context;
import Classes.Flat;
import CommandExecution.Command;

import static CommandExecution.BasicCommands.inputFlat;

public class CommandAdd extends Command {
    public CommandAdd(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        Flat toAdd = inputFlat(null);
        if (toAdd != null) {
            Context.getStructureStorage().addFlat(toAdd);
            return "Ok";
        }
        return ("Что-то пошло не так, но что же?");
    }
    @Override
    public String toString() {
        return "add {element} : добавить новый элемент в коллекцию\n";
    }
}
