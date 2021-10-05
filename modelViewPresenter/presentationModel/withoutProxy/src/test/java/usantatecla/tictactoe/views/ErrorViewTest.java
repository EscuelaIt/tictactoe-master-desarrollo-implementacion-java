package usantatecla.tictactoe.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.tictactoe.types.Error;
import usantatecla.utils.Console;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class ErrorViewTest {

    @Mock
    private Error error;

    @InjectMocks
    private ErrorView errorView;

    @Mock
    private Console console;

    @Captor
    private ArgumentCaptor<String> captor;

    @BeforeEach
    void before() {
        openMocks(this);
    }

    @Test
    void testGivenNewGameViewWhenWriteNullErrorThenNeverPrint() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            console.when(Console::getInstance).thenReturn(this.console);
            this.errorView.writeln();
            verify(this.console, never()).writeln("");
        }
    }

    @Test
    void testGivenNewGameViewWhenWriteNotNullErrorThenPrintErrorMessage() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            when(this.error.ordinal()).thenReturn(0);
            console.when(Console::getInstance).thenReturn(this.console);
            this.errorView.writeln();
            verify(this.console).writeln(captor.capture());
            assertThat(captor.getValue(), is("The square is not empty"));
        }
    }

}
