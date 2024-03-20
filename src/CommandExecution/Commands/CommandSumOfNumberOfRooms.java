package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandSumOfNumberOfRooms extends Command {
    public CommandSumOfNumberOfRooms(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        return String.valueOf(Context.getStructureStorage().getNumberOfRoomsSum());
    }

    @Override
    public String toString() {
        return "sum_of_number_of_rooms : вывести сумму значений поля numberOfRooms для всех элементов коллекции";
    }
}
