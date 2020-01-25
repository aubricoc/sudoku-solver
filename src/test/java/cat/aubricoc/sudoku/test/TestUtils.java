package cat.aubricoc.sudoku.test;

import cat.aubricoc.sudoku.SudokuReader;
import cat.aubricoc.sudoku.model.Sudoku;

public class TestUtils {

    public static Sudoku getSudoku(String fileName) {
        String file = ClassLoader.getSystemResource(fileName).getPath();
        return SudokuReader.getInstance().read(file);
    }
}
