package agh.cs.oop.Interfaces;

import agh.cs.oop.Objects.Animal;
import agh.cs.oop.Objects.Vector2d;

public interface IPositionChangeObserver {

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
