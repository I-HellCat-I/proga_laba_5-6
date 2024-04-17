package CommandExecution.Commands;

import Classes.Context;
import Classes.Flat;
import CommandExecution.Command;

import static CommandExecution.Interactor.inputFlat;

public class CommandAdd extends Command {
    public CommandAdd(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        try{
            Flat toAdd = inputFlat(null);
            if (toAdd != null) {
                Context.getStructureStorage().addFlat(toAdd);
                return "Ok";
            }
        } catch (NumberFormatException nfe){
            return "Не рекомендую пропускать числа.";
        }
        catch (RuntimeException e){
            return (e.getMessage() + " " + e.getClass());
        }
        return ("Что-то пошло не так, но что же?");
    }

    @Override
    public String toString() {
        return "add {element} : добавить новый элемент в коллекцию";
    }
}
