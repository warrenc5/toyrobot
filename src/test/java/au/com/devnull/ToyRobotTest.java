package au.com.devnull;

import static au.com.devnull.ToyRobot.Orientation.EAST;
import static au.com.devnull.ToyRobot.Orientation.NORTH;
import static au.com.devnull.ToyRobot.Orientation.SOUTH;
import static au.com.devnull.ToyRobot.Orientation.WEST;
import org.junit.Test;
import static org.junit.Assert.*;

public class ToyRobotTest {

    @Test
    public void testRotataion() {

        ToyRobot.Orientation o = NORTH;
        assertEquals(EAST, o = o.rotateClockwise());
        assertEquals(SOUTH, o = o.rotateClockwise());
        assertEquals(WEST, o = o.rotateClockwise());
        assertEquals(NORTH, o = o.rotateClockwise());

        o = NORTH;
        assertEquals(WEST, o = o.rotateCounterClockwise());
        assertEquals(SOUTH, o = o.rotateCounterClockwise());
        assertEquals(EAST, o = o.rotateCounterClockwise());
        assertEquals(NORTH, o = o.rotateCounterClockwise());

    }

    @Test
    public void testProcessCommand1() {
        ToyRobot robot = new ToyRobot();
        robot.processCommand("PLACE 0,0,NORTH MOVE REPORT");
        assertEquals("0,1,NORTH", robot.report());
    }

    @Test
    public void testProcessCommand2() {
        ToyRobot robot = new ToyRobot();

        robot.processCommand("PLACE 0,0,NORTH LEFT REPORT");
        assertEquals("0,0,WEST", robot.report());
    }

    @Test
    public void testProcessCommand3() {
        ToyRobot robot = new ToyRobot();

        robot.processCommand("PLACE 1,2,EAST MOVE MOVE LEFT MOVE REPORT");
        assertEquals("3,3,NORTH", robot.report());
    }

    @Test
    public void testProcessCommand4() {
        ToyRobot robot = new ToyRobot();

        robot.processCommand("MOVE REPORT");
        assertEquals("ROBOT MISSING", robot.report());
    }
}
