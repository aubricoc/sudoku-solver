package cat.aubricoc.sudoku;

import java.util.logging.Logger;

import cat.aubricoc.sudoku.config.Configuration;
import cat.aubricoc.sudoku.config.ConfigurationParser;

public class SudokuSolver {

	private static final Logger LOG = Logger.getLogger(SudokuSolver.class.getName());
	
	public static void main(String[] args) {
		LOG.info("Sudoku Solver");
		Configuration config = ConfigurationParser.getInstance().parse(args);
		LOG.info("Run multithreading: " + config.isMultithreading());
		LOG.info(config.getFile());
	}
}
