package Classes;

import java.util.Objects;

public class House {
    /**
     * Дом. Нужен по ТЗ для Flat.
     */
    House() {

    }

    public House(String name, int year, long numberOfFlatsOnFloor, Integer numberOfLifts) {
        checkVars(year, numberOfFlatsOnFloor, numberOfLifts);
        this.name = name;
        this.year = year;
        this.numberOfLifts = numberOfLifts;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public static void checkVars(int year, long numberOfFlatsOnFloor, Integer numberOfLifts) {
        checkYear(year);
        checkNumberOfLifts(numberOfLifts);
        checkNumberOfFlatsOnFloor(numberOfFlatsOnFloor);
    }

    public static void checkYear(int year) {
        if (!(0 < year && year <= 630))
            throw new IllegalArgumentException("Year должен быть между 0 и 630, а он: " + year);
    }

    public static void checkNumberOfFlatsOnFloor(long numberOfFlatsOnFloor) {
        if (numberOfFlatsOnFloor < 0) throw new IllegalArgumentException("numberOfFlatsOnFloor должен быть больше 0");
    }

    public static void checkNumberOfLifts(Integer numberOfLifts) {
        if (numberOfLifts < 0) throw new IllegalArgumentException("numberOfLifts должен быть больше 0");
    }

    public static void checkName(String name) {
        if (name == null) throw new NullPointerException("housename е должно быть null");
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public long getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public Integer getNumberOfLifts() {
        return numberOfLifts;
    }

    private static class HouseStorage {

    }

    private String name; //Поле может быть null
    private int year; //Максимальное значение поля: 630, Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; //Значение поля должно быть больше 0
    private Integer numberOfLifts; //Значение поля должно быть больше 0


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        if (year != house.year) return false;
        if (numberOfFlatsOnFloor != house.numberOfFlatsOnFloor) return false;
        if (!Objects.equals(name, house.name)) return false;
        return numberOfLifts.equals(house.numberOfLifts);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (int) (numberOfFlatsOnFloor ^ (numberOfFlatsOnFloor >>> 32));
        result = 31 * result + numberOfLifts.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\t\t<House>\n" +
                "\t\t\t<HName>" + name + "</HName>\n" +
                "\t\t\t<Year>" + year + "</Year>\n" +
                "\t\t\t<NumberOfFlatsOnFloor>" + numberOfFlatsOnFloor + "</NumberOfFlatsOnFloor>\n" +
                "\t\t\t<NumberOfLifts>" + numberOfLifts + "</NumberOfLifts>\n" +
                "\t\t</House>";
    }
}
