package usantatecla.tictactoe.models;

import org.junit.jupiter.api.Test;
import usantatecla.tictactoe.types.Token;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GameTest {

    @Test
    void testGivenNewGameWhenIsBoardCompleteThenIsFalse() {
        Game game = new GameBuilder().build();
        assertThat(game.isBoardComplete(), is(false));
    }

    @Test
    void testGivenCompleteBoardGameWhenIsBoardCompleteThenIsTrue() {
        Game game = new GameBuilder().rows("XXX",
                "OOO",
                "   ").build();
        assertThat(game.isBoardComplete(), is(true));
    }

    @Test
    void testGivenNewGameWhenIsTicTacToeThenIsFalse() {
        Game game = new GameBuilder().build();
        assertThat(game.isTicTacToe(), is(false));
    }

    @Test
    void testGivenGameWithXTokenWhenGetTokenThenIsOToken() {
        Game game = new GameBuilder().rows("X  ",
                "OX ",
                " O ").build();
        assertThat(game.getToken(new Coordinate(1, 0)), is(Token.O));
    }

    @Test
    void testGivenNewGameWhenGetTokenCoordinateThenIsNullToken() {
        Game game = new GameBuilder().build();
        assertThat(game.getToken(new Coordinate(0, 0)), is(Token.NULL));
    }

    @Test
    void testGivenGameWithXTokenWhenGetTokenCoordinateThenIsXToken() {
        Game game = new GameBuilder().rows("X  ",
                "   ",
                "   ").build();
        assertThat(game.getToken(new Coordinate(0, 0)), is(Token.X));
    }

    @Test
    void testGivenGameWithOTokenWhenGetTokenCoordinateThenIsOToken() {
        Game game = new GameBuilder().rows("X O",
                "X O",
                "   ").build();
        System.out.println(game.getToken(new Coordinate(0, 0)));
        System.out.println(game.getToken(new Coordinate(1, 2)));
        assertThat(game.getToken(new Coordinate(1, 2)), is(Token.O));
    }

    @Test
    void testGivenNewGameWhenEqualsThenIsTrue() {
        Game game = new GameBuilder().users(2).rows("XO ",
                " X ",
                "   ").build();
        assertThat(game.equals(game), is(true));
    }

}
