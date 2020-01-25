package cat.aubricoc.sudoku.service;

import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Position;
import cat.aubricoc.sudoku.model.Sudoku;
import java.util.List;
import java.util.stream.Collectors;

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
        return sudoku.getCells().stream().flatMap(List::stream).filter(cell -> cell.getValue() == null).findFirst()
                .orElse(null);
    }

    public Sudoku cloneSudoku(Sudoku sudoku) {
        if (sudoku == null) {
            return null;
        }
        return new Sudoku(
                sudoku.getCells().stream().map(row -> row.stream().map(this::cloneCell).collect(Collectors.toList()))
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
            return sudoku.getCells().get(position.getRow()).get(position.getCol());
        }
        return null;
    }

    private boolean isValidPosition(Position position) {
        return isValidPositionNumber(position.getRow()) && isValidPositionNumber(position.getCol());
    }

    private boolean isValidPositionNumber(short position) {
        return position > -1 && position < 9;
    }
}