package org.example.stone.factory;

import org.example.stone.entity.PreciousStone;
import org.example.stone.entity.SemiPreciousStone;
import org.example.stone.entity.Stone;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StoneFactoryTest {

    @Test
    public void testCreatePreciousStone() {
        Stone stone = StoneFactory.createStone("precious", "Рубин", 1.5, 3000.0, 90, "3");
        assertInstanceOf(PreciousStone.class, stone);
        assertEquals(3, ((PreciousStone) stone).getClarityGrade());
    }

    @Test
    public void testCreateSemiPreciousStone() {
        Stone stone = StoneFactory.createStone("semiprecious", "Топаз", 4.0, 200.0, 70, "Фасетная");
        assertInstanceOf(SemiPreciousStone.class, stone);
        assertEquals("Фасетная", ((SemiPreciousStone) stone).getCutType());
    }

    @Test
    public void testCreateStoneUnknownTypeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            StoneFactory.createStone("fake_type", "Стекло", 1.0, 1.0, 100, "0");
        });
    }
}
