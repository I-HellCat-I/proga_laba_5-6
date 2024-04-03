package Enums;

public enum Transport {
    NONE,
    FEW,
    NORMAL,
    ENOUGH;

    public static Transport valueOfInt(int a){
        return (a < Transport.values().length? Transport.values()[a] : null);
    }
}
