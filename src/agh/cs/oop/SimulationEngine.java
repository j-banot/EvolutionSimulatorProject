package agh.cs.oop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SimulationEngine implements IEngine{

    public List<MoveDirection> directionsList;
    public List<Animal> animalList;
    public IWorldMap tmpMap;
    public int animalCount;

    public SimulationEngine(MoveDirection[] directions, RectangularMap map, Vector2d[] positions){
        for (Vector2d position : positions) {
            map.placeAnimal(new Animal(map, position));
            animalCount += 1;
        }
        directionsList = Arrays.asList(directions);
        tmpMap = map;
        animalList = map.animals;

    }
//TO DO: get to know if possible and how I can merge these two constructors
    public SimulationEngine(MoveDirection[] directions, GrassField map, Vector2d[] positions){
        for (Vector2d position : positions) {
            map.placeAnimal(new Animal(map, position));
            animalCount += 1;
        }
        directionsList = Arrays.asList(directions);
        tmpMap = map;
        animalList = map.animals;

    }

    @Override
    public void run() {
        int counter = 0;
        Iterator<Animal> animalIterator = animalList.iterator();
        Iterator<MoveDirection> directionIterator = directionsList.iterator();
        while (directionIterator.hasNext()) {
            while (animalIterator.hasNext()) {
                animalIterator.next().move(directionIterator.next(), tmpMap);
            }
            animalIterator = animalList.iterator();

        }


    }
}
