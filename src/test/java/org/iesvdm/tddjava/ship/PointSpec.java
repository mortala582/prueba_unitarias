package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class PointSpec {

    private Point point;
    private final int x = 12;
    private final int y = 21;

    @BeforeMethod
    public void beforeTest() {
        point = new Point(x, y);
    }

    public void whenInstantiatedThenXIsSet() {
        Point punto= new Point(x,y);
        assertEquals(punto.getX(),12);


    }

    public void whenInstantiatedThenYIsSet() {
        Point punto= new Point(x,y);
        assertEquals(punto.getY(),21);

    }

}
