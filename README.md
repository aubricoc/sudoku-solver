# Sudoku Solver

Library to solve sudoku problems.

Execute jar as:

```bash
java -jar sudoku.jar -p n filename
```

Where:
- **-p n** : Indicates if it will run on multithreading mode:
    - **-p 0** : Single thread mode. By default.
    - **-p 1** : Multithreading mode.
- **filename** : File with the Sudoku to solve.

Sudoku solved will be printed to standard output.