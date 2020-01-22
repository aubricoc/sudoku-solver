package cat.aubricoc.sudoku.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import cat.aubricoc.sudoku.exception.RequiredFileException;

class ConfigurationParserTest {

    private static final String FILE = "/file.sudoku";

    @Test
    public void testParseNullArgs() {
        assertThrows(RequiredFileException.class, () -> ConfigurationParser.getInstance().parse(null));
    }

    @Test
    public void testParseEmptyArgs() {
        assertThrows(RequiredFileException.class, () -> ConfigurationParser.getInstance().parse(new String[0]));
    }

    @Test
    public void testParseEmptyString() {
        assertThrows(RequiredFileException.class, () -> ConfigurationParser.getInstance().parse(new String[] {""}));
    }

    @Test
    public void testParseWithoutMultithreading() {
        Configuration config = ConfigurationParser.getInstance().parse(new String[] {FILE});
        assertNotNull(config);
        assertFalse(config.isMultithreading());
        assertEquals(FILE, config.getFile());
    }
    
    @Test
    public void testParseWithoutMultithreadingValue() {
        Configuration config = ConfigurationParser.getInstance().parse(new String[] {FILE, "-p"});
        assertNotNull(config);
        assertFalse(config.isMultithreading());
        assertEquals(FILE, config.getFile());
    }
}
