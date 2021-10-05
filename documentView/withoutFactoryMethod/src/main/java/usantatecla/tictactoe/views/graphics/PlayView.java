package usantatecla.tictactoe.views.graphics;

import usantatecla.tictactoe.models.Game;
import usantatecla.tictactoe.types.Coordinate;
import usantatecla.tictactoe.types.Error;
import usantatecla.tictactoe.views.ErrorView;
import usantatecla.tictactoe.views.Message;

import javax.swing.*;
import java.awt.*;

class PlayView extends JFrame {

    static final String ENTER_COORDINATE_TO_PUT = "Enter a coordinate to put a token:";
    static final String ENTER_COORDINATE_TO_REMOVE = "Enter a coordinate to remove a token:";
    private CoordinatePutView coordinatePutView;
    private CoordinateMoveView coordinateMoveView;
    private Game game;
    private GameView gameView;

    PlayView(Game game) {
        super(Message.TITLE.toString());
        assert game != null;

        this.game = game;
        this.getContentPane().setLayout(new GridBagLayout());
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameView = new GameView(game);
        this.getContentPane().add(this.gameView);
        this.gameView.write();
        this.setVisible(true);
        this.coordinatePutView = new CoordinatePutView();
        this.coordinateMoveView = new CoordinateMoveView();
        this.getContentPane().add(this.coordinatePutView, new Constraints(0, 1, 3, 1));
        this.gameView.removeAll();
        this.setVisible(true);
    }

    void interact() {
        this.gameView.removeAll();
        this.gameView.write();
        this.setVisible(true);
        do {
            if (!this.game.areAllTokensOnBoard()) {
                this.put();
            } else {
                this.move();
            }
            this.game.next();
            this.gameView.write();
            this.setVisible(true);
        } while (!this.game.isTicTacToe());
        this.getContentPane().add(new JLabel(this.game.getActiveColor().toString() + Message.PLAYER_WIN.toString()),
                new Constraints(0, 1, 4, 1));
        this.setVisible(true);
    }

    private void put() {
        Coordinate coordinate;
        Error error;
        do {
            this.setVisible(true);
            do {
                coordinate = this.coordinatePutView.getCoordinate();
                System.out.println("");
            } while (coordinate.isNull());
            error = this.game.getPutTokenError(coordinate);
            if (!error.isNull()) {
                JOptionPane.showMessageDialog(null, ErrorView.MESSAGES[error.ordinal()], "ERROR",
                        JOptionPane.WARNING_MESSAGE);
                this.coordinatePutView.resetCoordinate();
            }
        } while (!error.isNull());
        this.game.putToken(coordinate);
        this.coordinatePutView.resetCoordinate();
    }

    private void move() {
        this.getContentPane().remove(this.getComponentCount());
        this.getContentPane().add(this.coordinateMoveView, new Constraints(0, 1, 3, 1));
        Error originError;
        Error targetError;
        Coordinate[] coordinates;
        do {
            this.setVisible(true);
            do {
                coordinates = this.coordinateMoveView.getCoordinates();
                System.out.println("");
            } while (coordinates[0].isNull() || coordinates[1].isNull());
            originError = this.game.getOriginMoveTokenError(coordinates[0]);
            targetError = this.game.getTargetMoveTokenError(coordinates[0], coordinates[1]);
            if (!originError.isNull()) {
                JOptionPane.showMessageDialog(null, ErrorView.MESSAGES[originError.ordinal()], "ERROR",
                        JOptionPane.WARNING_MESSAGE);
                this.coordinateMoveView.resetCoordinates();
            }
            if (!targetError.isNull()) {
                JOptionPane.showMessageDialog(null, ErrorView.MESSAGES[targetError.ordinal()], "ERROR",
                        JOptionPane.WARNING_MESSAGE);
                this.coordinateMoveView.resetCoordinates();
            }
        } while (!originError.isNull() || !targetError.isNull());
        this.game.moveToken(coordinates[0], coordinates[1]);
        this.coordinateMoveView.resetCoordinates();
    }

}