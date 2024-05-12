package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);
        Ship barco=new Ship(location,planet);
        assertEquals(barco.getLocation(),location);

    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }


    public void whenMoveForwardThenForward() {
        Ship barco=new Ship(location,planet);
        assertTrue(barco.moveForward(),"f");

    }

    public void whenMoveBackwardThenBackward() {
        Ship barco=new Ship(location,planet);
        assertTrue(barco.moveBackward(),"b");

    }

    public void whenTurnLeftThenLeft() {
        Ship barco=new Ship(location,planet);
        Location location1 = new Location(new Point(21, 13), Direction.WEST);

        barco.turnLeft();
        assertEquals(barco.getLocation(),location1);


    }

    public void whenTurnRightThenRight() {
        Ship barco=new Ship(location,planet);
        Location location1 = new Location(new Point(21, 13), Direction.EAST);

        barco.turnRight();
        assertEquals(barco.getLocation(),location1);


    }

    public void whenReceiveCommandsFThenForward() {
        Ship barco=new Ship(location,planet);
        String orden="f";

        assertEquals(barco.receiveCommands(orden),"O");


    }

    public void whenReceiveCommandsBThenBackward() {
        Ship barco=new Ship(location,planet);
        String orden="b";

        assertEquals(barco.receiveCommands(orden),"O");
    }

    public void whenReceiveCommandsLThenTurnLeft() {

    }

    public void whenReceiveCommandsRThenTurnRight() {

    }

    public void whenReceiveCommandsThenAllAreExecuted() {

    }

    public void whenInstantiatedThenPlanetIsStored() {
//        Point max = new Point(50, 50);
//        Planet planet = new Planet(max);
//        ship = new Ship(location, planet);

    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {

    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {

    }

    public void whenReceiveCommandsThenStopOnObstacle() {
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {

    }

}
