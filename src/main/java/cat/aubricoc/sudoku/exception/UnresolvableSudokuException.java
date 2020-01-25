package cat.aubricoc.sudoku.exception;

public class UnresolvableSudokuException extends SudokuSolverException {

    private static final long serialVersionUID = 1L;

    public UnresolvableSudokuException() {
        super("Sudoku has no solution!");
    }
}
