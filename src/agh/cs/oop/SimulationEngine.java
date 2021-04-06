package agh.cs.oop;

import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine{

    public List<MoveDirection> directionsList;
    //TO DO: not sure if it is a correct solution
    public int animalCount;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        for (Vector2d position : positions) {
            map.place(new Animal(map, position));
            animalCount += 1;
        }
        directionsList = Arrays.asList(directions);
    }

    @Override
    public void run() {
        int counter = 0;
        for (int i = 0; i < directionsList.size(); i++) {
            if (counter == animalCount) counter = 0;
            //Jak dostać się do listy Animals w tym miejscu?
            counter++;
        }

    }
}
