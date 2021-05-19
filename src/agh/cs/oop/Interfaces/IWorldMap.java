package agh.cs.oop.Interfaces;

//TODO: descriptions and comments in the way shown below (as example see interfaces in Lab4)
// /**
// * Description
// */

import agh.cs.oop.Objects.Animal;
import agh.cs.oop.Objects.Grass;
import agh.cs.oop.Objects.Vector2d;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);
    // TO DO: Add IMapElement
    boolean placeAnimal(Animal animal);

    boolean placeGrass(Grass grass);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);
}
