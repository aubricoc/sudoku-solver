package cat.aubricoc.sudoku.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cat.aubricoc.sudoku.exception.InvalidSudokuException;
import cat.aubricoc.sudoku.exception.SudokuFileNotFoundException;
import cat.aubricoc.sudoku.model.Sudoku;
import cat.aubricoc.sudoku.service.SudokuReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class SudokuReaderTest {

    @Test
    public void testReadNull() {
        Sudoku result = SudokuReader.getInstance().read(null);
        assertNull(result);
    }

    @Test
    public void testReadNonexistingFile() {
        assertThrows(SudokuFileNotFoundException.class, () -> SudokuReader.getInstance().read("xxxxxx"));
    }

    @Test
    public void testReadEmptyFile() {
        String file = ClassLoader.getSystemResource("empty").getPath();
        assertThrows(InvalidSudokuException.class, () -> SudokuReader.getInstance().read(file));
    }

    @Test
    public void testReadFileWithInvalidChar() {
        String file = ClassLoader.getSystemResource("invalidChar").getPath();
        assertThrows(InvalidSudokuException.class, () -> SudokuReader.getInstance().read(file));
    }

    @Test
    public void testReadFileWithInvalidRows() {
        String file = ClassLoader.getSystemResource("invalidRows").getPath();
        assertThrows(InvalidSudokuException.class, () -> SudokuReader.getInstance().read(file));
    }

    @Test
    public void testReadWithInavlidColumns() {
        String file = ClassLoader.getSystemResource("invalidColumns").getPath();
        assertThrows(InvalidSudokuException.class, () -> SudokuReader.getInstance().read(file));
    }

    @Test
    public void testRead1() throws IOException {
        testRead("sudoku1");
    }

    @Test
    public void testRead2() throws IOException {
        testRead("sudoku2");
    }

    private void testRead(String fileName) throws IOException {
        String file = ClassLoader.getSystemResource(fileName).getPath();
        Sudoku sudoku = SudokuReader.getInstance().read(file);
        assertNotNull(sudoku);
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            String expected = lines.collect(Collectors.joining(System.lineSeparator()));
            assertEquals(expected, sudoku.toString());
        }
    }
}
