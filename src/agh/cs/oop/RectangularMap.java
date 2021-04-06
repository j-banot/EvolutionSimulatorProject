package agh.cs.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    public List<Animal> animals = new ArrayList<>();
    public int mapHeight;
    public int mapWidth;

    public RectangularMap(int height, int width) {
        mapHeight = height;
        mapWidth = width;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(mapWidth-1, mapHeight-1)))
            return true;
        else {
            return false;
        }
    }

    @Override
    public boolean place(Animal animal) {
        if (!(this.isOccupied(animal.getPosition()))) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) return animal;
        }
        //TO DO: add part with returning plants
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(new Vector2d(0,0), new Vector2d(mapWidth-1, mapHeight-1));
    }
}
