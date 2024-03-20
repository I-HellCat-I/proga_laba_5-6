package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandRemoveById extends Command {
    public CommandRemoveById(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        Integer id;
        try {
            id = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            return ("Неверный формат Id (Не целое число)");
        } if (!Context.getStructureStorage().removeFlatById(id)) return ("Квартиры с таким Id не найдено, ничего не удалено");
        return "Ok";
    }

    @Override
    public String toString() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}
