package org.example.stone.action;

import org.example.stone.entity.PreciousStone;
import org.example.stone.entity.SemiPreciousStone;
import org.example.stone.entity.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NecklaceActionTest {
    private NecklaceAction action;
    private List<Stone> necklace;

    @BeforeEach
    public void setUp() {
        action = new NecklaceAction();
        necklace = new ArrayList<>();
        necklace.add(new PreciousStone("Бриллиант", 2.0, 5000.0, 90, 1));
        necklace.add(new SemiPreciousStone("Аметист", 10.0, 100.0, 50, "Кабошон"));
    }

    @Test
    public void testCalculateTotalWeight() {
        assertEquals(12.0, action.calculateTotalWeight(necklace), 1e-9);
    }

    @Test
    public void testCalculateTotalCost() {
        assertEquals(11000.0, action.calculateTotalCost(necklace), 1e-9);
    }

    @Test
    public void testSortByValue() {
        necklace.add(new PreciousStone("Изумруд", 1.0, 3000.0, 80, 2));

        action.sortByValue(necklace);

        assertEquals("Бриллиант", necklace.get(0).getName());
        assertEquals("Изумруд", necklace.get(1).getName());
        assertEquals("Аметист", necklace.get(2).getName());
    }

    @Test
    public void testFindByTransparencyRange() {
        List<Stone> result = action.findByTransparencyRange(necklace, 40, 60);

        assertEquals(1, result.size());
        assertEquals("Аметист", result.get(0).getName());
    }
}
