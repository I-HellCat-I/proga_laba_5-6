package CommandExecution.Commands;

import CommandExecution.Command;
import CommandExecution.Interactor;

public class CommandExecuteScript extends Command {
    public CommandExecuteScript(String[] args) {
        super(args);
    }
    @Override
    public String execute() {
        return Interactor.executeScript(args[0]);
    }
    @Override
    public String toString() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
