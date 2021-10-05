package usantatecla.tictactoe.views;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.utils.Console;

import usantatecla.tictactoe.models.Session;

@ExtendWith(MockitoExtension.class)
public class CoordinateViewTest {
    static final String ENTER_COORDINATE_TO_PUT = "Enter a coordinate to put a token:";

    @Mock
    Console console;

    @Mock
    private PlayController playController;

    @InjectMocks
    CoordinateView coordinateView = new CoordinateView(new PlayController(new Session(null), null));

    @BeforeEach
    void before() {
        openMocks(this);
    }

    @Test
    public void testGivenNewCoordinateViewWhenReadCoordinateThenIsCorrect() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt(anyString())).thenReturn(2,1);
            when(this.playController.isCoordinateValid(any())).thenReturn(true);
            Coordinate coordinateRead = this.coordinateView.read(ENTER_COORDINATE_TO_PUT);
            Coordinate coordinateExpected = new Coordinate(1, 0);
            assertThat(coordinateRead.getRow(), is(coordinateExpected.getRow()));
            assertThat(coordinateRead.getColumn(), is(coordinateExpected.getColumn()));
        }
    }

    @Test

    public void testGivenNewCoordinatesWhenRow4AndColumn4ThenAssertionException() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt("Row: ")).thenReturn(4);
            when(this.console.readInt("Column: ")).thenReturn(4);
            Assertions.assertThrows(AssertionError.class, () -> {
                this.coordinateView.read("Title");
            });
            verify(this.console).readInt("Row: ");
            verify(this.console).readInt("Column: ");
        }
    }

    @Test
    public void testGivenNewCoordinatesWhenRow0AndColumn0ThenAssertionException() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt("Row: ")).thenReturn(0);
            when(this.console.readInt("Column: ")).thenReturn(0);
            Assertions.assertThrows(AssertionError.class, () -> {
                this.coordinateView.read("Title");
            });
            verify(this.console).readInt("Row: ");
            verify(this.console).readInt("Column: ");
        }
    }

    @Test
    void testGivenNewCoordinateViewWhenReadCoordinateThenReturnCoordinate() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            when(this.console.readInt(anyString())).thenReturn(1);
            when(this.playController.isCoordinateValid(any(Coordinate.class))).thenReturn(true);
            Coordinate coordinate = this.coordinateView.read("");
            verify(this.console).writeln("");
            assertThat(coordinate, is(new Coordinate(0, 0)));
        }
    }

    @Test
    void testGivenNewCoordinateViewWhenReadInvalidCoordinateThenReadValidCoordinateAndReturnValidCoordinate() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            when(this.console.readInt(anyString())).thenReturn(4, 1);
            when(this.playController.isCoordinateValid(any(Coordinate.class))).thenReturn(true);
            console.when(Console::getInstance).thenReturn(this.console);
            Coordinate coordinate = this.coordinateView.read("");
            verify(this.console, times(1)).writeln("");
            verify(this.console, times(2)).readInt(anyString());
            assertThat(coordinate, is(new Coordinate(3, 0)));
        }
    }
}
