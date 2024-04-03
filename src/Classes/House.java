package Classes;

import java.util.Objects;

public class House {
    private static class HouseStorage {

    }

    private String name; //Поле может быть null
    private int year; //Максимальное значение поля: 630, Значение поля должно быть больше 0
    private long numberOfFlatsOnFloor; //Значение поля должно быть больше 0
    private Integer numberOfLifts; //Значение поля должно быть больше 0

    public House(String name, int year, long numberOfFlatsOnFloor, Integer numberOfLifts) {
        if (!(0 < year && year <= 630))
            throw new IllegalArgumentException("Year must be between 0 and 630 and it is " + year);
        if (numberOfFlatsOnFloor < 0) throw new IllegalArgumentException("numberOfFlatsOnFloor must be greater than 0");
        if (numberOfLifts < 0) throw new IllegalArgumentException("numberOfLifts must be greater than 0");
        this.name = name;
        this.year = year;
        this.numberOfLifts = numberOfLifts;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

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
