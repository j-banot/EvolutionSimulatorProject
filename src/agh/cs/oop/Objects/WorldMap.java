package agh.cs.oop.Objects;

import agh.cs.oop.Enums.MapDirection;
import agh.cs.oop.Interfaces.IWorldMap;

import java.util.*;

public class WorldMap implements IWorldMap {
    protected int day;
    protected int mapHeight;
    protected int mapWidth;
    protected int startEnergy;
    protected int moveEnergy;
    protected int plantEnergy;
    protected Vector2d jungleStartingPoint;
    protected int sizeOfJungleSquare;
    protected Map<Vector2d, ArrayList<Animal>> animals = new HashMap<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected int numberOfAnimals;
    protected int numberOfPlants;
    protected double sumOfAnimalsEnergy;
    protected double sumOfDeathAges;
    protected int numberofDeaths;
    protected int numberOfChildern;
    private int[] dominantGenotype; //??
    private int numberOfOffspringOfTrackedAnimal;
    private boolean allAnimalsAreDead;
    protected Vector2d mapStartPoint = new Vector2d(0,0);
    protected Vector2d mapEndPoint;

    public WorldMap(int mapHeight, int mapWidth, int baseEnergy, int moveEnergy, int plantEnergy, int sizeOfJungleSquare) {
        this.day = 0;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.mapEndPoint = new Vector2d(mapWidth-1, mapHeight-1);
        this.startEnergy = baseEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.sizeOfJungleSquare = sizeOfJungleSquare;
        setJungle();
        this.allAnimalsAreDead = false;
    }

    //Getters
    public Map<Vector2d, ArrayList<Animal>> getAnimals() {
        return animals;
    }

    public Map<Vector2d, Plant> getPlants() { return plants; }

    public int getStartEnergy() { return this.startEnergy; }

    public int getNumberOfAnimals() { return numberOfAnimals; }

    public int getNumberOfPlants() { return numberOfPlants; }

    public int getDay() { return day; }

    public double getAverageEnergy() { return (double) Math.round(sumOfAnimalsEnergy/numberOfAnimals); }

    public double getAverageDeathAge() {
        if (numberofDeaths != 0) return (double) Math.round(sumOfDeathAges/numberofDeaths);
        else return 0;
    }

    public double getAverageNumberOfChildren(){ return (double) Math.round(numberOfChildern/numberOfAnimals); }

    //TODO: implement??
    public int[] getDominantGenotype() {
        return dominantGenotype;
    }

    public boolean getIfAllAnimalsAreDead() { return allAnimalsAreDead; }

    //Setters
    public void setJungle() {
        this.sizeOfJungleSquare = (int) (this.mapHeight * 0.4);
        int x = (int) ((this.mapWidth / 2) - (this.sizeOfJungleSquare / 2));
        int y = (int) ((this.mapHeight / 2) - (this.sizeOfJungleSquare / 2));
        this.jungleStartingPoint = new Vector2d(x, y);
    }

    public void setSumOfAnimalsEnergy(double sumOfAnimalsEnergy) {
        this.sumOfAnimalsEnergy = sumOfAnimalsEnergy;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildern = numberOfChildren;
    }

