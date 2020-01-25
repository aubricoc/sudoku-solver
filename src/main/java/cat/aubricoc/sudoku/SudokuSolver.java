package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.model.Sudoku;

public class SudokuSolver {

    private static final SudokuSolver INSTANCE = new SudokuSolver();

    private SudokuSolver() {
        super();
    }

    public static SudokuSolver getInstance() {
        return INSTANCE;
    }

    public Sudoku solve(Sudoku sudoku, boolean multithreading) {
        return null;
    }
}
