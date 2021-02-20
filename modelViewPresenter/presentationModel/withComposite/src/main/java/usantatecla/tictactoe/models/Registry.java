package usantatecla.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

class Registry {

    private List<Memento> mementos;
    private Game game;
    private int firstPrevious;

    Registry(Game game) {
        this.game = game;
        this.reset();
    }

     void reset() {
        this.firstPrevious = 0;
        this.mementos = new ArrayList<>();
        this.mementos.add(this.firstPrevious, this.game.createMemento());
    }

    void register() {
        for (int i = 0; i < this.firstPrevious; i++) {
            this.mementos.remove(0);
            this.firstPrevious--;
        }
        this.mementos.add(this.firstPrevious, this.game.createMemento());
    }

    void undo() {
        this.firstPrevious++;
        this.game.setMemento(this.mementos.get(this.firstPrevious));
    }

    void redo() {
        this.firstPrevious--;
        this.game.setMemento(this.mementos.get(this.firstPrevious));
    }

    boolean undoable() {
        return this.firstPrevious < this.mementos.size() - 1;
    }

    boolean redoable() {
        return this.firstPrevious >= 1;
    }

}
