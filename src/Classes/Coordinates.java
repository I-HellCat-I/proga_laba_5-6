package Classes;

import com.fasterxml.jackson.core.SerializableString;

public class Coordinates {
    private float x; //Значение поля должно быть больше -146
    private Integer y; //Максимальное значение поля: 185, Поле не может быть null

    public Coordinates(float x, Integer y) {
        if (x <= -146) throw new IllegalArgumentException("x cannot be lower than -146");
        if (y == null) throw new NullPointerException("y is null");
        if (y > 185) throw new IllegalArgumentException("y cannot be greater than 185");
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "\t\t<Coordinates>\n\t\t\t<X>" + x + "</X>\n" + "\t\t\t<Y>" + y + "</Y>\n\t\t</Coordinates>";
    }

    public float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
