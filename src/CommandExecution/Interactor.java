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

public class Interactor {
    public static void processInput(String input){
        String[] words = input.split(" ");
        String[] args = words.length > 1 ? new String[words.length-1]:null;
        if (args != null) System.arraycopy(words, 1, args, 0, words.length - 1);
        System.out.println(CommandManager.exec(words[0], args));
    }
    public static String executeScript(String filename) {
        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(filename))) {
            Scanner sc = new Scanner(inStream);
            while (sc.hasNext()){
                processInput(sc.next());
            }
        }  catch (FileNotFoundException exc) {
            return ("Ваш файл не найден, введите имя существующего файла");
        } catch (IOException e) {
            return (e.getMessage());
        }
        return "Done.\n";
    }
    public static Flat inputFlat(Flat toUpdate){
        String name; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates;
        float x;
        Integer y;
        double area; //Значение поля должно быть больше 0
        Integer numberOfRooms; //Значение поля должно быть больше 0
        Furnish furnish; //Поле не может быть null
        View view; //Поле может быть null
        Transport transport; //Поле не может быть null
        House house;
        String housename; //Поле может быть null
        int year; //Максимальное значение поля: 630, Значение поля должно быть больше 0
        long numberOfFlatsOnFloor; //Значение поля должно быть больше 0
        Integer numberOfLifts;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название квартиры:");
        name = scanner.next();
        if (name.contains("<") || name.contains(">")){
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        System.out.println("Введите координату Х (число с плавающей точкой): ");
        x = Float.parseFloat(scanner.next());
        System.out.println("Введите координату Y (целое число): ");
        y = Integer.parseInt(scanner.next());
        coordinates = new Coordinates(x, y);
        System.out.println("Введите площадь: ");
        area = Double.parseDouble(scanner.next());
        System.out.println("Введите количество комнат: ");
        numberOfRooms = Integer.parseInt(scanner.next());
        System.out.println("Введите показатель furnish (Одно из трёх: NONE, BAD, FINE): ");
        furnish = Furnish.valueOf(scanner.next());
        System.out.println("Введите показатель view (Одно из трёх: YARD, BAD, TERRIBLE): ");
        view = View.valueOf(scanner.next());
        System.out.println("Введите показатель transport (Одно из четырёх: NONE, FEW, NORMAL, ENOUGH): ");
        transport = Transport.valueOf(scanner.next().toUpperCase());
        System.out.println("Введите название дома (Пропуск = нет названия, (<> запрещены)): ");
        housename = scanner.next();
        if (housename.contains("<") || housename.contains(">")){
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        if (Objects.equals(housename, "")) housename = null;
        System.out.println("Введите возраст дома: ");
        year = Integer.parseInt(scanner.next());
        System.out.println("Введите количество квартир на этаже: ");
        numberOfFlatsOnFloor = Integer.parseInt(scanner.next());
        System.out.println("Введите количество лифтов: ");
        numberOfLifts = Integer.parseInt(scanner.next());
        house = new House(housename, year, numberOfFlatsOnFloor, numberOfLifts);
        if (toUpdate != null){
            toUpdate.update(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
            return null;
        }
        return new Flat(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
    }
}
