package CommandExecution;

import Classes.Coordinates;
import Classes.Flat;
import Classes.House;
import CommandExecution.Command;
import CommandExecution.CommandManager;
import Enums.Furnish;
import Enums.Transport;
import Enums.View;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class Interactor {
    private static Scanner scanner = new Scanner(System.in);
    public static void processInput(String input) {
        String[] words = input.split(" ");
        String[] args = words.length > 1 ? new String[words.length - 1] : null;
        if (args != null) System.arraycopy(words, 1, args, 0, words.length - 1);
        System.out.println(CommandManager.exec(words[0], args));
    }

    public static String executeScript(String filename) {
        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(filename))) {
            scanner = new Scanner(inStream);
            while (scanner.hasNext()) {
                processInput(scanner.next());
            }
        } catch (FileNotFoundException exc) {
            return ("Ваш файл не найден, введите имя существующего файла");
        } catch (IOException e) {
            return (e.getMessage());
        } scanner = new Scanner(System.in);
        return "Done.\n";
    }

    public static Flat inputFlat(Flat toUpdate) {
        System.out.println("Введите название квартиры:");
        String name = scanner.next();
        if (name.contains("<") || name.contains(">")) {
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        System.out.println("Введите координату Х (число с плавающей точкой): ");
        float x = Float.parseFloat(scanner.next());
        System.out.println("Введите координату Y (целое число): ");
        Integer y = Integer.parseInt(scanner.next());
        Coordinates coordinates = new Coordinates(x, y);
        System.out.println("Введите площадь: ");
        double area = Double.parseDouble(scanner.next());
        System.out.println("Введите количество комнат: ");
        Integer numberOfRooms = Integer.parseInt(scanner.next());
        System.out.println("Введите показатель furnish (Одно из трёх: NONE, BAD, FINE): ");
        Furnish furnish = Furnish.valueOf(scanner.next());
        System.out.println("Введите показатель view (Одно из трёх: YARD, BAD, TERRIBLE): ");
        View view = View.valueOf(scanner.next());
        System.out.println("Введите показатель transport (Одно из четырёх: NONE, FEW, NORMAL, ENOUGH): ");
        Transport transport = Transport.valueOf(scanner.next().toUpperCase());
        System.out.println("Введите название дома (Пропуск = нет названия, (<> запрещены)): ");
        String housename = scanner.next();
        if (housename.contains("<") || housename.contains(">")) {
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        if (Objects.equals(housename, "")) housename = null;
        System.out.println("Введите возраст дома: ");
        int year = Integer.parseInt(scanner.next());
        System.out.println("Введите количество квартир на этаже: ");
        long numberOfFlatsOnFloor = Integer.parseInt(scanner.next());
        System.out.println("Введите количество лифтов: ");
        Integer numberOfLifts = Integer.parseInt(scanner.next());
        House house = new House(housename, year, numberOfFlatsOnFloor, numberOfLifts);
        if (toUpdate != null) {
            toUpdate.update(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
            return null;
        }
        return new Flat(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
    }
}
