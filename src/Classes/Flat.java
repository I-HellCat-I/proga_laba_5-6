package Classes;

import Enums.*;
import jdk.jfr.DataAmount;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.*;
import com.fasterxml.jackson.dataformat.xml.*;

public class Flat implements Comparable<Flat> {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double area; //Значение поля должно быть больше 0
    private Integer numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле не может быть null
    private View view; //Поле может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле не может быть null

    public Flat() { // Used for testing purposes
        this("1", new Coordinates(0.1F, 2), 0.1, 1, Furnish.NONE, View.TERRIBLE, Transport.NONE, new House("1", 1, 1, 1));
    }

    private static class IdGenerator{
        private static int funny_long = 1;
        private static ArrayDeque<Integer> missingIds = new ArrayDeque<>();
        private static HashSet<Integer> loadedIds = new HashSet<>();

        public static int generateId(){
            if (missingIds.isEmpty()){
                funny_long++;
                return funny_long-1;
            }
            return missingIds.pop();
        }


        public static void addMissing(Integer missing){
            missingIds.add(missing);
        }
        public static void clear(){
            ArrayDeque<Integer> missingIds = new ArrayDeque<>();
            funny_long = 1;
        }
        protected static void onPartlyLoaded(int id){
            loadedIds.add(id);
        }
        public static void onFullyLoaded(){
            int maxId = 0;
            for (int a : loadedIds){
                if (a > maxId){
                    maxId = a;
                }
            }
            funny_long = maxId + 1;
            for (int i = 1; i < maxId; i++){
                if (!loadedIds.contains(i)){
                    missingIds.add(i);
                }
            }
            loadedIds.clear();
        }
    }

    public static class StXMLParser {
        static Scanner sc;
        static StringBuilder sb;
        public static Collection<Flat> parseFlats(InputStream insteam, Collection<Flat> toWrite){
            sc = new Scanner(insteam);
            sb = new StringBuilder();
            toWrite.clear();

            while (sc.hasNext()){
                sb.append(sc.nextLine());
            }
            int cnt = 1;
            while (sb.indexOf("<Flat>") != -1){ // Мне АБСОЛЮТНО ВСЁ РАВНО, ЧТО ВНЕ КВАРТИР!!!
                sb.replace(0, sb.indexOf("<Flat>"), "");
                Flat toAdd = parseFlat(cnt);
                if (toAdd != null) {
                    toWrite.add(toAdd);
                } else {
                    System.out.println("Из-за ошибки не была добавлена квартира номер: " + cnt);
                    // todo: something
                }
                cnt++;
            }
            return toWrite;
        }
        private static Flat parseFlat(int num){
            return new Flat(Integer.parseInt(parseTag("Id", num)), parseTag("Name", num), ZonedDateTime.parse(parseTag("CreationDate", num)), new Coordinates(Float.parseFloat(parseTag("X", num)), Integer.parseInt(parseTag("Y", num))),
                    Double.parseDouble(parseTag("Area", num)), Integer.parseInt(parseTag("NumberOfRooms", num)), Furnish.valueOf(parseTag("Furnish", num)), View.valueOf(parseTag("View", num)), Transport.valueOf(parseTag("Transport", num)),
                    new House(parseTag("HName", num), Integer.parseInt(parseTag("Year", num)), Long.parseLong(parseTag("NumberOfFlatsOnFloor", num)), Integer.parseInt(parseTag("NumberOfLifts", num))));

        }
        private static String parseTag(String toParse, int num){
            int lastAv = sb.indexOf("</Flat>");
            if (sb.indexOf("<"+toParse+">") == -1 || sb.indexOf("</"+toParse+">") == -1 || sb.indexOf("<"+toParse+">") > lastAv || sb.indexOf("</"+toParse+">") > lastAv || sb.indexOf("<"+toParse+">") > sb.indexOf("</"+toParse+">")){
                System.out.println("У квартиры номер: " + num + " проблемы с именем, скорее всего вы неверно разместили теги <"+toParse+"></"+toParse+">");
            }
            return sb.substring(sb.indexOf("<"+toParse+">") + toParse.length() + 2, sb.indexOf("</"+toParse+">"));
        }
    }

