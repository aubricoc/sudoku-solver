package cat.aubricoc.sudoku.service;

import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Position;
import cat.aubricoc.sudoku.model.Sudoku;
import cat.aubricoc.sudoku.test.TestUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuServiceTest {

    @Test
    public void testGetNextEmptyCellOfNull() {
        Cell cell = SudokuService.getInstance().getNextEmptyCell(null);
        assertNull(cell);
    }

    @Test
    public void testGetNextEmptyCellSolvedSudoku() {
        Sudoku sudoku = TestUtils.getSudoku("sudoku1.solved");
        Cell cell = SudokuService.getInstance().getNextEmptyCell(sudoku);
        assertNull(cell);
    }

    @Test
    public void testGetNextEmptyCellSudoku1() {
        testGetNextEmptyCell("sudoku1", 0, 2);
    }

    @Test
    public void testGetNextEmptyCellSudoku2() {
        testGetNextEmptyCell("sudoku2", 0, 0);
    }

    private void testGetNextEmptyCell(String fileName, int x, int y) {
        Sudoku sudoku = TestUtils.getSudoku(fileName);
        Cell cell = SudokuService.getInstance().getNextEmptyCell(sudoku);
        assertNotNull(cell);
        assertEquals(new Position((short) x, (short) y), cell.getPosition());
    }

    @Test
    public void testCloneSudokuOfNull() {
        Sudoku cloned = SudokuService.getInstance().cloneSudoku(null);
        assertNull(cloned);
    }

    @Test
    public void testCloneSudoku1() {
        testCloneSudoku("sudoku1");
    }

    @Test
    public void testCloneSudoku2() {
        testCloneSudoku("sudoku2");
    }

    private void testCloneSudoku(String fileName) {
        Sudoku sudoku = TestUtils.getSudoku(fileName);
        Sudoku cloned = SudokuService.getInstance().cloneSudoku(sudoku);
        assertNotNull(cloned);
        assertFalse(sudoku == cloned);
        assertEquals(sudoku, cloned);
    }

    @Test
    public void testGetCellByPositionOfNull() {
        Cell cell = getCellByPosition(null, 0, 0);
        assertNull(cell);
    }

    @Test
    public void testGetCellByPositionOfNullPosition() {
        Sudoku sudoku = TestUtils.getSudoku("sudoku1");
        Cell cell = SudokuService.getInstance().getCellByPosition(sudoku, null);
        assertNull(cell);
    }

    @Test
    public void testGetCellByPositionOfInvalidPosition() {
        Sudoku sudoku = TestUtils.getSudoku("sudoku1");
        assertNull(getCellByPosition(sudoku, -1, 0));
        assertNull(getCellByPosition(sudoku, 0, -1));
        assertNull(getCellByPosition(sudoku, 9, 0));
        assertNull(getCellByPosition(sudoku, 0, 9));
    }

    @Test
    public void testGetCellByPosition() {
        Sudoku sudoku = TestUtils.getSudoku("sudoku1");
        Cell cell = getCellByPosition(sudoku, 6, 4);
        assertNotNull(cell);
        assertEquals((short) 4, cell.getValue());
        assertEquals(new Position((short) 6, (short) 4), cell.getPosition());
    }

    private Cell getCellByPosition(Sudoku sudoku, int x, int y) {
        return SudokuService.getInstance().getCellByPosition(sudoku, new Position((short) x, (short) y));
    }

    @Test
    public void testGetPossibleValuesInCellOfNull() {
        List<Short> result = SudokuService.getInstance().getPossibleValuesInCell(null, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPossibleValuesInCellOfNullCell() {
        Sudoku sudoku = TestUtils.getSudoku("sudoku1");
        List<Short> result = SudokuService.getInstance().getPossibleValuesInCell(sudoku, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetPossibleValuesInCell() {
        Sudoku sudoku = TestUtils.getSudoku("sudoku1");
        Cell cell = sudoku.getRows().get(4).get(2);
        List<Short> result = SudokuService.getInstance().getPossibleValuesInCell(sudoku, cell);
        assertEquals(5, result.size());
        assertTrue(Arrays.asList(1, 2, 4, 5, 6).stream().map(Integer::shortValue).allMatch(result::contains));
    }
}
