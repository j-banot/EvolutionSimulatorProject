package agh.cs.oop;

//TO DO: descriptions and comments in the way shown below
// /**
// * Description
// */

import agh.cs.oop.GUI.MainGUI;

public class World {

    public static void main(String[] args) {
        String[] arguments = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//
        MoveDirection[] directions = new OptionsParser().parse(arguments);
        RectangularMap map = new RectangularMap(10, 5);
        GrassField grassMap = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, grassMap, positions);
//        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

//        System.out.println(animal.toString());
        System.out.println(grassMap.toString());
        //new MainGUI();
    }
}
