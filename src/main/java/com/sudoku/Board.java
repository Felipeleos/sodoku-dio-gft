package com.sudoku;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Cell[][] grid = new Cell[9][9];

    public Board() {
        for (int r=0;r<9;r++){
            for (int c=0;c<9;c++){
                grid[r][c] = new Cell(r,c,0,false);
            }
        }
    }

    public void setCell(int row, int col, int value, boolean fixed) {
        grid[row][col] = new Cell(row, col, value, fixed);
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r=0;r<9;r++){
            if (r % 3 == 0) sb.append("+-------+-------+-------+\n");
            for (int c=0;c<9;c++){
                if (c % 3 == 0) sb.append("| ");
                Cell cell = grid[r][c];
                String s = cell.toString();
                if (cell.isFixed()) {
                    sb.append("[").append(s).append("] ");
                } else {
                    sb.append(" ").append(s).append("  ");
                }
            }
            sb.append("|\n");
        }
        sb.append("+-------+-------+-------+\n");
        return sb.toString();
    }

    public boolean isValid() {
        // simple validation: check rows, cols and 3x3 blocks for duplicate non-zero values
        for (int i=0;i<9;i++){
            if (hasDuplicates(getRowValues(i))) return false;
            if (hasDuplicates(getColValues(i))) return false;
        }
        for (int br=0;br<3;br++){
            for (int bc=0;bc<3;bc++){
                if (hasDuplicates(getBlockValues(br,bc))) return false;
            }
        }
        return true;
    }

    private List<Integer> getRowValues(int r) {
        List<Integer> vals = new ArrayList<>();
        for (int c=0;c<9;c++) vals.add(grid[r][c].getValue());
        return vals;
    }

    private List<Integer> getColValues(int c) {
        List<Integer> vals = new ArrayList<>();
        for (int r=0;r<9;r++) vals.add(grid[r][c].getValue());
        return vals;
    }

    private List<Integer> getBlockValues(int br, int bc) {
        List<Integer> vals = new ArrayList<>();
        int row0 = br*3;
        int col0 = bc*3;
        for (int r=row0;r<row0+3;r++){
            for (int c=col0;c<col0+3;c++){
                vals.add(grid[r][c].getValue());
            }
        }
        return vals;
    }

    private boolean hasDuplicates(List<Integer> vals) {
        boolean[] seen = new boolean[10];
        for (int v: vals) {
            if (v==0) continue;
            if (v<0 || v>9) return true;
            if (seen[v]) return true;
            seen[v] = true;
        }
        return false;
    }
}
