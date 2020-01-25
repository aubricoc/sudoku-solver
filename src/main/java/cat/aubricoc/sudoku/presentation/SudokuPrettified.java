package cat.aubricoc.sudoku.presentation;

import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Sudoku;
import java.util.List;

public class GoodLookingSudoku {

    private static final String LINE = "+ · · · · · · · · · · · + · · · · · · · · · · · + · · · · · · · · · · · +";
    private static final String LINE_BOLD = "+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +";
    private static final String LINE_SEPARATION = "+       ·       ·       +       ·       ·       +       ·       ·       +";

    private final Sudoku sudoku;

    public GoodLookingSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(System.lineSeparator());
        sudoku.getRows().forEach(row -> print(row, builder));
        builder.append(LINE_BOLD).append(System.lineSeparator());
        return builder.toString();
    }

    private void print(List<Cell> row, StringBuilder builder) {
        boolean boldLine = row.get(0).getPosition().getRow() % 3 == 0;
        builder.append(boldLine ? LINE_BOLD : LINE).append(System.lineSeparator()).append(LINE_SEPARATION)
                .append(System.lineSeparator());
        row.forEach(cell -> print(cell, builder));
        builder.append("+").append(System.lineSeparator()).append(LINE_SEPARATION).append(System.lineSeparator());
    }

    private void print(Cell cell, StringBuilder builder) {
        Short value = cell.getValue();
        boolean boldLine = cell.getPosition().getCol() % 3 == 0;
        builder.append(boldLine ? "+   " : "·   ").append(value == null ? " " : value).append("   ");
    }
}
