package agh.cs.oop.main;


public class Animal {
    private MapDirection mapDirection;
    private Vector2d position;

    public Animal() {
        mapDirection = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public void move(MoveDirection moveDirection) {
        switch(moveDirection) {
            case RIGHT:
                mapDirection = mapDirection.next();
                break;
            case LEFT:
                mapDirection = mapDirection.previous();
                break;
            case FORWARD:
                Vector2d tmpPosition = position.add(mapDirection.toUnitVector());
                // Checks if the result position is in the map scope, currently map size is 5x5
                if (tmpPosition.x > 4 || tmpPosition.x < 0 || tmpPosition.y > 4 || tmpPosition.y < 0) ;
                else position = tmpPosition;
                break;
            case BACKWARD:
                tmpPosition = position.subtract(mapDirection.toUnitVector());
                if (tmpPosition.x > 4 || tmpPosition.x < 0 || tmpPosition.y > 4 || tmpPosition.y < 0) ;
                else position = tmpPosition;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    @Override
    public String toString() {
        return "Animal{" + "direction=" + mapDirection + ", position=" + position + '}';
    }
}
