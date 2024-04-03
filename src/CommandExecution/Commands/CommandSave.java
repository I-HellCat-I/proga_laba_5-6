package CommandExecution.Commands;

import CommandExecution.Command;
import CommandExecution.FileManager;

public class CommandSave extends Command {
    public CommandSave(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        return FileManager.saveCollection() ? "Ok" : "Что-то пошло не так при сохранении";
    }

    @Override
    public String toString() {
        return "save : сохранить коллекцию в файл";
    }
}
