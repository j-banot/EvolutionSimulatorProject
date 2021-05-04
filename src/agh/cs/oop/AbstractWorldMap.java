package agh.cs.oop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected Map<Vector2d, Grass> grass = new HashMap<>();
    protected int mapWidth;
    protected int mapHeight;

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }
    public Map<Vector2d, Grass> getGrass() { return grass; }
    public int getMapWidth() {
        return mapWidth;
    }
    public int getMapHeight() {
        return mapHeight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(mapWidth - 1, mapHeight - 1));
    }

    @Override
    public boolean placeAnimal(Animal animal) {
        if (!(this.isOccupied(animal.getPosition()))) {
            animal.addObserver(animal.observer);
            animals.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean placeGrass(Grass grassField) {
        if (!(this.isOccupied(grassField.getPosition()))) {
            grassField.addObserver(grassField.observer);
            grass.put(grassField.getPosition(), grassField);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        //TO DO: not sure if the method should consider both animals and grass (maybe animals only?)
        if (animals.containsKey(position) || grass.containsKey(position)) return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(animals.containsKey(position)) return animals.get(position);
        else return grass.getOrDefault(position, null);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal tmpAnimal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, tmpAnimal);

    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(new Vector2d(0,0), new Vector2d(mapWidth-1, mapHeight-1));
    }

    //TO DO: check if it is correct and try to use it
    public boolean eatGrass (Animal animal) {
        Iterator<Grass> grassIterator = grass.values().iterator();

        while (grassIterator.hasNext()) {
            if (grassIterator.next().getPosition().equals(animal.getPosition())) {
                System.out.println("yumm");
                grassIterator.remove();
                return true;
            }
        }
        return false;
    }
}
