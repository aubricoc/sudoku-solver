package cat.aubricoc.sudoku;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class SudokuSolverRunnerTest {

    @Test
    public void testSudoku1() {
        testSudoku("sudoku1", false);
    }

    @Test
    public void testSudoku2() {
        testSudoku("sudoku2", false);
    }
    
    @Test
    public void testSudoku1Multithreading() {
        testSudoku("sudoku1", true);
    }

    @Test
    public void testSudoku2Multithreading() {
        testSudoku("sudoku2", true);
    }

    private void testSudoku(String fileName, boolean multithreading) {
        String file = ClassLoader.getSystemResource(fileName).getPath();
        try {
            SudokuSolverRunner.main(new String[] {file, "-p", multithreading ? "1" : "0"});
        } catch (Throwable e) {
            fail();
        }
    }
}
