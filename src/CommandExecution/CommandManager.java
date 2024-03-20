package CommandExecution;

import java.util.HashMap;
import java.util.function.Consumer;

public class CommandManager {
    static HashMap<String, Consumer<String[]>> commands = new HashMap<>();

    static {
        addCommand("help", BasicCommands::getHelp);
        addCommand("info", BasicCommands::getInfo);
        addCommand("show", BasicCommands::show);
        addCommand("add", BasicCommands::add);
        addCommand("sort", BasicCommands::sort);
        addCommand("update", BasicCommands::update);
        addCommand("remove_by_id", BasicCommands::remove_by_id);
        addCommand("save", BasicCommands::save);
        addCommand("clear", BasicCommands::clear);
        addCommand("remove_at", BasicCommands::remove_at);
        addCommand("remove_last", BasicCommands::remove_last);
        addCommand("count_less_than_furnish", BasicCommands::count_less_than_furnish);
        addCommand("remove_by_id", BasicCommands::remove_by_id);
        addCommand("execute_script", BasicCommands::execute_script);
        addCommand("sum_of_number_of_rooms", BasicCommands::sum_of_number_of_rooms);
        addCommand("print_unique_house", BasicCommands::print_unique_house);
    } // adding basic commands

    public static void addCommand(String s, Consumer<String[]> f){
        commands.put(s, f);
    }
    public static void exec(Command c){ // Когда дойдёт до 6 лабы, мб сделаю очередь команд, пока это не особо нужно
        commands.get(c.type()).accept(c.args());
    }
}
