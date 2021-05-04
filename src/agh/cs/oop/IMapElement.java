package agh.cs.oop;

public interface IMapElement {

    Vector2d getPosition();

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);

    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
