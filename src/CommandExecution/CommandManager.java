package CommandExecution;

import CommandExecution.Commands.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

public class CommandManager {
    static HashMap<String, Class<? extends Command>> commands = new HashMap<>();

    static {
        addCommand("help", CommandHelp.class);
        addCommand("info", CommandInfo.class);
        addCommand("show", CommandShow.class);
        addCommand("add", CommandAdd.class);
        addCommand("sort", CommandSort.class);
        addCommand("update", CommandUpdate.class);
        addCommand("remove_by_id", CommandRemoveById.class);
        addCommand("save", CommandSave.class);
        addCommand("clear", CommandClear.class);
        addCommand("remove_at", CommandRemoveAt.class);
        addCommand("remove_last", CommandRemoveLast.class);
        addCommand("count_less_than_furnish", CommandCountLTFurnish.class);
        addCommand("remove_by_id", CommandRemoveById.class);
        addCommand("execute_script", CommandExecuteScript.class);
        addCommand("sum_of_number_of_rooms", CommandSumOfNumberOfRooms.class);
        addCommand("print_unique_house", CommandPrintUniqueHouse.class);
        addCommand("exit", CommandExit.class);
    } // Базовые команды

    public static void addCommand(String s, Class<? extends Command> f) {
        commands.put(s, f);
    }

    public static String exec(String type, String[] args) { // Когда дойдёт до 6 лабы, мб сделаю очередь команд, пока это не особо нужно
        try {
            return (commands.get(type).getConstructor(String[].class).newInstance((Object) (args == null ? new String[]{""} : args))).execute();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, Class<? extends Command>> getCommands() {
        return commands;
    }
}
