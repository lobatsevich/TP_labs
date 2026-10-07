package org.example.stone.entity;

public class SemiPreciousStone extends Stone {
    private final String cutType;

    public SemiPreciousStone(String name, double weight, double pricePerCarat, int transparency, String cutType) {
        super(name, weight, pricePerCarat, transparency);
        if (cutType == null || cutType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип огранки полудрагоценного камня не может быть пустым");
        }
        this.cutType = cutType;
    }

    public String getCutType() {
        return cutType;
    }

    @Override
    public String toString() {
        return super.toString() + "(Полудрагоценный, Огранка: " + cutType + ")";
    }
}