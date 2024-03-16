package Classes;

import Enums.Furnish;
import Enums.Transport;
import Enums.View;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class StXMLParser {
    static Scanner sc;
    static StringBuilder sb;
    public static Stack<Flat> parseFlats(InputStream insteam){
        sc = new Scanner(insteam);
        sb = new StringBuilder();
        Stack<Flat> ans = new Stack<>();

        while (sc.hasNext()){
            sb.append(sc.nextLine());
        }
        int cnt = 1;
        while (sb.indexOf("<Flat>") != -1){ // Мне АБСОЛЮТНО ВСЁ РАВНО, ЧТО ВНЕ КВАРТИР!!!
            sb.replace(0, sb.indexOf("<Flat>"), "");
            Flat toAdd = parseFlat(cnt);
            if (toAdd != null) {
                ans.add(toAdd);
            } else {
                System.out.println("Из-за ошибки не была добавлена квартира номер: " + cnt);
                // todo: something
            }
            cnt++;
        }
        return ans;
    }
    private static Flat parseFlat(int num){
        int Id;
        String name; //Поле не может быть null, Строка не может быть пустой
        ZonedDateTime creationDate;
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
        //String[] tagsToParse = {"Id", "Name", "CreationDate", "X", "Y", "Area", "NumberOfRooms", "Furnish", "View", "Transport", "HName", "Year", "NumberOfFlatsOnFloor", "NumberOfLifts"};
        Id = Integer.parseInt(parseTag("Id", num));
        name = parseTag("Name", num);
        if (name.contains("<") || name.contains(">")){
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        creationDate = ZonedDateTime.parse(parseTag("CreationDate", num));
        x = Float.parseFloat(parseTag("X", num));
        y = Integer.parseInt(parseTag("Y", num));
        coordinates = new Coordinates(x, y);
        area = Double.parseDouble(parseTag("Area", num));
        numberOfRooms = Integer.parseInt(parseTag("NumberOfRooms", num)); //
        furnish = Furnish.valueOf(parseTag("Furnish", num));
        view = View.valueOf(parseTag("View", num));
        transport = Transport.valueOf(parseTag("Transport", num));
        housename = parseTag("HName", num);
        if (housename.contains("<") || housename.contains(">")){
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        if (Objects.equals(housename, "")) housename = null;
        year = Integer.parseInt(parseTag("Year", num));
        numberOfFlatsOnFloor = Integer.parseInt(parseTag("NumberOfFlatsOnFloor", num));
        numberOfLifts = Integer.parseInt(parseTag("NumberOfLifts", num));
        house = new House(housename, year, numberOfFlatsOnFloor, numberOfLifts);
        return Flat.fromLoad(Id, name, creationDate, coordinates, area, numberOfRooms, furnish, view, transport, house);
    }
    private static String parseTag(String toParse, int num){
        int lastAv = sb.indexOf("</Flat>");
        if (sb.indexOf("<"+toParse+">") == -1 || sb.indexOf("</"+toParse+">") == -1 || sb.indexOf("<"+toParse+">") > lastAv || sb.indexOf("</"+toParse+">") > lastAv || sb.indexOf("<"+toParse+">") > sb.indexOf("</"+toParse+">")){
            System.out.println("У квартиры номер: " + num + " проблемы с именем, скорее всего вы неверно разместили теги <"+toParse+"></"+toParse+">");
        }
        return sb.substring(sb.indexOf("<"+toParse+">") + toParse.length() + 2, sb.indexOf("</"+toParse+">"));
    }
}
