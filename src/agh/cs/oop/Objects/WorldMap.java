package agh.cs.oop.Objects;

import agh.cs.oop.Interfaces.IWorldMap;
import agh.cs.oop.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WorldMap implements IWorldMap {
    protected int day;
    protected int mapHeight;
    protected int mapWidth;
    protected int baseEnergy;
    protected int moveEnergy;
    protected int plantEnergy;
    protected Vector2d jungleStartingPooint;
    protected int sizeOfJungleSquareSide;
    protected Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    protected Map<Vector2d, Grass> grass = new HashMap<>();
    protected double numberOfAnimals;
    protected double numberOfPlants;
    protected double sumOfAnimalsEnergy;
    protected double sumOfDeathAges;
    protected int numberofDeaths;
    protected int numberOfChildern;
    private int[] dominantGenotype;
    private boolean allAnimalsAreDead;

    public WorldMap(int mapHeight, int mapWidth, int baseEnergy, int moveEnergy, int plantEnergy, int sizeOfJungleSquareSide) {
        this.day = 0;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.baseEnergy = baseEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.sizeOfJungleSquareSide = sizeOfJungleSquareSide;
        //TODO: setJungle();
        this.allAnimalsAreDead = false;
    }

    public Map<Vector2d, ArrayList<Animal>> getAnimals() {
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
            putAnimalToHashMap(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException("Element can not be placed on: " + animal.getPosition());
    }

    @Override
    public boolean placeGrass(Grass grassField) {
        if (!(this.isOccupied(grassField.getPosition()))) {
            grassField.addObserver(grassField.observer);
            grass.put(grassField.getPosition(), grassField);
            return true;
        }
        throw new IllegalArgumentException("Element can not be placed on: " + grassField.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        //TODO: not sure if the method should consider both animals and grass (maybe animals only?)
        if (animals.containsKey(position) || grass.containsKey(position)) return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(animals.containsKey(position)) return animals.get(position);
        else return grass.getOrDefault(position, null);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        removeAnimalFromHashMap(oldPosition, animal);
        putAnimalToHashMap(newPosition, animal);
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(new Vector2d(0,0), new Vector2d(mapWidth-1, mapHeight-1));
    }

    //TODO: check if it is correct and try to use it
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

    //TODO: unit test
    public void putAnimalToHashMap(Vector2d position, Animal animal) {
        ArrayList<Animal> tmpList = animals.get(position);
        if (tmpList == null) tmpList = new ArrayList<Animal>();
        tmpList.add(animal);
        numberOfAnimals++;
        animals.put(position, tmpList);
    }

    //TODO: unit test
    public void removeAnimalFromHashMap(Vector2d position, Animal animal) {
        ArrayList<Animal> tmpList = animals.get(position);
        if (tmpList != null) {
            tmpList.remove(animal);
            numberOfAnimals--;
            if (!tmpList.isEmpty()) animals.put(position, tmpList);
        }
    }
}
