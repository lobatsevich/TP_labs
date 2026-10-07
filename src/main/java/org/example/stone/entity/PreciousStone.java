package org.example.stone.entity;

public class PreciousStone extends Stone{
    private final int clarityGrade;

    public PreciousStone(String name, double weight, double pricePerCarat, int transparency, int clarityGrade) {
        super(name, weight, pricePerCarat, transparency);
        if (clarityGrade < 1 || clarityGrade > 12) {
            throw new IllegalArgumentException("Уровень чистоты драгоценного камня должен быть в диапазоне от 1 до 12 (передано: " + clarityGrade + ")");
        }
        this.clarityGrade = clarityGrade;
    }

    public int getClarityGrade() {
        return clarityGrade;
    }

    @Override
    public String toString() {
        return super.toString() + "(Драгоценный, Чистота: " + clarityGrade + ")";
    }
}