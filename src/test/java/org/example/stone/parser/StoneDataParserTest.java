package org.example.stone.parser;

import org.example.stone.entity.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StoneDataParserTest {
    private StoneDataParser parser;
    private final String realFilePath = "stones.txt";

    @BeforeEach
    public void setUp() {
        parser = new StoneDataParser();
        File file = new File(realFilePath);
        assertTrue(file.exists());
    }

    @Test
    public void testParseRealExistingFile() {
        List<Stone> stones = parser.parseStonesFromFile(realFilePath);
        assertEquals(3, stones.size());
        assertEquals("Бриллиант", stones.get(0).getName());
        assertEquals("Аметист", stones.get(1).getName());
        assertEquals("Рубин", stones.get(2).getName());
    }

    @Test
    public void testFirstStoneDataAccuracy() {
        List<Stone> stones = parser.parseStonesFromFile(realFilePath);
        Stone diamond = stones.get(0);
        assertEquals("Бриллиант", diamond.getName());
        assertEquals(2.5, diamond.getWeight(), 1e-9);
        assertEquals(5000.0, diamond.getPricePerCarat(), 1e-9);
        assertEquals(98, diamond.getTransparency());
    }

    @Test
    public void testParseNonExistentFile() {
        List<Stone> stones = parser.parseStonesFromFile("wrong_directory/ghost_file.txt");
        assertNotNull(stones);
        assertTrue(stones.isEmpty());
    }
}
