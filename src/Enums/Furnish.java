package Enums;

public enum Furnish {
    NONE,
    BAD,
    FINE;

    public static Furnish valueOfInt(int a){
        return (a < Furnish.values().length? Furnish.values()[a] : null);
    }

}
