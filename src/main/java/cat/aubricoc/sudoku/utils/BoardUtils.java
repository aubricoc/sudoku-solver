package cat.aubricoc.sudoku.utils;

import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Position;
import java.util.ArrayList;
import java.util.List;

public class BoardUtils {

    private BoardUtils() {
        throw new UnsupportedOperationException();
    }

    public static List<List<Cell>> transformRowsToColumns(List<List<Cell>> rows) {
        List<List<Cell>> columns = initCellsStructure();
        rows.stream().flatMap(List::stream).forEach(cell -> columns.get(cell.getPosition().getCol()).add(cell));
        return columns;
    }

    public static List<List<Cell>> transformRowsToSquares(List<List<Cell>> rows) {
        List<List<Cell>> columns = initCellsStructure();
        rows.stream().flatMap(List::stream)
                .forEach(cell -> columns.get(getSquarePosition(cell.getPosition())).add(cell));
        return columns;
    }

    private static List<List<Cell>> initCellsStructure() {
        List<List<Cell>> list = new ArrayList<>();
        for (int iter = 0; iter < 9; iter++) {
            list.add(new ArrayList<>());
        }
        return list;
    }

    private static int getSquarePosition(Position position) {
        int vPos = position.getRow() / 3;
        int hPos = position.getCol() / 3;
        return vPos * 3 + hPos;
    }
}
