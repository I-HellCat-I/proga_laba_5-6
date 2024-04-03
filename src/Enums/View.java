package Enums;

public enum View {
    YARD,
    BAD,
    TERRIBLE;
    public static View valueOfInt(int a){
        return (a < View.values().length? View.values()[a] : null);
    }
}