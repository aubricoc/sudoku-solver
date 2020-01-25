package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.exception.UnresolvableSudokuException;
import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Sudoku;
import cat.aubricoc.sudoku.service.SudokuService;
import cat.aubricoc.sudoku.service.SudokuValidator;

public class SudokuSolver {

    private static final SudokuSolver INSTANCE = new SudokuSolver();

    private SudokuSolver() {
        super();
    }

    public static SudokuSolver getInstance() {
        return INSTANCE;
    }

    public Sudoku solve(Sudoku sudoku, boolean multithreading) {
        if (sudoku == null) {
            return null;
        }
        Cell cell = SudokuService.getInstance().getNextEmptyCell(sudoku);
        if (cell == null) {
            return sudoku;
        }
        return tryNumbersInCell(sudoku, cell);
    }

    private Sudoku tryNumbersInCell(Sudoku sudoku, Cell cell) {
        Sudoku cloned = SudokuService.getInstance().cloneSudoku(sudoku);
        Cell cellCloned = SudokuService.getInstance().getCellByPosition(cloned, cell.getPosition());
        for (short numToTry = 1; numToTry < 10; numToTry++) {
            cellCloned.setValue(numToTry);
            if (SudokuValidator.getInstance().validateSudoku(cloned)) {
                return cloned;
            }
        }
        throw new UnresolvableSudokuException();
    }
}