    public void setNumberOfDominantGenotype(int[] dominantGenotype) {
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

    public void nextDay() {
        this.day++;
        moveAll();
        eatPlants();
        updateStatsAndRemoveDeads();
        reproduceAndPlaceChildren();
        placeNewPlants();
    }

    public void reproduceAndPlaceChildren() {
        ArrayList<ArrayList<Animal>> tmpAnimalsCopy = new ArrayList<>(animals.values());
        for (ArrayList<Animal> animalsAtPosition : tmpAnimalsCopy)  {
            ArrayList<Animal> twoStrongest = Animal.getTwoStrongest(animalsAtPosition);
            if (animalsAtPosition.size() > 1) {
                Animal child = Animal.reproduce(twoStrongest.get(0), twoStrongest.get(1));
                if (child != null) {
                    if (child.getIfIsOffsprintOfTrackedAnimal()) {
                        numberOfOffspringOfTrackedAnimal++;
                    }
                    placeNewAnimal(twoStrongest.get(0).getPosition(), child);
                }
            }
        }
    }

    public void eatPlants() {
        ArrayList<ArrayList<Animal>> tmpAnimalsCopy = new ArrayList<>(animals.values());
        for (ArrayList<Animal> animalsAtPosition : tmpAnimalsCopy)  {
            ArrayList<Animal> twoStrongest = Animal.getTwoStrongest(animalsAtPosition);
            Vector2d position = animalsAtPosition.get(0).getPosition();
            if (plants.containsKey(position) && animalsAtPosition.size() > 0) {
                Plant tmpPlant = plants.get(position);
                if (twoStrongest.size() > 1 && twoStrongest.get(0) == twoStrongest.get(1)) {
                    twoStrongest.get(0).changeEnergy(tmpPlant.getPlantEnergy() * 0.5);
                    twoStrongest.get(1).changeEnergy(tmpPlant.getPlantEnergy() * 0.5);
                } else {
                    twoStrongest.get(0).changeEnergy(tmpPlant.getPlantEnergy());
                }
                numberOfPlants--;
                plants.remove(position);
            }
        }

    }

    public void updateStatsAndRemoveDeads() {
        double currentSumOfAnimalsEnergy = 0;
        int currentNumberOfChildren = 0;
        int[] currentNumberOfDominantGenotypes = new int[8];
        ArrayList<ArrayList<Animal>> tmpAnimals = new ArrayList<>(animals.values());
        for (ArrayList<Animal> animalList : tmpAnimals) {
            ArrayList<Animal> tmpAnimalList = new ArrayList<>(animalList);
            for (Animal tmpAnimal : tmpAnimalList) {
                if (tmpAnimal.getEnergy() <= 0) {
                    sumOfDeathAges += this.day - tmpAnimal.getDayOfBirth();
                    numberofDeaths++;
                    removeAnimalFromHashMap(tmpAnimal.getPosition(), tmpAnimal);
                    tmpAnimal.setIfDead();
                    if (numberOfAnimals <= 0) this.allAnimalsAreDead = true;
                } else {
                    currentSumOfAnimalsEnergy += tmpAnimal.getEnergy();
                    currentNumberOfChildren += tmpAnimal.getNumberOfChildren();
                    // TODO: check what is going on here
//                    ArrayList<Integer> tmpAnimalDominantGenotype = tmpAnimal.getDominantGenotypes();
//                    for (int i = 0; i < tmpAnimalDominantGenotype.size(); i++) {
//                        currentNumberOfDominantGenotypes[tmpAnimalDominantGenotype.get(i)]++;
//                    }
                }
            }
        }
        setSumOfAnimalsEnergy(currentSumOfAnimalsEnergy);
        setNumberOfChildren(currentNumberOfChildren);
        setNumberOfDominantGenotype(currentNumberOfDominantGenotypes);
    }

    public void moveAnimal(Animal animal) {
//        animal.setPosition(checkTargetPosition(animal.getPosition().add(animal.getMapDirection().toUnitVector()),
//                                               animal.getPosition()));
        animal.setPosition(convertPositionToMap(animal.getPosition().add(animal.getMapDirection().toUnitVector())));
    }

    // check if animal can move to a new position, if not it stays in old position
    public Vector2d checkTargetPosition(Vector2d newPosition, Vector2d oldPosition) {
        if (canMoveTo(newPosition)) return newPosition;
        else {
            return oldPosition;
        }
    }

    // converts position to map when we go outside it
    private Vector2d convertPositionToMap(Vector2d position) {
        int x = position.x < 0 ? this.mapWidth - 1 : position.x;
        int y = position.y < 0 ? this.mapHeight - 1 : position.y;
        x = x % (this.mapWidth);
        y = y % (this.mapHeight);
        return new Vector2d(x, y);
    }

    public void moveAll() {
        ArrayList<ArrayList<Animal>> tmpAnimals = new ArrayList<>(animals.values());
        for (ArrayList<Animal> animalList : tmpAnimals) {
            ArrayList<Animal> tmpAnimalList = new ArrayList<>(animalList);
            for (Animal tmpAnimal : tmpAnimalList) {
                tmpAnimal.changeDirection();
                Vector2d oldPosition = tmpAnimal.getPosition();
                moveAnimal(tmpAnimal);
                Vector2d newPosition = tmpAnimal.getPosition();
                tmpAnimal.notifyPositionChanged(oldPosition, newPosition);
                tmpAnimal.changeEnergy(-this.moveEnergy);
            }
        }
    }

    //TODO: check if it is needed
    public boolean areAllAroundOccupied(Vector2d position) {
        Animal tmpAnimal = new Animal(this);
        for (int i = 0; i < 8; i++) {
            tmpAnimal.setPosition(position);
            tmpAnimal.setDirection(MapDirection.getDirectionFromValue(i));
            moveAnimal(tmpAnimal);
            if (!isOccupied(tmpAnimal.getPosition())) {
                return false;
            }
        }
        return true;
    }

    //TODO: unit test
    public void placeNewAnimal(Vector2d parentPosition, Animal newAnimal) {
        if (!areAllAroundOccupied(parentPosition)) {
            do {
                newAnimal.setPosition(parentPosition);
                //newAnimal.changeDirection();
                moveAnimal(newAnimal);
            } while (isOccupied(newAnimal.getPosition()));
        } else {
            newAnimal.setPosition(parentPosition);
            newAnimal.changeDirection();
            moveAnimal(newAnimal);
        }
        putAnimalToHashMap(newAnimal.getPosition(), newAnimal);
        //TODO: check
//        Vector2d childPosition = null;
//        for (int i = 0; i < 8; i++) {
//            Vector2d tmpPosition = parentPosition.add(MapDirection.getDirectionFromValue(i).toUnitVector());
//            if (!isOccupied(tmpPosition)) {
//                childPosition = tmpPosition;
//                putAnimalToHashMap(childPosition, newAnimal);
//                return;
//            }
//        }
//        Random random = new Random();
//        int option = random.nextInt(8);
//        Vector2d positionChange = MapDirection.getDirectionFromValue(option).toUnitVector();
//        childPosition = parentPosition.add(positionChange);
//        putAnimalToHashMap(childPosition, newAnimal);
    }

    public void placeNewPlants() {
        Random random = new Random();
        int x, y;
        //placing one plant in jungle area
        if (!isJungleFull()) {
            do {
                x = random.nextInt(sizeOfJungleSquare);
                y = random.nextInt(sizeOfJungleSquare);
                x += jungleStartingPoint.x;
                y += jungleStartingPoint.y;
            } while (isOccupied(new Vector2d(x, y)));
            Plant newJunglePlant = new Plant(plantEnergy);
            numberOfPlants++;
            plants.put(new Vector2d(x, y), newJunglePlant);
        }
        //placing one plant in desert area (rest of the map)
        do {
            x = random.nextInt(mapWidth);
            y = random.nextInt(mapHeight);
        } while (isOccupied(new Vector2d(x, y)) || isOnJungle(x, y));
        Plant newPlant = new Plant(plantEnergy);
        numberOfPlants++;
        plants.put(new Vector2d(x, y), newPlant);
    }

    public boolean isOnJungle(int x, int y) {
        return jungleStartingPoint.x <= x && x < jungleStartingPoint.x + this.sizeOfJungleSquare
                && jungleStartingPoint.y <= y && y < jungleStartingPoint.y + this.sizeOfJungleSquare;
    }

    public boolean isJungleFull() {
        for (int x = jungleStartingPoint.x; x < jungleStartingPoint.x + this.sizeOfJungleSquare; x++) {
            for (int y = jungleStartingPoint.y; y < jungleStartingPoint.y + this.sizeOfJungleSquare; y++) {
                if(!isOccupied(new Vector2d(x, y))) return false;
            }
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        ArrayList<Animal> animalArrayList = animals.get(position);
        if (animalArrayList != null) {
            return animalArrayList.get(0);
        } else {
            return plants.get(position);
        }
//        if(animals.containsKey(position)) return animals.get(position);
//        else return plants.getOrDefault(position, null);
    }

    @Override
    public boolean placeAnimal(Animal animal) {
        if (!(this.isOccupied(animal.getPosition()))) {
            putAnimalToHashMap(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    //TODO: check if it is needed
    public boolean placeGrass(Plant plantField) {
        if (!(this.isOccupied(plantField.getPosition()))) {
            plants.put(plantField.getPosition(), plantField);
            return true;
        }
        throw new IllegalArgumentException("Element can not be placed on: " + plantField.getPosition());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return (animals.get(position) != null || plants.get(position) != null );
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return  position.follows(mapStartPoint) && position.precedes(mapEndPoint);
    }

    //TODO: unit test
    public void putAnimalToHashMap(Vector2d position, Animal animal) {
        numberOfAnimals++;
        ArrayList<Animal> tmpList = animals.get(position);
        if (tmpList == null) {
            tmpList = new ArrayList<Animal>();
            animals.put(position, tmpList);
        }
        tmpList.add(animal);
    }

    //TODO: unit test
    public void removeAnimalFromHashMap(Vector2d position, Animal animal) {
        numberOfAnimals--;
        ArrayList<Animal> tmpList = animals.get(position);
        if (tmpList != null) {
            tmpList.remove(animal);
            animals.put(position, tmpList);
            if (tmpList.isEmpty()) {
                animals.remove(position);
            }
        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        removeAnimalFromHashMap(oldPosition, animal);
        putAnimalToHashMap(newPosition, animal);
    }
}
