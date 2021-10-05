package usantatecla.tictactoe.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.types.PlayerType;
import usantatecla.utils.Console;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class PlayViewTest {

    @Mock
    private PlayController playController;

    @InjectMocks
    private PlayView playView;

    @Mock
    private Console console;

    @BeforeEach
    void before() {
        openMocks(this);
        this.playView = spy(this.playView);
    }

    @Test
    void testGivenNewPlayViewWhenUserPlayerPutCoordinateThenGamePutCoordinate() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            when(this.playController.isBoardComplete()).thenReturn(false);
            when(this.playController.getTypeOfTokenPlayerFromTurn()).thenReturn(PlayerType.USER_PLAYER);
            when(this.console.readInt(anyString())).thenReturn(1);
            when(this.playController.isCoordinateValid(any(Coordinate.class))).thenReturn(true);
            when(this.playController.isTicTacToe()).thenReturn(true);
            console.when(Console::getInstance).thenReturn(this.console);
            this.playView.interact(this.playController);
            verify(this.playController).putTokenPlayerFromTurn(new Coordinate(0, 0));
            verify(this.console).writeln(MessageView.PLAYER_WIN.getMessage());
        }
    }

    @Test
    void testGivenNewPlayViewWhenMachinePlayerPutCoordinateThenGamePutCoordinate() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            Coordinate coordinate = new Coordinate(0, 0);
            when(this.playController.isBoardComplete()).thenReturn(false);
            when(this.playController.getTypeOfTokenPlayerFromTurn()).thenReturn(PlayerType.MACHINE_PLAYER);
            when(this.playController.generateRandomCoordinate()).thenReturn(coordinate);
            when(this.playController.isTicTacToe()).thenReturn(true);
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt(anyString())).thenReturn(1);
            this.playView.interact(this.playController);
            verify(this.playController).putTokenPlayerFromTurn(coordinate);
            verify(this.console).writeln(MessageView.PLAYER_WIN.getMessage());
        }
    }

    @Test
    void testGivenNewPlayViewWhenUserPlayerMoveOriginToTargetThenGameMoveOriginToTarget() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            Coordinate[] coordinates = {new Coordinate(0, 1), new Coordinate(1, 1)};
            when(this.playController.isBoardComplete()).thenReturn(true);
            when(this.playController.getTypeOfTokenPlayerFromTurn()).thenReturn(PlayerType.USER_PLAYER);
            when(this.playController.isCoordinateValid(any(Coordinate.class))).thenReturn(true);
            when(this.playController.isTicTacToe()).thenReturn(true);
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt(anyString())).thenReturn(1, 1, 2, 2);
            this.playView.interact(this.playController);
            verify(this.playController).moveTokenPlayerFromTurn(coordinates);
            verify(this.console).writeln(MessageView.PLAYER_WIN.getMessage());
        }
    }

    @Test
    void testGivenNewPlayViewWhenMachinePlayerMoveOriginToTargetThenGameMoveOriginToTarget() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            Coordinate[] coordinates = {new Coordinate(0, 0), new Coordinate(1, 1)};
            when(this.playController.isBoardComplete()).thenReturn(true);
            when(this.playController.getTypeOfTokenPlayerFromTurn()).thenReturn(PlayerType.MACHINE_PLAYER);
            when(this.playController.isTicTacToe()).thenReturn(true);
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.playController.generateRandomCoordinate()).thenReturn(coordinates[0], coordinates[1]);
            when(this.console.readInt(anyString())).thenReturn(1, 1, 2, 2);
            this.playView.interact(this.playController);
            verify(this.playController).moveTokenPlayerFromTurn(coordinates);
            verify(this.console).writeln(MessageView.PLAYER_WIN.getMessage());
        }
    }

}
