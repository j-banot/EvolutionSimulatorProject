package agh.cs.oop;

//TO DO: descriptions and comments in the way shown below (as example see interfaces in Lab4)
// /**
// * Description
// */

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);
    // TO DO: Add IMapElement
    boolean placeAnimal(Animal animal);

    boolean placeGrass(Grass grass);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);
}
