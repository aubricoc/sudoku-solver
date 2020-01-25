package cat.aubricoc.sudoku;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SudokuSolverRunnerTest {

    @Test
    public void testSudoku1() {
        testSudoku("sudoku1");
    }

    @Test
    public void testSudoku2() {
        testSudoku("sudoku2");
    }

    private void testSudoku(String fileName) {
        String file = ClassLoader.getSystemResource(fileName).getPath();
        try {
            SudokuSolverRunner.main(new String[] {file});
        } catch (Throwable e) {
            fail();
        }
    }
}
