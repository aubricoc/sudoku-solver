package cat.aubricoc.sudoku.exception;

public class InvalidMulthreadingParameter extends SudokuSolverException {

    private static final long serialVersionUID = 1L;

    public InvalidMulthreadingParameter() {
        super("Multithreading parameter must be 0 or 1");
    }
}
