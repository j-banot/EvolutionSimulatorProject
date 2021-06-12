package agh.cs.oop;

//TODO: descriptions and comments in the way shown below
// /**
// * Description
// */

import agh.cs.oop.Constants.Constants;
import agh.cs.oop.Enums.MoveDirection;
import agh.cs.oop.GUI.GameFrame;
import agh.cs.oop.Interfaces.IEngine;
import agh.cs.oop.Objects.*;

import java.io.IOException;

public class GameSimulation {

//    public static void main(String[] args) {
//        String[] arguments = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//        try {
//            MoveDirection[] directions = new OptionsParser().parse(arguments);
//            RectangularMap map = new RectangularMap(10, 5);
//            Grassland grassMap = new Grassland(10);
//            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
//            IEngine engine = new SimulationEngine(directions, grassMap, positions);
//            //IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
//
//            //System.out.println(animal.toString());
//            System.out.println(grassMap.toString());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

    public static void main(String[] args) throws IOException{
        Constants.getValueFromJSON();
        for(int i = 0; i < Constants.NUMBER_OF_MAPS_TO_GENERATE; i++) {
            try {
                new GameFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
