package agh.cs.oop;

//TO DO: descriptions and comments in the way shown below
// /**
// * Description
// */


public class Animal implements IMapElement{

    private MapDirection mapDirection;
    private Vector2d position;

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
                if (map.canMoveTo(tmpPosition)) position = tmpPosition;
                break;
            case BACKWARD:
                tmpPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(tmpPosition)) position = tmpPosition;
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
}
