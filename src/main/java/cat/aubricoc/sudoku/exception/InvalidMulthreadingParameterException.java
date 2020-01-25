package cat.aubricoc.sudoku.exception;

public class InvalidMulthreadingParameterException extends SudokuSolverException {

    private static final long serialVersionUID = 1L;

    public InvalidMulthreadingParameterException() {
        super("Multithreading parameter must be 0 or 1");
    }
}
