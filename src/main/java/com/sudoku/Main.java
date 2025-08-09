package com.sudoku;

import java.util.Scanner;

/**
 * Main CLI entrypoint.
 *
 * Usage:
 * java -cp target/classes com.sudoku.Main "0,0;4,false 1,0;7,false ..."
 *
 * Token format: col,row;value,isFixed
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        if (args.length == 0) {
            System.out.println("Nenhum argumento informado. Iniciando demo com tabuleiro vazio.");
            System.out.println(board);
            interactiveLoop(board);
            return;
        }
        // Join all args into a single string (in case the shell splits)
        String joined = String.join(" ", args).trim();
        parseAndPopulate(board, joined);
        System.out.println("Tabuleiro inicial:");
        System.out.println(board);
        System.out.println("Validade inicial: " + (board.isValid() ? "OK" : "INVÁLIDO"));
        interactiveLoop(board);
    }

    private static void parseAndPopulate(Board board, String input) {
        // tokens separated by whitespace
        String[] tokens = input.split("\\s+");
        for (String t: tokens) {
            if (t.isBlank()) continue;
            // expected: a,b;c,d  -> coords ; value,fixed
            try {
                String[] parts = t.split(";");
                if (parts.length != 2) continue;
                String[] coords = parts[0].split(",");
                String[] valfix = parts[1].split(",");
                if (coords.length != 2 || valfix.length != 2) continue;
                // THE ORIGINAL EXERCISE PROVIDES tokens like 0,0;4,false => (col,row;value,isFixed)
                int col = Integer.parseInt(coords[0]);
                int row = Integer.parseInt(coords[1]);
                int value = Integer.parseInt(valfix[0]);
                boolean fixed = Boolean.parseBoolean(valfix[1]);
                if (row>=0 && row<9 && col>=0 && col<9) {
                    board.setCell(row, col, value, fixed);
                }
            } catch (Exception e) {
                // ignore malformed token
            }
        }
    }

    private static void interactiveLoop(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modo interativo: comandos: set r c v | print | check | exit");
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line == null) break;
            String[] parts = line.trim().split("\\s+");
            if (parts.length==0) continue;
            String cmd = parts[0].toLowerCase();
            if (cmd.equals("exit") || cmd.equals("quit")) {
                System.out.println("Saindo...");
                break;
            } else if (cmd.equals("print")) {
                System.out.println(board);
            } else if (cmd.equals("check")) {
                System.out.println(board.isValid() ? "Sem conflitos detectados." : "Conflitos detectados!");
            } else if (cmd.equals("set")) {
                if (parts.length < 4) {
                    System.out.println("Uso: set <row> <col> <value>");
                    continue;
                }
                try {
                    int r = Integer.parseInt(parts[1]);
                    int c = Integer.parseInt(parts[2]);
                    int v = Integer.parseInt(parts[3]);
                    if (r<0||r>8||c<0||c>8||v<0||v>9) {
                        System.out.println("Valores fora do intervalo.");
                        continue;
                    }
                    Cell cell = board.getCell(r,c);
                    if (cell.isFixed()) {
                        System.out.println("Célula fixa — não pode alterar.");
                    } else {
                        cell.setValue(v);
                        System.out.println("Atualizado.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Argumentos inválidos.");
                }
            } else {
                System.out.println("Comando desconhecido: " + cmd);
            }
        }
        scanner.close();
    }
}
