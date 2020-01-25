package cat.aubricoc.sudoku.exception;

public class InvalidSudokuException extends SudokuSolverException {

    private static final long serialVersionUID = 1L;

    public InvalidSudokuException(String message) {
        super(message);
    }
}
