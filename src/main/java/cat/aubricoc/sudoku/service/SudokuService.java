package cat.aubricoc.sudoku.service;

import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Position;
import cat.aubricoc.sudoku.model.Sudoku;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuService {

    private static final SudokuService INSTANCE = new SudokuService();

    private SudokuService() {
        super();
    }

    public static SudokuService getInstance() {
        return INSTANCE;
    }

    public Cell getNextEmptyCell(Sudoku sudoku) {
        if (sudoku == null) {
            return null;
        }
        return sudoku.getRows().stream().flatMap(List::stream).filter(cell -> cell.getValue() == null).findFirst()
                .orElse(null);
    }

    public Sudoku cloneSudoku(Sudoku sudoku) {
        if (sudoku == null) {
            return null;
        }
        return new Sudoku(
                sudoku.getRows().stream().map(row -> row.stream().map(this::cloneCell).collect(Collectors.toList()))
                        .collect(Collectors.toList()));
    }

    private Cell cloneCell(Cell cell) {
        Cell cloned = new Cell(cell.getPosition());
        cloned.setValue(cell.getValue());
        return cloned;
    }

    public Cell getCellByPosition(Sudoku sudoku, Position position) {
        if (sudoku == null || position == null) {
            return null;
        }
        if (isValidPosition(position)) {
            return sudoku.getRows().get(position.getRow()).get(position.getCol());
        }
        return null;
    }

    private boolean isValidPosition(Position position) {
        return isValidPositionNumber(position.getRow()) && isValidPositionNumber(position.getCol());
    }

    private boolean isValidPositionNumber(short position) {
        return position > -1 && position < 9;
    }

    public List<Short> getPossibleValuesInCell(Sudoku sudoku, Cell cell) {
        if (sudoku == null || cell == null) {
            return Collections.emptyList();
        }
        List<Short> possibleValues = IntStream.range(1, 10).mapToObj(value -> (short) value).filter(value -> {
            cell.setValue((short) value);
            return SudokuValidator.getInstance().validateSudoku(sudoku);
        }).collect(Collectors.toList());
        cell.setValue(null);
        return possibleValues;
    }
}
