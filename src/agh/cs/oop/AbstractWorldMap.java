package agh.cs.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List <Animal> animals = new ArrayList<>();
    protected List <Grass> grass = new ArrayList<>();
    protected int mapWidth;
    protected int mapHeight;

    public List<Animal> getAnimals() {
        return animals;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
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
    public boolean placeAnimal(Animal animal) {
        if (!(this.isOccupied(animal.getPosition()))) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean placeGrass(Grass grassField) {
        if (!(this.isOccupied(grassField.getPosition()))) {
            grass.add(grassField);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) return true;
        }
        for  (Grass grassField : grass) {
            if (grassField.getPosition().equals(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) return animal;
        }
        for (Grass grassField : grass) {
            if (grassField.getPosition().equals(position)) return grassField;
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(new Vector2d(0,0), new Vector2d(mapWidth-1, mapHeight-1));
    }
}
