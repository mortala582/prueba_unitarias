package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        Point punto=new Point(20,10);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto, dir);

        assertEquals(20,loc.getX());


    }

    public void whenInstantiatedThenYIsStored() {
        Point punto=new Point(20,10);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto, dir);

        assertEquals(10,loc.getY());

    }

    public void whenInstantiatedThenDirectionIsStored() {
        Point punto=new Point(20,10);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto, dir);

        assertEquals(Direction.SOUTH,loc.getDirection());

    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto, dir);

        assertTrue(loc.forward());
        assertEquals(9,loc.getY());
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto,dir);

        assertTrue(loc.forward());
        assertEquals(11,loc.getY());


    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.EAST;
        Location loc= new Location(punto,dir);

        assertTrue(loc.forward());
        assertEquals(21,loc.getX());

    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.WEST;
        Location loc= new Location(punto,dir);

        assertTrue(loc.forward());
        assertEquals(19,loc.getX());

    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);

        assertTrue(loc.backward());
        assertEquals(11,loc.getY());

    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto,dir);

        assertTrue(loc.backward());
        assertEquals(9,loc.getY());

    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.EAST;
        Location loc= new Location(punto,dir);

        assertTrue(loc.backward());
        assertEquals(19,loc.getX());

    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        Point punto=new Point(20,10);
        Direction dir=Direction.WEST;
        Location loc= new Location(punto,dir);

        assertTrue(loc.backward());
        assertEquals(21,loc.getX());

    }

    public void whenTurnLeftThenDirectionIsSet() {
        Point punto=new Point(20,10);
        Location loc= new Location(punto,Direction.NORTH);

        loc.turnLeft();
        assertEquals(Direction.WEST, loc.getDirection());
    }

    public void whenTurnRightThenDirectionIsSet() {
        Point punto=new Point(20,10);
        Location loc= new Location(punto,Direction.NORTH);

        loc.turnRight();
        assertEquals(Direction.EAST, loc.getDirection());

    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        Point punto=new Point(12,32);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);

        assertTrue(location.equals(loc));


    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Point punto=new Point(12,32);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);
        location.setDirection(dir);

        Point punto1=new Point(12,32);
        Direction dir1=Direction.SOUTH;
        Location loc1= new Location(punto1,dir1);

        assertFalse(location.equals(loc1));

    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Point punto1=new Point(20,32);
        Direction dir1=Direction.NORTH;
        Location loc1= new Location(punto1,dir1);

        assertFalse(location.equals(loc1));


    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Point punto1=new Point(12,10);
        Direction dir1=Direction.NORTH;
        Location loc1= new Location(punto1,dir1);

        assertFalse(location.equals(loc1));


    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Point punto=new Point(12,32);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);
        location.setDirection(dir);

        Point punto1=new Point(12,32);
        Direction dir1=Direction.SOUTH;
        Location loc1= new Location(punto1,dir1);

        assertFalse(location.equals(loc1));


    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Point punto=new Point(12,32);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);


        Point punto1=new Point(12,32);
        Location loc1= new Location(punto1,dir);

        assertTrue(location.equals(loc1));


    }

    public void whenCopyThenDifferentObject() {
        Point punto=new Point(12,32);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);

        Location retorno=loc.copy();

        Point punto1=new Point(20,32);
        Direction dir1=Direction.NORTH;
        Location loc1= new Location(punto1,dir1);

        Location retorno1=loc1.copy();

        assertFalse(retorno.equals(retorno1));

        }

    public void whenCopyThenEquals() {
        Point punto=new Point(12,32);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);

        Location retorno=loc.copy();

        Point punto1=new Point(12,32);
        Direction dir1=Direction.NORTH;
        Location loc1= new Location(punto1,dir1);

        Location retorno1=loc1.copy();

        assertTrue(retorno.equals(retorno1));

    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        Point punto=new Point(100,32);
        Direction dir=Direction.EAST;
        Location loc= new Location(punto,dir);


        assertTrue(loc.forward());
        assertEquals(1,loc.getX());


    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        Point punto=new Point(1,32);
        Direction dir=Direction.WEST;
        Location loc= new Location(punto,dir);


        assertTrue(loc.forward());
        assertEquals(100,loc.getX());


    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        Point punto=new Point(12,1);
        Direction dir=Direction.NORTH;
        Location loc= new Location(punto,dir);


        assertTrue(loc.forward());
        assertEquals(100,loc.getY());

    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        Point punto=new Point(12,100);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto,dir);


        assertTrue(loc.forward());
        assertEquals(1,loc.getY());


    }
    public void givenObstacleWhenForwardThenReturnFalse() {

        Point punto=new Point(12,35);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto,dir);
        //obstaculo

        obstacles.add(new Point(12, 36));
        Planet pla= new Planet(max, obstacles);

        assertFalse(loc.forward(max, obstacles));

    }

    public void givenObstacleWhenBackwardThenReturnFalse() {

        Point punto=new Point(12,35);
        Direction dir=Direction.SOUTH;
        Location loc= new Location(punto,dir);
        //obstaculo

        obstacles.add(new Point(12, 34));
        Planet pla= new Planet(max, obstacles);

        assertFalse(loc.backward(max, obstacles));



    }

}
