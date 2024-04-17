package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

import java.util.EmptyStackException;

public class CommandRemoveLast extends Command {

    public CommandRemoveLast(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        try {
            Context.getStructureStorage().removeLastFlat();
        } catch (EmptyStackException e){
            return "Стэк пуст, ничего не удалено";
        }
        return "Ok";
    }

    @Override
    public String toString() {
        return "remove_last : удалить последний элемент из коллекции";
    }
}
