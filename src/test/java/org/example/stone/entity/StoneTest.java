package org.example.stone.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoneTest {

    @Test
    public void testPreciousStoneGettersAndCost() {
        PreciousStone diamond = new PreciousStone("Бриллиант", 2.0, 5000.0, 95, 1);

        assertEquals("Бриллиант", diamond.getName());
        assertEquals(2.0, diamond.getWeight(), 1e-9);
        assertEquals(5000.0, diamond.getPricePerCarat(), 1e-9);
        assertEquals(95, diamond.getTransparency());
        assertEquals(1, diamond.getClarityGrade());

        assertEquals(10000.0, diamond.calculateCost(), 1e-9);
        assertTrue(diamond.toString().contains("Драгоценный"));
    }

    @Test
    public void testSemiPreciousStoneGettersAndCost() {
        SemiPreciousStone amethyst = new SemiPreciousStone("Аметист", 10.0, 150.0, 50, "Кабошон");

        assertEquals("Аметист", amethyst.getName());
        assertEquals(10.0, amethyst.getWeight(), 1e-9);
        assertEquals(150.0, amethyst.getPricePerCarat(), 1e-9);
        assertEquals(50, amethyst.getTransparency());
        assertEquals("Кабошон", amethyst.getCutType());

        assertEquals(1500.0, amethyst.calculateCost(), 1e-9);
        assertTrue(amethyst.toString().contains("Полудрагоценный"));
    }
}
