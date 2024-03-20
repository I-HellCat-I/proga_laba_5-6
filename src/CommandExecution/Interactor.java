package CommandExecution;

import CommandExecution.Command;
import CommandExecution.CommandManager;
import Enums.Furnish;
import Enums.Transport;
import Enums.View;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Interactor {
    public static void processInput(String input){
        String[] words = input.split(" ");
        String[] args = words.length > 1 ? new String[words.length-1]:null;
        if (args != null) System.arraycopy(words, 1, args, 0, words.length - 1);
        CommandManager.exec(new Command(words[0], args));
    }
    public static void executeScript(String filename) {
        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(filename))) {
            Scanner sc = new Scanner(inStream);
            while (sc.hasNext()){
                processInput(sc.next());
            }
        }  catch (FileNotFoundException exc) {
            System.out.println("Ваш файл не найден, введите имя существующего файла");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