    public Flat(String name, Coordinates coordinates, double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house){
        checkIfRight(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
        id = IdGenerator.generateId();
        creationDate = java.time.ZonedDateTime.now();
        update(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
    }
    private Flat(int id, String name, ZonedDateTime creationDate, Coordinates coordinates, double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house){
        checkIfRight(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
        this.id = id;
        this.creationDate = creationDate;
        update(name, coordinates, area, numberOfRooms, furnish, view, transport, house);
    }
    public void update(String name, Coordinates coordinates, double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house){
        this.name = name==null?this.name:name;
        this.coordinates = coordinates==null?this.coordinates:coordinates;
        this.area = area==0?this.area:area;
        this.numberOfRooms = numberOfRooms==null?this.numberOfRooms:numberOfRooms;
        this.furnish = furnish==null?this.furnish:furnish;
        this.view = view==null?this.view:view;
        this.transport = transport==null?this.transport:transport;
        this.house = house==null?this.house:house;
    }

    private void checkIfRight(String name, Coordinates coordinates, double area, Integer numberOfRooms, Furnish furnish, View view, Transport transport, House house){
        if (area < 0) throw new IllegalArgumentException("Значение area должно быть больше 0");
        if (numberOfRooms < 0) throw new IllegalArgumentException("Значение numberOfRooms должно быть больше 0");
        if (coordinates == null) throw new NullPointerException("coordinates не может быть null");
        if (furnish == null) throw new NullPointerException("furnish не может быть null");
        if (view == null) throw new NullPointerException("View не может быть null");
        if (transport == null) throw new NullPointerException("Transport не может быть null");
        if (house == null) throw new NullPointerException("House не может быть null");
    }
    public final Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void markForDeletion(){
        IdGenerator.addMissing(id);
    }

    @Override
    public int compareTo(Flat o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.name, o.name);
    } // todo: mb there is a better way to compare

    public static void clearIndicator(){
        IdGenerator.clear();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
    public static Flat fromLoad(String id, String name, String creationDate, String x, String y, String area, String numberOfRooms, String furnish, String view, String transport, String housename, String year, String numberOfFlatsOnFloor, String numberOfLifts){
        int Id = -1;
        Coordinates coordinates = new Coordinates(Float.parseFloat(x), Integer.parseInt(y));
        double n_area = Double.parseDouble(area); //Значение поля должно быть больше 0
        Integer NoR = Integer.parseInt(numberOfRooms); //Значение поля должно быть больше 0
        Furnish f = Furnish.valueOf(furnish); //Поле не может быть null
        View v = View.valueOf(view); //Поле может быть null
        Transport t = Transport.valueOf(transport); //Поле не может быть null
        House house;
        if (id != null){
            Id = Integer.parseInt(id);
        }
        if (name.contains("<") || name.contains(">")){
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        if (housename.contains("<") || housename.contains(">")){
            System.out.println("Название содержит запрещённые символы: < >");
            return null;
        }
        if (Objects.equals(housename, "")) housename = null;
        house = new House(housename, Integer.parseInt(year), Long.parseLong(numberOfFlatsOnFloor), Integer.parseInt(numberOfLifts));
        if (id != null) return new Flat(Id, name, ZonedDateTime.parse(creationDate), coordinates, n_area, NoR, f, v, t, house);
        return new Flat(Id, name, ZonedDateTime.parse(creationDate), coordinates, n_area, NoR, f, v, t, house);
    }

    @Override
    public String toString() {
        return "\t<Flat>\n" +
                "\t\t<Id>" + id + "</Id>\n" +
                "\t\t<Name>" + name + "</Name>\n" +
                coordinates + "\n" +
                "\t\t<CreationDate>" + creationDate + "</CreationDate>\n" +
                "\t\t<Area>" + area + "</Area>\n" +
                "\t\t<NumberOfRooms>" + numberOfRooms + "</NumberOfRooms>\n" +
                "\t\t<Furnish>" + furnish + "</Furnish>\n" +
                "\t\t<View>" + view + "</View>\n" +
                "\t\t<Transport>" + transport + "</Transport>\n" +
                house + "\n" +
                "\t</Flat>";
    }
}
