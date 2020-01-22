package cat.aubricoc.sudoku.exception;

public class RequiredFileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RequiredFileException() {
        super("Sudoku file is mandatory");
    }
}
