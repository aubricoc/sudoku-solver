package cat.aubricoc.sudoku.exception;

public abstract class SudokuSolverException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SudokuSolverException(String message) {
        super(message);
    }

    public SudokuSolverException(String message, Throwable cause) {
        super(message, cause);
    }
}
