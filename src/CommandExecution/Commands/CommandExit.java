package CommandExecution.Commands;

import CommandExecution.Command;

import static java.lang.System.exit;

public class CommandExit extends Command {
    public CommandExit(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        exit(1);
        return "Ok";
    }

    @Override
    public String toString() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
