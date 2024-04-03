package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandCountLTFurnish extends Command {
    public CommandCountLTFurnish(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        int amount = Integer.parseInt(args[0]);
        return String.valueOf(Context.getStructureStorage().countLTFurnish(amount));
    }

    @Override
    public String toString() {
        return "count_less_than_furnish furnish : вывести количество элементов, значение поля furnish которых меньше заданного";
    }
}
