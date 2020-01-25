package cat.aubricoc.sudoku.service;

import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Sudoku;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SudokuValidator {

    private static final SudokuValidator INSTANCE = new SudokuValidator();

    private SudokuValidator() {
        super();
    }

    public static SudokuValidator getInstance() {
        return INSTANCE;
    }

    public boolean validateSudoku(Sudoku sudoku) {
        if (sudoku == null) {
            return false;
        }
        return sudoku.getRows().stream().allMatch(this::validateGroupOfCells)
                && sudoku.getColumns().stream().allMatch(this::validateGroupOfCells)
                && sudoku.getSquares().stream().allMatch(this::validateGroupOfCells);
    }

    private boolean validateGroupOfCells(List<Cell> cells) {
        Set<Short> values = new HashSet<>();
        return cells.stream().map(Cell::getValue).filter(Objects::nonNull).allMatch(value -> {
            if (values.contains(value)) {
                return false;
            }
            values.add(value);
            return true;
        });
    }
}
