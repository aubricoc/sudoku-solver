package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.exception.InvalidSudokuException;
import cat.aubricoc.sudoku.exception.SudokuFileNotFoundException;
import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Sudoku;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuReader {

    private static final SudokuReader INSTANCE = new SudokuReader();
    private static final Pattern CELL_PATTERN = Pattern.compile("[1-9x]");

    private SudokuReader() {
        super();
    }

    public static SudokuReader getInstance() {
        return INSTANCE;
    }

    public Sudoku read(String fileName) {
        if (fileName == null) {
            return null;
        }
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<List<Cell>> cells = lines.map(this::readRow).collect(Collectors.toList());
            if (cells.size() != 9) {
                throw new InvalidSudokuException("Sudoku has " + cells.size() + "rows");
            }
            return new Sudoku(cells);
        } catch (IOException e) {
            throw new SudokuFileNotFoundException(fileName, e);
        }
    }

    private List<Cell> readRow(String line) {
        if (line == null) {
            return Collections.emptyList();
        }
        List<Cell> row = Stream.of(line.split(",")).map(this::readCell).collect(Collectors.toList());
        if (row.size() != 9) {
            throw new InvalidSudokuException("Sudoku has " + row.size() + "columns");
        }
        return row;
    }

    private Cell readCell(String value) {
        Cell cell = new Cell();
        Matcher matcher = CELL_PATTERN.matcher(value);
        if (matcher.matches()) {
            if (!"x".equals(value)) {
                cell.setValue(Short.parseShort(value));
            }
        } else {
            throw new InvalidSudokuException("Sudoku has an invalid value: '" + value + "'");
        }
        return cell;
    }
}
