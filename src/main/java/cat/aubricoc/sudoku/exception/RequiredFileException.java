package cat.aubricoc.sudoku.exception;

public class RequiredFileException extends SudokuSolverException {

    private static final long serialVersionUID = 1L;

    public RequiredFileException() {
        super("Sudoku file is mandatory");
    }
}
