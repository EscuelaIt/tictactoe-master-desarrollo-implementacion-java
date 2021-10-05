package usantatecla.tictactoe.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import usantatecla.utils.Console;
import usantatecla.utils.Direction;

public class CoordinateTest {

    private Coordinate coordinate00;
    private Coordinate coordinate01;
    private Coordinate coordinate11;
    private Coordinate coordinate02;

    @Mock
    private Console console;

    @InjectMocks
    private Coordinate coordinate = new Coordinate();

    public CoordinateTest() {
        this.coordinate00 = new Coordinate(0, 0);
        this.coordinate01 = new Coordinate(0, 1);
        this.coordinate11 = new Coordinate(1, 1);
        this.coordinate02 = new Coordinate(0, 2);
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareCoordinates00And01ThenIsHorizontal() {
        assertThat(this.coordinate00.getDirection(this.coordinate01), is(Direction.HORIZONTAL));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareCoordinates01And11ThenIsVertical() {
        assertThat(this.coordinate01.getDirection(this.coordinate11), is(Direction.VERTICAL));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareCoordinates00And11ThenIsMainDiagonal() {
        assertThat(this.coordinate00.getDirection(this.coordinate11), is(Direction.MAIN_DIAGONAL));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareCoordinates02And11ThenIsInverseDiagonal() {
        assertThat(this.coordinate02.getDirection(this.coordinate11), is(Direction.INVERSE_DIAGONAL));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareCoordinates00And12ThenDirectionIsNull() {
        //assertThat(this.coordinate00.getDirection(this.coordinate12), is(null));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareTwoCoordinateEqualsThenIsTrue() {
        Coordinate coordinate00Copy = new Coordinate(0, 0);
        assertThat(this.coordinate00.equals(coordinate00Copy), is(true));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareTwoCoordinateNotEqualsRowThenIsTrue() {
        Coordinate coordinate01Copy = new Coordinate(1, 0);
        assertThat(!this.coordinate00.equals(coordinate01Copy), is(true));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareTwoCoordinateNotEqualsColumnThenIsTrue() {
        Coordinate coordinate01Copy = new Coordinate(0, 1);
        assertThat(!this.coordinate00.equals(coordinate01Copy), is(true));
    }

    @Test
    public void testGivenNewCoordinatesWhenCompareOneCoordinateWithAnObjectThenIsFalse() {
        assertThat(this.coordinate00.equals(new Object()), is(false));
    }
}
