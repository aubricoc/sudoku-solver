package cat.aubricoc.sudoku.config;

import cat.aubricoc.sudoku.exception.RequiredFileException;
import java.util.Objects;
import java.util.stream.Stream;

public class ConfigurationParser {

    private ConfigurationParser() {
        super();
    }

    private static class InstanceHolder {

        private static final ConfigurationParser INSTANCE = new ConfigurationParser();
    }

    public static ConfigurationParser getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Configuration parse(String[] args) {
        boolean multithreading = false;
        String file = args == null ? null : Stream.of(args)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(arg -> !arg.isEmpty())
                .findFirst()
                .orElse(null);
        if (file == null) {
            throw new RequiredFileException();
        }
        return new Configuration(multithreading, file);
    }
}
