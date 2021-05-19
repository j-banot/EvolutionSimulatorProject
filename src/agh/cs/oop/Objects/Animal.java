package agh.cs.oop.Objects;

//TODO: descriptions and comments in the way shown below
// /**
// * Description
// */


import agh.cs.oop.Interfaces.IMapElement;
import agh.cs.oop.Interfaces.IPositionChangeObserver;
import agh.cs.oop.Interfaces.IWorldMap;
import agh.cs.oop.Enums.MapDirection;
import agh.cs.oop.Enums.MoveDirection;

public class Animal implements IMapElement {

    private MapDirection mapDirection;
    private Vector2d position;
    IPositionChangeObserver observer = null;

    public Animal() {
        mapDirection = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map) {
        mapDirection = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        mapDirection = MapDirection.NORTH;
        position = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection direction) {
        mapDirection = direction;
        position = initialPosition;
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void move(MoveDirection moveDirection, IWorldMap map) {
        switch(moveDirection) {
            case RIGHT:
                mapDirection = mapDirection.next();
                break;
            case LEFT:
                mapDirection = mapDirection.previous();
                break;
            case FORWARD:
                Vector2d tmpPosition = position.add(mapDirection.toUnitVector());
                if (map.canMoveTo(tmpPosition)) {
                    positionChanged(position, tmpPosition);
                    position = tmpPosition;
                }
                break;
            case BACKWARD:
                tmpPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(tmpPosition)) {
                    positionChanged(position, tmpPosition);
                    position = tmpPosition;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    @Override
    public String toString() {
        switch (mapDirection) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case EAST:
                return "E";
            case WEST:
                return "W";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

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
