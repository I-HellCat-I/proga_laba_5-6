package CommandExecution.Commands;

import CommandExecution.Command;
import CommandExecution.CommandManager;

import java.lang.reflect.InvocationTargetException;

public class CommandHelp extends Command {
    public CommandHelp(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        StringBuilder res = new StringBuilder();
        CommandManager.getCommands().forEach((s, c) -> {
            try {
                res.append(c.getMethod("toString", null)
                        .invoke(c.getConstructor(String[].class).newInstance((Object) new String[]{""})) + "\n");
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                     InstantiationException e) {
                throw new RuntimeException(e);
            }
        });
        return res.toString();
    }

    @Override
    public String toString() {
        return "help : вывести справку по доступным командам";
    }
}
