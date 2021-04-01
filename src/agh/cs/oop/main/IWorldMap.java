package agh.cs.oop.main;

//TO DO: descriptions and comments in the way shown below (as example see interfaces in Lab4)
// /**
// * Description
// */

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

}
