package agh.cs.oop.Interfaces;

import agh.cs.oop.Objects.Vector2d;

public interface IMapElement {

    Vector2d getPosition();

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);

    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
