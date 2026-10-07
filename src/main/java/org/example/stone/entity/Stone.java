package org.example.stone.entity;

public class Stone {
    private final String name;
    private final double weight;
    private final double pricePerCarat;
    private final int transparency;

    protected Stone(String name, double weight, double pricePerCarat, int transparency) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя камня не может быть пустым");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Вес камня должен быть положительным (передано: " + weight + ")");
        }
        if (pricePerCarat <= 0) {
            throw new IllegalArgumentException("Цена за карат должна быть положительной (передано: " + pricePerCarat + ")");
        }
        if (transparency < 0 || transparency > 100) {
            throw new IllegalArgumentException("Прозрачность должна быть в диапазоне от 0 до 100% (передано: " + transparency + ")");
        }

        this.name = name;
        this.weight = weight;
        this.pricePerCarat = pricePerCarat;
        this.transparency = transparency;
    }

    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }
    public double getPricePerCarat() {
        return pricePerCarat;
    }
    public int getTransparency() {
        return transparency;
    }

    public double calculateCost() {
        return weight * pricePerCarat;
    }

    @Override
    public String toString() {
        return String.format("%s[Вес: %.2f к., Цена/к: %.2f, Прозрачность: %d%%]",
                name, weight, pricePerCarat, transparency);
    }
}
