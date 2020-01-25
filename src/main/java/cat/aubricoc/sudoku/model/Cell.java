package cat.aubricoc.sudoku.model;

import java.util.Objects;

public class Cell {

    private Short value;

    public Short getValue() {
        return value;
    }

    public void setValue(Short value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell other = (Cell) obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
