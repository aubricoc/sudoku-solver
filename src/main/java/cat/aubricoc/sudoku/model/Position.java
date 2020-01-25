package cat.aubricoc.sudoku.model;

import java.util.Objects;

public class Position {

    private final short row;
    private final short col;

    public Position(short row, short col) {
        this.row = row;
        this.col = col;
    }

    public short getRow() {
        return row;
    }

    public short getCol() {
        return col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        return row == other.row && col == other.col;
    }
}
