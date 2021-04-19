import agh.cs.oop.*;
import org.junit.Assert;
import org.junit.Test;

public class SimulationEngineTest {
//    String[] arguments = {"f", "b", "b", "r", "b", "l", "r", "l", "f", "b", "f", "f"};
    String[] arguments = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};

    MoveDirection[] directions = new OptionsParser().parse(arguments);
    RectangularMap map = new RectangularMap(10, 5);
    Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
    IEngine engine = new SimulationEngine(directions, map, positions);
    Animal tmpAnimal1 = new Animal(map, new Vector2d(3, 0), MapDirection.SOUTH);
    Animal tmpAnimal2 = new Animal(map, new Vector2d(2, 7), MapDirection.NORTH);

    @Test
    public void testRunDataSample1() {
        engine.run();
        //TO DO: spilt asserts into separate tests! And make it more READABLE
        Animal animal1 = map.animals.get(0);
        Animal animal2 = map.animals.get(1);
        Assert.assertTrue(animal1.getPosition().equals(tmpAnimal1.getPosition()));
        Assert.assertTrue(animal2.getPosition().equals(tmpAnimal2.getPosition()));
        Assert.assertTrue(animal1.getMapDirection() == tmpAnimal1.getMapDirection());
        Assert.assertTrue(animal2.getMapDirection() == tmpAnimal2.getMapDirection());
    }
}
