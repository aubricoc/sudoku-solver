package cat.aubricoc.sudoku.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import cat.aubricoc.sudoku.exception.InvalidMulthreadingParameter;
import cat.aubricoc.sudoku.exception.RequiredFileException;
import org.junit.jupiter.api.Test;

public class ConfigurationParserTest {

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
        verifyResult(config, false);
    }

    @Test
    public void testParseWithoutMultithreadingValue() {
        assertThrows(InvalidMulthreadingParameter.class,
                () -> ConfigurationParser.getInstance().parse(new String[] {FILE, "-p"}));
    }

    @Test
    public void testParseWithMultithreadingValueInvalid() {
        assertThrows(InvalidMulthreadingParameter.class,
                () -> ConfigurationParser.getInstance().parse(new String[] {FILE, "-p", "a"}));
    }

    @Test
    public void testParseWithMultithreadingFalse() {
        Configuration config = ConfigurationParser.getInstance().parse(new String[] {FILE, "-p", "0"});
        verifyResult(config, false);
    }

    @Test
    public void testParseWithMultithreadingTrue() {
        Configuration config = ConfigurationParser.getInstance().parse(new String[] {FILE, "-p", "1"});
        verifyResult(config, true);
    }

    private void verifyResult(Configuration config, boolean multithreading) {
        assertNotNull(config);
        assertEquals(multithreading, config.isMultithreading());
        assertEquals(FILE, config.getFile());
    }
}
