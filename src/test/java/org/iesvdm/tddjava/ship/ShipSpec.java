package org.iesvdm.tddjava.ship;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
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
        obstacles.add(new Point(21, 13));
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
        Location location1 = new Location(new Point(21, 12), Direction.NORTH);
        String orden="f";
        assertEquals(barco.receiveCommands(orden), "O");
        assertEquals(barco.getLocation(),location1);


    }

    public void whenReceiveCommandsBThenBackward() {
        Ship barco=new Ship(location,planet);
        Location location1 = new Location(new Point(21, 14), Direction.NORTH);
        String orden="b";

        assertEquals(barco.receiveCommands(orden),"O");
        assertEquals(barco.getLocation(),location1);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Ship barco=new Ship(location,planet);
        Location location1 = new Location(new Point(21, 13), Direction.WEST);
        String orden="l";

        assertEquals(barco.receiveCommands(orden),"O");
        assertEquals(barco.getLocation(),location1);

    }

    public void whenReceiveCommandsRThenTurnRight() {
        Ship barco=new Ship(location,planet);
        Location location1 = new Location(new Point(21, 13), Direction.EAST);
        String orden="r";

        assertEquals(barco.receiveCommands(orden),"O");
        assertEquals(barco.getLocation(),location1);

    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        Ship barco=new Ship(location,planet);
        String orden="f";
        assertEquals(barco.receiveCommands(orden),"O");
        String orden1="b";
        assertEquals(barco.receiveCommands(orden),"O");
        String orden2="l";
        assertEquals(barco.receiveCommands(orden),"O");
        String orden3="r";
        assertEquals(barco.receiveCommands(orden),"O");

    }

    public void whenInstantiatedThenPlanetIsStored() {
//        Point max = new Point(50, 50);
//        Planet planet = new Planet(max);
//        ship = new Ship(location, planet);
        Point max = new Point(55, 55);
        Planet planet = new Planet(max);
        location = new Location(new Point(20, 10), Direction.NORTH);
        Ship barco=new Ship(location,planet);

        assertEquals(barco.getLocation(),location);
        assertEquals(barco.getPlanet(),planet);
    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        List<Point> obstacles = new ArrayList<>();
        Point max = new Point(55, 55);
        obstacles.add(new Point(12, 36));
        Planet planet= new Planet(max, obstacles);
        location = new Location(new Point(55, 10), Direction.EAST);
        Ship barco=new Ship(location,planet);
        Location locdev = new Location(new Point(1, 10), Direction.EAST);
        String orden="f";

        assertEquals(barco.receiveCommands(orden),"O");
        assertEquals(locdev,barco.getLocation());
        assertEquals(barco.getLocation(),locdev);


    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        Location locdev = new Location(new Point(1, 10), Direction.EAST);
        String orden="b";
        location = new Location(new Point(55, 10), Direction.EAST);
        Ship barco=new Ship(location,planet);

        assertEquals(barco.receiveCommands(orden),"O");
        assertEquals(locdev,barco.getLocation());
        assertEquals(barco.getLocation(),locdev);

    }
    // este tampoco lo consigo
    public void whenReceiveCommandsThenStopOnObstacle() {
//        List<Point> obstacles = new ArrayList<>();
//        obstacles.add(new Point(44, 44));
//        obstacles.add(new Point(45, 46));
//        obstacles.add(new Point(21, 13));
        Ship barco=new Ship(location,planet);
        String orden="l";

        assertEquals(barco.receiveCommands(orden),"O");
    }
    // no consigo este
    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(20, 12));
        location = new Location(new Point(55, 10), Direction.EAST);
        Ship barco=new Ship(location,planet);
        String orden="r";

        assertEquals(barco.receiveCommands(orden),"X");


    }
    }


