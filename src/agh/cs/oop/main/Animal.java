package agh.cs.oop.main;

public class Animal {
    private MapDirection direction;
    private Vector2d position;

    public Animal() {
        direction = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    @Override
    public String toString() {
        return "Animal{" + "direction=" + direction + ", position=" + position + '}';
    }
}
