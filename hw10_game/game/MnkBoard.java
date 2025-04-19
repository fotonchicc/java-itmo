package game;

import java.util.Arrays;
import java.util.Map;

public class MnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;
    private final int m;
    private final int n;
    private final int k;
    private final String shape;

    public MnkBoard(int m, int n, int k, String shape) {
        this.cells = new Cell[m][n];
        this.m = m;
        this.n = n;
        this.k = k;
        this.shape = shape;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private Result checkRow() {
        int empty = 0;
        for (int u = 0; u < this.m; u++) {
            int inRow = 0;
            for (int v = 0; v < this.n; v++) {
                if (cells[u][v] == turn) {
                    inRow++;
                    if (inRow >= this.k) {
                        return Result.WIN;
                    }
                } else {
                    inRow = 0;
                }
                if (cells[u][v] == Cell.E) {
                    empty++;
                }
            }
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    private boolean checkCol() {
        for (int u = 0; u < this.n; u++) {
            int inCol = 0;
            for (int v = 0; v < this.m; v++) {
                if (cells[v][u] == turn) {
                    inCol++;
                    if (inCol >= this.k) {
                        return true;
                    }
                } else {
                    inCol = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return checkMainDiagonals() || checkAntiDiagonals();
    }

    private boolean checkMainDiagonals() {
        // Двигаемся вправо вниз
        // Проверяем фигуру выше главной диагонали
        for (int i = 0; this.n - i >= k; i++) {
            int count = 0;
            for (int j = 0; j + i < this.m && j < this.n; j++) {
                int r = i + j;
                if (cells[j][r] == turn) {
                    count++;
                    if (count >= this.k) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        // Проверяем фигуру ниже главной диагонали
        for (int i = 1; this.m - i >= k; i++) {
            int count = 0;
            for (int j = 0; j + i < this.m && j < this.n; j++) {
                int r = i + j;
                if (cells[r][j] == turn) {
                    count++;
                    if (count >= this.k) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkAntiDiagonals() {
        // Двигаемся вправо вверх
        // Проверяем фигуру выше побочной диагонали
        for (int i = this.m - 1; i >= this.k - 1; i--) {
            int count = 0;
            for (int j = 0; i - j >= 0 && this.n - j >= this.k; j++) {
                int r = i - j;
                if (cells[r][j] == turn) {
                    count++;
                    if (count >= this.k) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        // Проверяем фигуру ниже побочной диагонали
        for (int i = 1; i <= this.n - this.k; i++) {
            int count = 0;
            for (int r = i, j = this.m - 1; r < this.n && j >= 0; r++, j--) {
                if (cells[j][r] == turn) {
                    count++;
                    if (count >= this.k) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();

        Result resultRow = checkRow();
        if (resultRow != Result.UNKNOWN) {
            return resultRow;
        }
        if (checkCol()) {
            return Result.WIN;
        }
        if (checkDiagonals()) {
            return Result.WIN;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < this.m
                && 0 <= move.getColumn() && move.getColumn() < this.n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    public String toStringSquare() {
        final StringBuilder sb = new StringBuilder("   ");
        for (int i = 1; i < this.n + 1; i++) {
            sb.append(i);
        }
        sb.append(System.lineSeparator()).append("  +").append("-".repeat(n)).append(System.lineSeparator());
        for (int r = 0; r < this.m; r++) {
            if (r < 9) {
                sb.append(" ").append(r + 1).append("|");
            } else {
                sb.append(r + 1).append("|");
            }
            for (int c = 0; c < this.n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    private String toStringDiamond() {
        StringBuilder sb = new StringBuilder();
        int size = 2 * this.n - 1; // Размер ромба

        // Заполняем ромб
        String[][] diamond = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                diamond[i][j] = " ";
            }
        }
        // Перемещаем элементы массива в ромб
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                // Преобразуем координаты массива в координаты ромба
                int x = i + j;
                int y = (this.n - 1) - i + j;
                diamond[x][y] = String.valueOf(SYMBOLS.get(cells[i][j]));
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(diamond[i][j]);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        String board;
        if (this.shape.equals("d")) {
            board = toStringDiamond();
        } else {
            board = toStringSquare();
        }
        return board;
    }

    @Override
    public int getM() {
        return this.m;
    }

    @Override
    public int getN() {
        return this.n;
    }
}

