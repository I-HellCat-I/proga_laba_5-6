package CommandExecution.Commands;

import CommandExecution.Command;
import Classes.FileManager;

import java.util.Arrays;

public class CommandSave extends Command {
    public CommandSave(String[] args) {
        super(args);
    }

    @Override
    public String execute() {
        try {
            return FileManager.saveCollection() ? "Ok" : "Что-то пошло не так при сохранении";
        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return "dsad";
        }
    }

    @Override
    public String toString() {
        return "save : сохранить коллекцию в файл";
    }
}
