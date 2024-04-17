package CommandExecution;

import Classes.Context;
import Classes.Coordinates;
import Classes.Flat;
import Classes.House;
import Enums.Furnish;
import Enums.Transport;
import Enums.View;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

public class Interactor {
    /**
     * Класс, отвечающий за обработку пользовательского ввода
     */
    private static Scanner scanner = new Scanner(System.in);
    private static int nowRecursion = 0;
    private static Stack<Scanner> scannerStack = new Stack<>();

    public static void masterProcessInput(String input) { // Костыль, который убирает проблемы с рекурсией
        try {
            processInput(input);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            scanner = scannerStack.get(0);
            scannerStack.clear();
            nowRecursion = 0;
        }
    }

    static void processInput(String input) { // Базовый обработчик ввода, скидывает обработанную команду менеждеру команд
        String[] words = input.trim().split(" ");
        String[] args = words.length > 1 ? new String[words.length - 1] : null;
        if (args != null) System.arraycopy(words, 1, args, 0, words.length - 1);
        try {
            System.out.println(CommandManager.exec(words[0], args));
        } catch (NullPointerException e) {
            System.out.println("Такой команды нет");
        }
    }

    public static String executeScript(String filename) {  // Читает и обрабатывает скрипты.
        if (nowRecursion > Context.getMaxRecursionDepth()) {
            throw new RuntimeException("Вы превысили максимальную допустимую глубину рекурсии: " + Context.getMaxRecursionDepth());
        }
        nowRecursion++;
        scannerStack.add(scanner);
        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(filename))) {
            scanner = new Scanner(inStream);
            while (scanner.hasNextLine()) {
                processInput(scanner.nextLine());
            }
        } catch (FileNotFoundException exc) {
            return ("Ваш файл не найден, введите имя существующего файла");
        } catch (IOException e) {
            return (e.getMessage());
        }
        nowRecursion--;
        scanner = scannerStack.pop();
        return "Done.\n";
    }

    public static Flat inputFlat(Flat toUpdate) {
        String name = inputFoolProof("Введите название квартиры:", Flat::checkName, (a) -> {
            return a;
        }, false);
        float x = inputFoolProof("Введите координату Х (число с плавающей точкой): ", Coordinates::checkX, Float::parseFloat, true);
        Integer y = inputFoolProof("Введите координату Y (целое число): ", Coordinates::checkY, Integer::parseInt, true);
        Coordinates coordinates = new Coordinates(x, y);
        double area = inputFoolProof("Введите площадь (число с плавающей точкой): ", Flat::checkArea, Double::parseDouble, true);
        Integer numberOfRooms = inputFoolProof("Введите количество комнат (целое число): ", Flat::checkNumberOfRooms, Integer::parseInt, true);
        System.out.println("Введите показатель furnish (Одно из трёх: NONE, BAD, FINE): ");
        Furnish furnish = inputEnumValue(Furnish.class);
        System.out.println("Введите показатель view (Одно из трёх: YARD, BAD, TERRIBLE): ");
        View view = inputEnumValue(View.class);
        System.out.println("Введите показатель transport (Одно из четырёх: NONE, FEW, NORMAL, ENOUGH): ");
        Transport transport = inputEnumValue(Transport.class);
        String housename = inputFoolProof("Введите название дома:", House::checkName, (a) -> {
            return a;
        }, false);
        int year = inputFoolProof("Введите возраст дома (целое число): ", House::checkYear, Integer::parseInt, true);
        long numberOfFlatsOnFloor = inputFoolProof("Введите количество квартир на этаже (целое число): ", House::checkNumberOfFlatsOnFloor, Long::parseLong, true);
        Integer numberOfLifts = inputFoolProof("Введите количество лифтов (целое число): ", House::checkNumberOfLifts, Integer::parseInt, true);
        House house = new House(housename, year, numberOfFlatsOnFloor, numberOfLifts);
        if (toUpdate != null) {
            toUpdate.update(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
            return null;
        }
        return new Flat(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
    }

    public static <T extends Enum<T>> T inputEnumValue(Class<T> enumClass) { // Отдельный ввод для Enum'ов, т.к их проверка немного отличается
        while (true) {
            String toCheck = scanner.nextLine().toUpperCase();
            T[] constants = enumClass.getEnumConstants();
            try {
                return T.valueOf(enumClass, toCheck);
            } catch (IllegalArgumentException e) {
                try {
                    return constants[Integer.parseInt(toCheck)];
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e1) {
                    System.out.println("То, что вы ввели - не " + enumClass.getName());
                }
            }
        }
    }

    public static <T> T inputFoolProof(String inputPrompt, Consumer<T> checker, Function<String, T> converter, boolean commaToDot) {
        // Ввод и проверка правильности значения
        int errorInRowCounter = 0;
        System.out.println(inputPrompt);
        T answer = null;
        if (nowRecursion != 0) {
            try {
                String buffer = scanner.nextLine();
                if (commaToDot) {
                    buffer = buffer.replace(',', '.');
                }
                if (!Objects.equals(buffer, "")) {
                    answer = converter.apply(buffer);
                } else {
                    answer = null;
                }
                checker.accept(answer);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                System.err.println("Вы ввели что-то не так. Попробуйте ещё раз =)");
            }
            return answer;
        }
        while (true) {
            try {
                String buffer = scanner.nextLine();
                if (commaToDot) {
                    buffer = buffer.replace(',', '.');
                }
                if (!Objects.equals(buffer, "")) {
                    answer = converter.apply(buffer);
                } else {
                    answer = null;
                }
                checker.accept(answer);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                System.err.println("Вы ввели что-то не так. Попробуйте ещё раз =)");
                errorInRowCounter++;
                if (errorInRowCounter > 5) {
                    System.err.println("Тут явно что-то не так.... Но что же????");
                }
                continue;
            }
            return answer;
        }
    }
}
