package usantatecla.tictactoe.views.graphics;

import usantatecla.tictactoe.models.Game;
import usantatecla.tictactoe.views.Message;

import javax.swing.*;
import java.awt.*;

public class StartView extends JFrame {

    private Game game;

    StartView(Game game) {
        super(Message.TITLE.toString());
        assert game != null;
        this.game = game;
        this.getContentPane().setLayout(new GridBagLayout());
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    void interact() {
        GameView gameView;
        gameView = new GameView(this.game);
        gameView.write();
        this.getContentPane().add(gameView, new Constraints(0, 0, 3, 1));
        this.setVisible(false);
    }

}