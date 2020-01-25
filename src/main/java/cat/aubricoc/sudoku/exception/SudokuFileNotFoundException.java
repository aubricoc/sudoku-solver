package cat.aubricoc.sudoku.exception;

public class SudokuFileNotFoundException extends SudokuSolverException {

    private static final long serialVersionUID = 1L;

    public SudokuFileNotFoundException(String file, Throwable cause) {
        super("File '" + file + "' not found", cause);
    }
}
