package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.exception.UnresolvableSudokuException;
import cat.aubricoc.sudoku.model.Cell;
import cat.aubricoc.sudoku.model.Sudoku;
import cat.aubricoc.sudoku.service.SudokuService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SudokuSolver {

    private static final SudokuSolver INSTANCE = new SudokuSolver();
    private static final int MAX_THREADS = 100;

    private SudokuSolver() {
        super();
    }

    public static SudokuSolver getInstance() {
        return INSTANCE;
    }

    public Sudoku solve(Sudoku sudoku, boolean multithreading) {
        if (sudoku == null) {
            return null;
        }
        Cell cell = SudokuService.getInstance().getNextEmptyCell(sudoku);
        if (cell == null) {
            return sudoku;
        }
        return tryNumbersInCell(sudoku, cell, multithreading);
    }

    private Sudoku tryNumbersInCell(Sudoku sudoku, Cell cell, boolean multithreading) {
        if (Thread.activeCount() > MAX_THREADS) {
            multithreading = false;
        }

        List<Short> possibleValues = SudokuService.getInstance().getPossibleValuesInCell(sudoku, cell);
        if (possibleValues.isEmpty()) {
            throw new UnresolvableSudokuException();
        } else if (possibleValues.size() == 1) {
            cell.setValue(possibleValues.get(0));
            return solve(sudoku, multithreading);
        }

        List<TryNumberInCellCallable> callables = null;
        if (multithreading) {
            callables = new ArrayList<>(possibleValues.size());
        }

        for (Short numToTry : possibleValues) {
            TryNumberInCellCallable callable = new TryNumberInCellCallable(sudoku, cell, numToTry, multithreading);
            if (multithreading) {
                callables.add(callable);
            } else {
                Sudoku solved = callable.call();
                if (solved != null) {
                    return solved;
                }
            }
        }
        if (multithreading) {
            Sudoku solved = tryNumbersInCellInMultithreadingMode(callables);
            if (solved != null) {
                return solved;
            }
        }
        throw new UnresolvableSudokuException();
    }

    private Sudoku tryNumbersInCellInMultithreadingMode(List<TryNumberInCellCallable> callables) {
        ExecutorService executor = Executors.newFixedThreadPool(callables.size());
        try {
            return executor.invokeAll(callables).stream().map(future -> {
                try {
                    return future.get();
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Failed multithreading mode", e);
                }
            }).filter(Objects::nonNull).findFirst().orElse(null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Failed multithreading mode", e);
        } finally {
            executor.shutdown();
        }
    }

    private class TryNumberInCellCallable implements Callable<Sudoku> {

        private final Sudoku sudoku;
        private final Cell cell;
        private final short numToTry;
        private final boolean multithreading;

        public TryNumberInCellCallable(Sudoku sudoku, Cell cell, short numToTry, boolean multithreading) {
            super();
            this.sudoku = sudoku;
            this.cell = cell;
            this.numToTry = numToTry;
            this.multithreading = multithreading;
        }

        @Override
        public Sudoku call() {
            try {
                Sudoku cloned = SudokuService.getInstance().cloneSudoku(sudoku);
                Cell cellCloned = SudokuService.getInstance().getCellByPosition(cloned, cell.getPosition());
                cellCloned.setValue(numToTry);
                return solve(cloned, multithreading);
            } catch (UnresolvableSudokuException e) {
                return null;
            }
        }
    }
}
