import org.junit.Assert;
import agh.cs.oop.Enums.MapDirection;
import org.junit.Test;

public class MapDirectionTest {

    @Test
    public void testNextNorth() {
        MapDirection direction = MapDirection.NORTH;
        Assert.assertEquals(MapDirection.EAST, direction.next());
    }

    @Test
    public void testNextSouth() {
        MapDirection direction = MapDirection.SOUTH;
        Assert.assertEquals(MapDirection.WEST, direction.next());
    }

    @Test
    public void testNextEast() {
        MapDirection direction = MapDirection.EAST;
        Assert.assertEquals(MapDirection.SOUTH, direction.next());
    }

    @Test
    public void testNextWest() {
        MapDirection direction = MapDirection.WEST;
        Assert.assertEquals(MapDirection.NORTH, direction.next());
    }

    @Test
    public void testPreviousNorth() {
        MapDirection direction = MapDirection.NORTH;
        Assert.assertEquals(MapDirection.WEST, direction.previous());
    }

    @Test
    public void testPreviousSouth() {
        MapDirection direction = MapDirection.SOUTH;
        Assert.assertEquals(MapDirection.EAST, direction.previous());
    }

    @Test
    public void testPreviousEast() {
        MapDirection direction = MapDirection.EAST;
        Assert.assertEquals(MapDirection.NORTH, direction.previous());
    }

    @Test
    public void testPreviousWest() {
        MapDirection direction = MapDirection.WEST;
        Assert.assertEquals(MapDirection.SOUTH, direction.previous());
    }
}
