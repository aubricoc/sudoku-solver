package cat.aubricoc.sudoku;

import cat.aubricoc.sudoku.config.Configuration;
import cat.aubricoc.sudoku.config.ConfigurationParser;
import cat.aubricoc.sudoku.exception.SudokuSolverException;
import cat.aubricoc.sudoku.model.Sudoku;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuSolverRunner {

    private static final Logger LOG = Logger.getLogger(SudokuSolverRunner.class.getName());

    public static void main(String[] args) {
        log("Sudoku Solver starts...");
        try {
            Configuration config = ConfigurationParser.getInstance().parse(args);
            log(config);
            Sudoku sudoku = SudokuReader.getInstance().read(config.getFile());
            log("Sudoku to solve:", sudoku);
            Sudoku solution = SudokuSolver.getInstance().solve(sudoku, config.isMultithreading());
            log("Sudoku solved:", solution);
            log("Sudoku Solver finished!");
        } catch (SudokuSolverException e) {
            LOG.severe("Failed Sudoku Solver: " + e.getMessage());
        }
    }

    private static void log(Object... obj) {
        if (LOG.isLoggable(Level.INFO)) {
            String message = Stream.of(obj).filter(Objects::nonNull).map(Object::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
            LOG.info(message);
        }
    }
}
