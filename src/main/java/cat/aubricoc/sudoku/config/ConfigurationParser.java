package cat.aubricoc.sudoku.config;

import cat.aubricoc.sudoku.exception.InvalidMulthreadingParameterException;
import cat.aubricoc.sudoku.exception.RequiredFileException;

public class ConfigurationParser {

    private static final ConfigurationParser INSTANCE = new ConfigurationParser();
    private static final String MULTITHREADING_KEYWORD = "-p";

    private ConfigurationParser() {
        super();
    }

    public static ConfigurationParser getInstance() {
        return INSTANCE;
    }

    public Configuration parse(String[] args) {
        boolean multithreading = false;
        String file = null;
        if (args != null) {
            int iter = 0;
            while (iter < args.length) {
                String arg = args[iter];
                if (MULTITHREADING_KEYWORD.equals(arg)) {
                    iter++;
                    multithreading = parseMultithreadingValue(args, iter);
                } else if (file == null && arg != null && arg.length() > 0) {
                    file = arg;
                }
                iter++;
            }
        }
        if (file == null) {
            throw new RequiredFileException();
        }
        return new Configuration(multithreading, file);
    }

    private boolean parseMultithreadingValue(String[] args, int iter) {
        if (iter < args.length) {
            String arg = args[iter];
            if ("1".equals(arg)) {
                return true;
            } else if ("0".equals(arg)) {
                return false;
            }
        }
        throw new InvalidMulthreadingParameterException();
    }
}
