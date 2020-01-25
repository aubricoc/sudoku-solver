package cat.aubricoc.sudoku.config;

import cat.aubricoc.sudoku.exception.InvalidMulthreadingParameterException;
import cat.aubricoc.sudoku.exception.RequiredFileException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfigurationParserTest {

    private static final String FILE = "/file.sudoku";

    @Test
    public void testParseNoArgs() {
        assertThrows(RequiredFileException.class, () -> ConfigurationParser.parse());
    }

    @Test
    public void testParseEmptyString() {
        assertThrows(RequiredFileException.class, () -> ConfigurationParser.parse(""));
    }

    @Test
    public void testParseWithoutMultithreading() {
        Configuration config = ConfigurationParser.parse(FILE);
        verifyResult(config, false);
    }

    @Test
    public void testParseWithoutMultithreadingValue() {
        assertThrows(InvalidMulthreadingParameterException.class, () -> ConfigurationParser.parse(FILE, "-p"));
    }

    @Test
    public void testParseWithMultithreadingValueInvalid() {
        assertThrows(InvalidMulthreadingParameterException.class, () -> ConfigurationParser.parse(FILE, "-p", "a"));
    }

    @Test
    public void testParseWithMultithreadingFalse() {
        Configuration config = ConfigurationParser.parse(FILE, "-p", "0");
        verifyResult(config, false);
    }

    @Test
    public void testParseWithMultithreadingTrue() {
        Configuration config = ConfigurationParser.parse(FILE, "-p", "1");
        verifyResult(config, true);
    }

    private void verifyResult(Configuration config, boolean multithreading) {
        assertNotNull(config);
        assertEquals(multithreading, config.isMultithreading());
        assertEquals(FILE, config.getFile());
    }
}
