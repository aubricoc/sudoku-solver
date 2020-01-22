package cat.aubricoc.sudoku.config;

import java.util.Objects;

public class Configuration {

	private final boolean multithreading;
	private final String file;

	public Configuration(boolean multithreading, String file) {
		super();
		this.multithreading = multithreading;
		this.file = file;
	}

	public boolean isMultithreading() {
		return multithreading;
	}

	public String getFile() {
		return file;
	}

	@Override
	public int hashCode() {
		return Objects.hash(file, multithreading);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		return Objects.equals(file, other.file) && multithreading == other.multithreading;
	}
}
