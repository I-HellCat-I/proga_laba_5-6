package Classes;

import Enums.Furnish;
import Enums.Transport;
import Enums.View;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Interactor {
    public static final String ENVIRONMENT_VARIABLE = "FlatsFilePath";
    private static StoreStack<Flat> flats = new StoreStack<>();
    static {
        flats.load(ENVIRONMENT_VARIABLE);
    }
    private java.time.ZonedDateTime initDate = java.time.ZonedDateTime.now();
    public void processInput(String input){
        String[] words = input.split(" ");
        switch (words[0]){
            case "help" -> System.out.println("help : вывести справку по доступным командам\n" +
                    "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                    "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                    "add {element} : добавить новый элемент в коллекцию\n" +
                    "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                    "remove_by_id id : удалить элемент из коллекции по его id\n" +
                    "clear : очистить коллекцию\n" +
                    "save : сохранить коллекцию в файл\n" +
                    "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                    "exit : завершить программу (без сохранения в файл)\n" +
                    "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)\n" +
                    "remove_last : удалить последний элемент из коллекции\n" +
                    "sort : отсортировать коллекцию в естественном порядке\n" +
                    "sum_of_number_of_rooms : вывести сумму значений поля numberOfRooms для всех элементов коллекции\n" +
                    "count_less_than_furnish furnish : вывести количество элементов, значение поля furnish которых меньше заданного\n" +
                    "print_unique_house : вывести уникальные значения поля house всех элементов в коллекции");
            case "info" -> System.out.println(flats.getClass() + " " + initDate + " " + flats.size());
            case "show" -> flats.forEach(System.out::println);
            case "add" -> {
                Flat toAdd = inputFlat(null);
                if (toAdd != null) {
                    flats.add(toAdd);
                } else {
                    System.out.println("Что-то пошло не так, но что же?");
                }
            }
            case "update" -> {
                int id = Integer.parseInt(words[1]);
                for (Flat flat : flats) {
                    if (Objects.equals(flat.getId(), id)){
                        inputFlat(flat);
                        break;
                    }
                }
                System.out.println("Квартиры с таким Id не найдено, ничего обновляться не будет");
            }
            case "remove_by_id" -> {
                Integer id;
                try {
                    id = Integer.parseInt(words[1]);
                }
                catch (NumberFormatException e) {
                    System.out.println("Неверный формат Id (Не целое число)");
                    break;
                } removeFlat(id);
            }
            case "clear" -> {
                Flat.clearIndicator();
                flats.clear();
            }
            case "save" -> {
                String fn = "";
                if (words.length == 1){
                    fn = System.getenv(ENVIRONMENT_VARIABLE);
                }
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < words.length; i++){
                    builder.append(words[i]);
                    if (i < words.length - 1) builder.append(" ");
                }
                fn = (!builder.isEmpty() ? builder.toString() : fn);
                try {flats.save(fn);}
                catch (FileNotFoundException e){
                    System.out.println(e);
                }
            }
            case "execute_script" -> executeScript(words[1]);
            case "remove_at" -> flats.remove(Integer.parseInt(words[1])).markForDeletion();
            case "remove_last" -> flats.pop().markForDeletion();
            case "sort" -> flats.sort();
            case "sum_of_number_of_rooms" -> System.out.println(countRooms());
            case "count_less_than_furnish" -> {
                int am;
                try {
                    am = Integer.parseInt(words[1]);
                }
                catch (NumberFormatException e) {
                    System.out.println("Неверный формат Id (Не целое число)");
                    break;
                }
                System.out.println(countLTFurnish(am));
            }
            case "print_unique_house" -> System.out.println();
            default -> System.out.println("Неизвестный ввод");
        }

    }
    private Flat inputFlat(Flat toUpdate){
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
        System.out.println("Введите название квартиры (<> запрещены): ");
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
    private void removeFlat(Integer id){
        for (Flat flat : flats) {
            if (Objects.equals(flat.getId(), id)){
                flat.markForDeletion();
                flats.remove(flat);
                break;
            }
        }
        System.out.println("Квартиры с таким Id не найдено, ничего не удалено");
    }
    private void executeScript(String filename) {
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

    private int countRooms(){
        return flats.stream().mapToInt(Flat::getNumberOfRooms).sum();
    }
    private int countLTFurnish(int amount){
        int res = 0;
        for (Flat f: flats){
            if (f.getFurnish().getAmount() < amount) res++;
        }
        return res;
    }
}
