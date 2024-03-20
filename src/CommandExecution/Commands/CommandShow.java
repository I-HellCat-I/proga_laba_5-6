package CommandExecution.Commands;

import Classes.Context;
import Classes.Flat;
import CommandExecution.Command;

public class CommandShow extends Command {
    public CommandShow(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        StringBuilder ans = new StringBuilder();
        Context.getStructureStorage().getCollection().forEach(ans::append);
        return ans.toString();
    }
    @Override
    public String toString() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
