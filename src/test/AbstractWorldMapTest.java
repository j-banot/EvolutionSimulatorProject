import agh.cs.oop.Animal;
import agh.cs.oop.RectangularMap;
import agh.cs.oop.Vector2d;
import org.junit.Assert;
import org.junit.Test;

public class AbstractWorldMapTest {
    RectangularMap map = new RectangularMap(10, 10);

    @Test
    public void testCanMoveToTrue() {
        Assert.assertTrue(map.canMoveTo(new Vector2d(6,6)));
    }

    @Test
    public void testCanMoveToFalse() {
        Assert.assertFalse(map.isOccupied(new Vector2d(11,11)));
    }

    @Test
    public void testIsOccupiedFalse() {
        map.placeAnimal(new Animal(map, new Vector2d(5,5)));
        Assert.assertFalse(map.isOccupied(new Vector2d(6,6)));
    }

    @Test
    public void testIsOccupiedTrue() {
        map.placeAnimal(new Animal(map, new Vector2d(5,5)));
        Assert.assertTrue(map.isOccupied(new Vector2d(5,5)));
    }

    @Test
    public void testPlaceAnimalFalse() {
        map.placeAnimal(new Animal(map, new Vector2d(5,5)));
        Assert.assertFalse(map.placeAnimal(new Animal(map, new Vector2d(5,5))))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ;
    }

    @Test
    public void testPlaceAnimalTrue() {
        map.placeAnimal(new Animal(map, new Vector2d(5,5)));
        Assert.assertTrue(map.placeAnimal(new Animal(map, new Vector2d(4,4))))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ;
    }

}
