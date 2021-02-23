package usantatecla.tictactoe.views.console;

import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.utils.views.Menu;

public class PlayMenu extends Menu {

    PlayMenu(PlayController playController) {
        this.addCommand(new PlayCommand(playController));
        this.addCommand(new UndoCommand(playController));
        this.addCommand(new RedoCommand(playController));
    }

}