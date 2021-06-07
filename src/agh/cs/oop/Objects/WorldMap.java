package agh.cs.oop.Objects;

import agh.cs.oop.Interfaces.IWorldMap;

import java.util.*;

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
    protected Map<Vector2d, Plant> grass = new HashMap<>();
    protected int numberOfAnimals;
    protected int numberOfPlants;
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
        setJungle();
        this.allAnimalsAreDead = false;
    }

    //Getters
    public Map<Vector2d, ArrayList<Animal>> getAnimals() {
        return animals;
    }

    public Map<Vector2d, Plant> getGrass() { return grass; }

    public int getBaseEnergy() { return this.baseEnergy; }

    public int getNumberOfAnimals() { return numberOfAnimals; }

    public int getNumberOfPlants() { return numberOfPlants; }

    public int getDay() { return day; }

    public double getAverageEnergy() { return (double) Math.round(sumOfAnimalsEnergy/numberOfAnimals); }

    public double getAverageDeathAge() {
        if (numberofDeaths != 0) return (double) Math.round(sumOfDeathAges/numberofDeaths);
        else return 0;
    }

    public double getAverageNumberOfChildren(){ return (double) Math.round(numberOfChildern/numberOfAnimals); }

    //TODO: implement
    public int[] getDominantGenotype() {
        return dominantGenotype;
    }

    public boolean getIfAllAnimalsAreDead() { return allAnimalsAreDead; }

    //Setters
    public void setJungle() {
        //TODO: implement
    }

    public void sumOfAnimalsEnergy(double sumOfAnimalsEnergy) {
        this.sumOfAnimalsEnergy = sumOfAnimalsEnergy;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildern = numberOfChildren;
    }

    public void setDominantGenotype(int[] dominantGenotype) {
        this.dominantGenotype = dominantGenotype;
    }

    public void placeNAnimalsOnMap(int n) {
        Random rand = new Random();
        int x, y;
        for (int i = 0; i < n; i++) {
            do {
                x = rand.nextInt(mapWidth);
                y = rand.nextInt(mapHeight);
            } while (isOccupied(new Vector2d(x, y)));
            Animal tmpAnimal = new Animal(this, new Vector2d(x, y));
            this.placeAnimal(tmpAnimal);
        }
    }

    //TODO: check if it is correct and try to use it
    public boolean eatGrass (Animal animal) {
        Iterator<Plant> grassIterator = grass.values().iterator();

        while (grassIterator.hasNext()) {
            if (grassIterator.next().getPosition().equals(animal.getPosition())) {
                System.out.println("yumm");
                grassIterator.remove();
                return true;
            }
        }
        return false;
    }

    public void nextDay() {
        this.day++;
        //moveAll();
        //eatPlants();
        //removeDeads();
        //reproduce();
        //placeNewPlants();
    }

    public void moveAll() {
        //TODO: add moving of animals based on their genes
    }

    public void eatPlants() {
        //TODO: add logic if they are more than one animal the strongest one eats
    }

    public void removeDeads() {
        //TODO: implement
    }

    public void reproduce() {
        //TODO: implement two strongest reproduce with each other
    }

    public boolean areAllAroundOccupied(Vector2d position) {
        //TODO: implement
        return false;
    }

    public void placeNewAnimal(Vector2d parentPosition, Animal newAnimal) {
        //TODO: implement placing a newborn
    }

    public void placeNewPlants() {
        //TODO: implement placing one grass in the jungle and one grass on the normal area
    }

    public boolean isOnJungle(int x, int y) {
        //TODO: implement
        return false;
    }

    public boolean isJungleFull() {
        //TODO: implement
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if(animals.containsKey(position)) return animals.get(position);
        else return grass.getOrDefault(position, null);
    }
    @Override
    public int getStartEnergy() {
        return 0;
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
    //TODO: check if it is needed
    public boolean placeGrass(Plant plantField) {
        if (!(this.isOccupied(plantField.getPosition()))) {
            grass.put(plantField.getPosition(), plantField);
            return true;
        }
        throw new IllegalArgumentException("Element can not be placed on: " + plantField.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        //TODO: not sure if the method should consider both animals and grass (maybe animals only?)
        if (animals.containsKey(position) || grass.containsKey(position)) return true;
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return  position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(mapWidth - 1, mapHeight - 1));
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(new Vector2d(0,0), new Vector2d(mapWidth-1, mapHeight-1));
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

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        removeAnimalFromHashMap(oldPosition, animal);
        putAnimalToHashMap(newPosition, animal);
    }
}
