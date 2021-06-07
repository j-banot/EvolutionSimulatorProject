package agh.cs.oop.Interfaces;

//TODO: descriptions and comments in the way shown below (as example see interfaces in Lab4)
// /**
// * Description
// */

import agh.cs.oop.Objects.Animal;
import agh.cs.oop.Objects.Plant;
import agh.cs.oop.Objects.Vector2d;

public interface IWorldMap extends IPositionChangeObserver {

    boolean canMoveTo(Vector2d position);
    // TO DO: Add IMapElement
    boolean placeAnimal(Animal animal);

    //TODO: check if it is needed
    boolean placeGrass(Plant plant);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

    int getStartEnergy();

    int getDay();
}
