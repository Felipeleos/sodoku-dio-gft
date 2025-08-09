package com.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testSetAndGetCell() {
        Board b = new Board();
        b.setCell(0,0,5,true);
        assertEquals(5, b.getCell(0,0).getValue());
        assertTrue(b.getCell(0,0).isFixed());
    }
}
