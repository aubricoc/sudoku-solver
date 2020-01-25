package cat.aubricoc.sudoku.test;

import cat.aubricoc.sudoku.model.Sudoku;
import cat.aubricoc.sudoku.service.SudokuReader;

public class TestUtils {

    public static Sudoku getSudoku(String fileName) {
        String file = ClassLoader.getSystemResource(fileName).getPath();
        return SudokuReader.getInstance().read(file);
    }
}
