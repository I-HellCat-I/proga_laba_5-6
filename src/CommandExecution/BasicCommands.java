package CommandExecution;

import Classes.*;
import Enums.Furnish;
import Enums.Transport;
import Enums.View;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class BasicCommands {
    static StructureStorage flats = Context.getStructureStorage();
    public static void getHelp(String[] s){
        System.out.println("help : вывести справку по доступным командам\n" +
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
    }
    public static void getInfo(String[] s){
        System.out.println(flats.getClass() + " " + Context.getInitDate() + " " + flats.getCollection().size());
    }
    public static void show(String[] s){
        flats.getCollection().forEach(System.out::println);
    }
    public static void add(String[] s){
        Flat toAdd = inputFlat(null);
        if (toAdd != null) {
            flats.getCollection().add(toAdd);
        } else {
            System.out.println("Что-то пошло не так, но что же?");
        }
    }
    public static void update(String[] s){
        int id = Integer.parseInt(s[1]);
        for (Flat flat : flats.getCollection()) {
            if (Objects.equals(flat.getId(), id)){
                inputFlat(flat);
                break;
            }
        }
        System.out.println("Квартиры с таким Id не найдено, ничего обновляться не будет");
    }

    public static void remove_by_id(String[] s){
        Integer id;
        try {
            id = Integer.parseInt(s[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Неверный формат Id (Не целое число)");
            return;
        } removeFlat(id);
    }
    public static void clear(String[] s){
        Flat.clearIndicator();
        flats.getCollection().clear();
    }
    public static void save(String[] s){
        String fn = "";
        if (s.length == 1){
            fn = System.getenv(Context.getPathVar());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < s.length; i++){
            builder.append(s[i]);
            if (i < s.length - 1) builder.append(" ");
        }
        fn = (!builder.isEmpty() ? builder.toString() : fn);
        try {flats.save(fn);}
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }
    public static void execute_script(String[] s){
        Interactor.executeScript(s[1]);
    }
    public static void remove_at(String[] s){
        flats.getCollection().remove(Integer.parseInt(s[1])).markForDeletion();
    }
    public static void remove_last(String[] s){
        flats.getCollection().pop().markForDeletion();
    }
    public static void sum_of_number_of_rooms(String[] s){
        flats.sort();
    }
    public static void count_less_than_furnish(String[] s){
        System.out.println(countRooms());
    }
    public static void print_unique_house(String[] s){
        int am;
        try {
            am = Integer.parseInt(s[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Неверный формат Id (Не целое число)");
            return;
        }
        System.out.println(countLTFurnish(am));
    }
    private static Flat inputFlat(Flat toUpdate){
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

        System.out.println();
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
    private static void removeFlat(Integer id){
        for (Flat flat : flats.getCollection()) {
            if (Objects.equals(flat.getId(), id)){
                flat.markForDeletion();
                flats.getCollection().remove(flat);
                break;
            }
        }
        System.out.println("Квартиры с таким Id не найдено, ничего не удалено");
    }

    private static int countRooms(){
        return flats.getCollection().stream().mapToInt(Flat::getNumberOfRooms).sum();
    }
    private static int countLTFurnish(int amount){
        int res = 0;
        for (Flat f: flats.getCollection()){
            if (f.getFurnish().getAmount() < amount) res++;
        }
        return res;
    }
}
