package cat.aubricoc.sudoku.model;

import cat.aubricoc.sudoku.utils.BoardUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sudoku {

    private final List<List<Cell>> rows;
    private final List<List<Cell>> columns;
    private final List<List<Cell>> squares;

    public Sudoku(List<List<Cell>> rows) {
        this.rows = rows;
        this.columns = BoardUtils.transformRowsToColumns(rows);
        this.squares = BoardUtils.transformRowsToSquares(rows);
    }

    public List<List<Cell>> getRows() {
        return rows;
    }

    public List<List<Cell>> getColumns() {
        return columns;
    }

    public List<List<Cell>> getSquares() {
        return squares;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sudoku other = (Sudoku) obj;
        return Objects.equals(rows, other.rows);
    }

    @Override
    public String toString() {
        return rows.stream().map(row -> row.stream().map(Object::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
