package usantatecla.tictactoe.models;

import usantatecla.tictactoe.types.Color;
import usantatecla.tictactoe.types.Coordinate;

class Memento {

    private Color activeColor;
    private char[] boardColors;

    public Memento(char[] boardColors, Color activeColor ) {
        this.boardColors = boardColors;
        this.activeColor = activeColor;
    }

    Board getBoard() {
        Board board = new Board();
        int colorsCount = 0;
        for (int i = 0; i < Coordinate.DIMENSION; i++) {
            for (int j = 0; j < Coordinate.DIMENSION; j++) {
                board.putToken(new Coordinate(i, j), Color.parse(boardColors[colorsCount++]));
            }
        }
        return board;
    }

    Color getActiveColor() {
        return this.activeColor;
    }

}
