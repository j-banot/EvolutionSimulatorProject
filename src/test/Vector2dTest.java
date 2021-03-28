import agh.cs.oop.main.Vector2d;
import org.junit.Assert;
import org.junit.Test;

public class Vector2dTest {

    @Test
    public void testEquals_ReturnsTrue() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertTrue(vector.equals(new Vector2d(2, 4)));
    }

    @Test
    public void testEquals_ReturnsFalse() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertFalse(vector.equals(new Vector2d(4, 2)));
    }

    @Test
    public void testToString_Equals() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertEquals(vector.toString(), "(2,4)");
    }

    @Test
    public void testPrecedes_ReturnsTrue() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertTrue(vector.precedes(new Vector2d(3, 5)));
    }

    @Test
    public void testPrecedes_ReturnsFalse() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertFalse(vector.precedes(new Vector2d(1, 3)));
    }

    @Test
    public void testFollows_ReturnsTrue() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertTrue(vector.follows(new Vector2d(1, 3)));
    }

    @Test
    public void testFollows_ReturnsFalse() {
        Vector2d vector = new Vector2d(2, 4);
        Assert.assertFalse(vector.follows(new Vector2d(3, 5)));
    }

    @Test
    public void testUpperRight_Equals() {
        Vector2d vectorA = new Vector2d(2, 4);
        Vector2d vectorB = new Vector2d(1, 5);
        Vector2d vectorResult = new Vector2d(2, 5);
        Assert.assertEquals(vectorA.upperRight(vectorB), vectorResult);
    }

    @Test
    public void testLowerLeft_Equals() {
        Vector2d vectorA = new Vector2d(2, 4);
        Vector2d vectorB = new Vector2d(1, 5);
        Vector2d vectorResult = new Vector2d(1, 4);
        Assert.assertEquals(vectorA.lowerLeft(vectorB), vectorResult);
    }

    @Test
    public void testAdd_Equals() {
        Vector2d vectorA = new Vector2d(2, 4);
        Vector2d vectorB = new Vector2d(1, 5);
        Vector2d vectorResult = new Vector2d(3, 9);
        Assert.assertEquals(vectorA.add(vectorB), vectorResult);
    }

    @Test
    public void testSubtract_Equals() {
        Vector2d vectorA = new Vector2d(3, 6);
        Vector2d vectorB = new Vector2d(1, 5);
        Vector2d vectorResult = new Vector2d(2, 1);
        Assert.assertEquals(vectorA.subtract(vectorB), vectorResult);
    }

    @Test
    public void testOpposite_Equals() {
        Vector2d vectorA = new Vector2d(-3, 6);
        Vector2d vectorResult = new Vector2d(3, -6);
        Assert.assertEquals(vectorA.opposite(), vectorResult);
    }
}
