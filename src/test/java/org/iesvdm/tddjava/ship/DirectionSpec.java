package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {
            assertEquals(Direction.getFromShortName('N'), (Direction.NORTH));
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {
        assertEquals(Direction.getFromShortName('W'), (Direction.WEST) );
    }

    public void whenGetFromShortNameBThenReturnNone() {
        assertEquals(Direction.getFromShortName('B'), (Direction.NONE) );
    }

    public void givenSWhenLeftThenE() {
        assertEquals(Direction.EAST, Direction.SOUTH.turnLeft());
    }

    public void givenNWhenLeftThenW() {
        assertEquals(Direction.WEST, Direction.NORTH.turnLeft());
    }

    public void givenSWhenRightThenW() {
        assertEquals(Direction.WEST, Direction.SOUTH.turnRight());
    }

    public void givenWWhenRightThenN() {
        assertEquals(Direction.NORTH, Direction.WEST.turnRight());

    }

}
