package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

public class CommandInfo extends Command {
    public CommandInfo(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        return (Context.getStructureStorage().getClass() + " " + Context.getInitDate() + " " + Context.getStructureStorage().getSize());
    }

    @Override
    public String toString() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
