package cat.aubricoc.sudoku.service;

import cat.aubricoc.sudoku.model.Sudoku;

public class SudokuValidator {

    private static final SudokuValidator INSTANCE = new SudokuValidator();

    private SudokuValidator() {
        super();
    }

    public static SudokuValidator getInstance() {
        return INSTANCE;
    }

    public boolean validateSudoku(Sudoku sudoku) {
        return false;
    }
}
