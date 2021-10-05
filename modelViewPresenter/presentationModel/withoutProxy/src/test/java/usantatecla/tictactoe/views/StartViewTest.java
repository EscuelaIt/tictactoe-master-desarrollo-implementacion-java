package usantatecla.tictactoe.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import usantatecla.tictactoe.controllers.StartController;
import usantatecla.utils.Console;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class StartViewTest {

    @Mock
    private StartController startController;

    @InjectMocks
    private StartView startView;

    @Mock
    private Console console;

    @BeforeEach
    void before() {
        openMocks(this);
    }

    @Test
    void testGivenNewStartViewWhenReadNumberOfUsersThenGameSetNumberOfUsers() {
        try (MockedStatic<Console> console = mockStatic(Console.class)) {
            when(this.console.readInt(anyString())).thenReturn(1);
            console.when(Console::getInstance).thenReturn(this.console);
            this.startView.interact(startController);
            verify(this.console).writeln(MessageView.START_GAME.getMessage());
            verify(this.startController).createPlayers(1);
        }
    }
}
