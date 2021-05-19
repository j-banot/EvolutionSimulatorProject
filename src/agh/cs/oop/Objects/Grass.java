package agh.cs.oop.Objects;

import agh.cs.oop.Interfaces.IMapElement;
import agh.cs.oop.Interfaces.IPositionChangeObserver;
import agh.cs.oop.Objects.Vector2d;

public class Grass implements IMapElement {

    Vector2d position;
    IPositionChangeObserver observer = null;

    public Grass(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "*";
    }
// TODO: move observer methods to a common abstract class for Grass and Animal
    @Override
    public void addObserver(IPositionChangeObserver observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        this.observer = null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (observer != null) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
