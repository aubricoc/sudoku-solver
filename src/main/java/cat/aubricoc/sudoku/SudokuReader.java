package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.exception.InvalidSudokuException;
import cat.aubricoc.sudoku.exception.SudokuFileNotFoundException;
import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Position;
import cat.aubricoc.sudoku.model.Sudoku;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        try {
            List<List<Cell>> cells = new ArrayList<>();
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (short rowNum = 0; rowNum < lines.size(); rowNum++) {
                String line = lines.get(rowNum);
                if (line != null && line.length() > 0) {
                    cells.add(readRow(line, rowNum));
                }
            }
            if (cells.size() != 9) {
                throw new InvalidSudokuException("Sudoku has " + cells.size() + "rows");
            }
            return new Sudoku(cells);
        } catch (IOException e) {
            throw new SudokuFileNotFoundException(fileName, e);
        }
    }

    private List<Cell> readRow(String line, short rowNum) {
        if (line == null) {
            return Collections.emptyList();
        }
        String[] parts = line.split(",");
        if (parts.length != 9) {
            throw new InvalidSudokuException("Sudoku has " + parts.length + "columns");
        }
        List<Cell> row = new ArrayList<>();
        for (short colNum = 0; colNum < parts.length; colNum++) {
            String value = parts[colNum];
            Position position = new Position(rowNum, colNum);
            row.add(readCell(value, position));
        }
        return row;
    }

    private Cell readCell(String value, Position position) {
        Cell cell = new Cell(position);
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
