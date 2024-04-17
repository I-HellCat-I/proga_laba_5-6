package CommandExecution.Commands;

import Classes.Context;
import CommandExecution.Command;

import static java.lang.System.exit;

public class CommandExit extends Command {
    public CommandExit(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        Context.setExitCommandUsed();
        exit(1);
        return "Ok";
    }

    @Override
    public String toString() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
