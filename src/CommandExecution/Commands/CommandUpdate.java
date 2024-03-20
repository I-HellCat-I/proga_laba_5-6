package CommandExecution.Commands;

import Classes.Context;
import Classes.Flat;
import CommandExecution.Command;

import java.util.Objects;

import static CommandExecution.Interactor.inputFlat;

public class CommandUpdate extends Command {
    public CommandUpdate(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        int id = Integer.parseInt(args[0]);
        Flat toUpdate = Context.getStructureStorage().getFlatById(id);
        if (toUpdate == null){
            return ("Квартиры с таким Id не найдено, ничего обновляться не будет");
        }
        inputFlat(toUpdate);
        return "Ok";
    }

    @Override
    public String toString() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
