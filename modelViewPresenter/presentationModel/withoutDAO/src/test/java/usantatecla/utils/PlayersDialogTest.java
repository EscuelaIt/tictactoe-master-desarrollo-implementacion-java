package usantatecla.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayersDialogTest {

    @Mock
    private Console console;

    @InjectMocks
    private PlayersDialog playersDialog = new PlayersDialog();

    @Test
    public void testGivenNewPlayersDialogWhenNumberOfPlayersAreZeroThenIsCorrect() {
        when(this.console.readInt("Number of users [0-" + 2 + "] ")).thenReturn(0);
        assertEquals(0, this.playersDialog.read(2));
        verify(this.console).readInt("Number of users [0-" + 2 + "] ");
    }

    @Test
    public void testGivenNewPlayersDialogWhenNumberOfPlayersAreTwoThenIsCorrect() {
        when(this.console.readInt("Number of users [0-" + 2 + "] ")).thenReturn(2);
        assertEquals(2, this.playersDialog.read(2));
        verify(this.console).readInt("Number of users [0-" + 2 + "] ");
    }

    @Test
    public void testGivenNewPlayersDialogWhenNumberOfPlayersAreNegativeThenIsIncorrect() {
        when(this.console.readInt("Number of users [0-" + 2 + "] ")).thenReturn(-1);
        Assertions.assertThrows(AssertionError.class, () ->
            this.playersDialog.read(2));
        verify(this.console).readInt("Number of users [0-" + 2 + "] ");
    }

    @Test
    public void testGivenNewPlayersDialogWhenNumberOfPlayersAreThreeThenIsIncorrect() {
        when(this.console.readInt("Number of users [0-" + 2 + "] ")).thenReturn(3);
        Assertions.assertThrows(AssertionError.class, () ->
            this.playersDialog.read(2));
        verify(this.console).readInt("Number of users [0-" + 2 + "] ");
    }
    
}