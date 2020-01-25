package cat.aubricoc.sudoku.service;

import cat.aubricoc.sudoku.test.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuValidatorTest {

    @Test
    public void testValidateSudokuNull() {
        assertFalse(SudokuValidator.getInstance().validateSudoku(null));
    }

    @Test
    public void testValidateSudoku() {
        assertTrue(SudokuValidator.getInstance().validateSudoku(TestUtils.getSudoku("sudoku1")));
    }
    
    @Test
    public void testValidateSudokuSolved() {
        assertTrue(SudokuValidator.getInstance().validateSudoku(TestUtils.getSudoku("sudoku1.solved")));
    }
    
    @Test
    public void testValidateSudokuEmpty() {
        assertTrue(SudokuValidator.getInstance().validateSudoku(TestUtils.getSudoku("sudokuEmpty")));
    }
    
    @Test
    public void testValidateSudokuRepeatedNumInRow() {
        assertFalse(SudokuValidator.getInstance().validateSudoku(TestUtils.getSudoku("sudokuInvalid1")));
    }
    
    @Test
    public void testValidateSudokuRepeatedNumInCol() {
        assertFalse(SudokuValidator.getInstance().validateSudoku(TestUtils.getSudoku("sudokuInvalid2")));
    }
    
    @Test
    public void testValidateSudokuRepeatedNumInSquare() {
        assertFalse(SudokuValidator.getInstance().validateSudoku(TestUtils.getSudoku("sudokuInvalid3")));
    }
}
