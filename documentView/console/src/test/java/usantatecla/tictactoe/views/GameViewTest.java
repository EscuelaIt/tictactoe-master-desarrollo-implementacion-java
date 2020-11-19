package usantatecla.tictactoe.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.models.Game;
import usantatecla.tictactoe.models.Token;
import usantatecla.utils.Console;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class GameViewTest {

    @Mock
    private Game game;

    @InjectMocks
    private GameView gameView;

    @Mock
    private Console console;

    @Captor
    private ArgumentCaptor<String> captor;

    @BeforeEach
    void before() {
        openMocks(this);
    }

    @Test
    void testGivenNewGameViewWhenWriteThenPrintBoard() {
        try (MockedStatic console = mockStatic(Console.class)) {
            when(this.game.getToken(any(Coordinate.class))).thenReturn(Token.X);
            console.when(Console::getInstance).thenReturn(this.console);
            this.gameView.write();
            verify(this.console, times(2)).writeln(Message.SEPARATOR.toString());
            verify(this.console, times(3)).write(Message.VERTICAL_LINE_LEFT.toString());
            verify(this.console, times(9)).write(Message.VERTICAL_LINE_CENTERED.toString());
            verify(this.console, times(3)).writeln(Message.VERTICAL_LINE_RIGHT.toString());
            verify(this.console, times(21)).write(captor.capture());
            assertThat(captor.getAllValues().toString(), is("[| , X,  | , X,  | , X,  | , " +
                                                                   "| , X,  | , X,  | , X,  | , " +
                                                                   "| , X,  | , X,  | , X,  | ]"));
        }
    }

}
