package cat.aubricoc.sudoku.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Sudoku {

    private final List<List<Cell>> cells;

    public Sudoku(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cells);
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
        return Objects.equals(cells, other.cells);
    }

    @Override
    public String toString() {
        return cells.stream().map(row -> row.stream().map(cell -> {
            Short value = cell.getValue();
            return value == null ? "x" : value.toString();
        }).collect(Collectors.joining(","))).collect(Collectors.joining(System.lineSeparator()));
    }
}
