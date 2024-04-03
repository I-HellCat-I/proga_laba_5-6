package Enums;

public enum Furnish {
    NONE(0),
    BAD(1),
    FINE(2);
    private int amount;
    Furnish(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

}
