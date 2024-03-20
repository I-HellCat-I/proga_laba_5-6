package CommandExecution.Commands;

import Classes.Context;
import Classes.House;
import CommandExecution.Command;

import java.util.ArrayList;

public class CommandPrintUniqueHouse extends Command {
    public CommandPrintUniqueHouse(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        ArrayList<House> uniques = Context.getStructureStorage().getUniqueHouse();
        return String.valueOf(uniques);
    }

    @Override
    public String toString() {
        return "print_unique_house : вывести уникальные значения поля house всех элементов в коллекции";
    }
}
