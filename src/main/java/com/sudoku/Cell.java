package com.sudoku;

public class Cell {
    private int row;
    private int col;
    private int value; // 0 means empty
    private boolean fixed;

    public Cell(int row, int col, int value, boolean fixed) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.fixed = fixed;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getValue() { return value; }
    public boolean isFixed() { return fixed; }

    public void setValue(int value) {
        if (!fixed) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return value == 0 ? "." : Integer.toString(value);
    }
}
