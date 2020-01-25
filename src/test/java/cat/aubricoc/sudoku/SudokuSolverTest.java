package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.exception.UnresolvableSudokuException;
import cat.aubricoc.sudoku.model.Sudoku;
import cat.aubricoc.sudoku.test.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SudokuSolverTest {

    @Test
    public void testSolveNull() {
        Sudoku solved = SudokuSolver.getInstance().solve(null, false);
        assertNull(solved);
    }

    @Test
    public void testSolveSudokuWithoutSolution() {
        Sudoku sudoku = TestUtils.getSudoku("sudokuNoSolution");
        assertThrows(UnresolvableSudokuException.class, () -> SudokuSolver.getInstance().solve(sudoku, false));
    }

    @Test
    public void testSolveSudoku1() {
        testSolveSudoku("sudoku1");
    }

    @Test
    public void testSolveSudoku2() {
        testSolveSudoku("sudoku2");
    }

    @Test
    public void testSolveSudokuEmpty() {
        testSolveSudoku("sudokuEmpty");
    }

    private void testSolveSudoku(String fileName) {
        Sudoku sudoku = TestUtils.getSudoku(fileName);
        Sudoku solved = SudokuSolver.getInstance().solve(sudoku, false);
        Sudoku solution = TestUtils.getSudoku(fileName + ".solved");
        assertEquals(solution, solved);
    }
}
