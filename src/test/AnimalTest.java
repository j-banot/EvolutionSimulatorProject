import agh.cs.oop.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class AnimalTest {

    Animal animal = new Animal();
    RectangularMap map = new RectangularMap(5, 5);

    @Test
    public void testAnimalDefaultDirection() {
        Assert.assertEquals(MapDirection.NORTH, animal.getMapDirection());
    }

    @Test
    public void testAnimalMoveDirectionForward() {
        animal.move(MoveDirection.FORWARD, map);
        Assert.assertEquals(new Vector2d(2,3), animal.getPosition());
    }

    @Test
    public void testAnimalMoveDirectionBackward() {
        animal.move(MoveDirection.BACKWARD, map);
        Assert.assertEquals(new Vector2d (2,1), animal.getPosition());
    }

    @Test
    public void testAnimalMoveDirectionRight() {
        animal.move(MoveDirection.RIGHT, map);
        Assert.assertEquals(MapDirection.EAST, animal.getMapDirection());
    }

    @Test
    public void testAnimalMoveDirectionLeft() {
        animal.move(MoveDirection.LEFT, map);
        Assert.assertEquals(MapDirection.WEST, animal.getMapDirection());
    }

    @Test
    public void testAnimalCoordinatesY() {
        animal.move(MoveDirection.BACKWARD, map);
        animal.move(MoveDirection.BACKWARD, map);
        animal.move(MoveDirection.BACKWARD, map);
//        animal.move(MoveDirection.BACKWARD);
        Assert.assertEquals(new Vector2d (2,0), animal.getPosition());
    }

    @Test
    public void testAnimalCoordinatesX() {
        animal.move(MoveDirection.RIGHT, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        Assert.assertEquals(new Vector2d (4,2), animal.getPosition());
    }

    @Test
    public void testAnimalCoordinatesParsedOptionY() {
        String[] moveDirections = {"b", "b", "b"};
        MoveDirection[] moveSequence = OptionsParser.parse(moveDirections);
        for (MoveDirection move : moveSequence) {
            animal.move(move, map);
        }
        Assert.assertEquals(new Vector2d (2,0), animal.getPosition());
    }

    @Test
    public void testAnimalCoordinatesParsedOptionX() {
        String[] moveDirections = {"r", "f", "f", "f"};
        MoveDirection[] moveSequence = OptionsParser.parse(moveDirections);
        for (MoveDirection move : moveSequence) {
            animal.move(move, map);
        }
        Assert.assertEquals(new Vector2d (4,2), animal.getPosition());
    }

}
