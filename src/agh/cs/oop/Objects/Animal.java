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
import agh.cs.oop.Tools.HelperMethods;

import java.util.*;

public class Animal implements IMapElement {

    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private IWorldMap map;
    private int dayOfBirth;
    private int numberOfChildren;
    private ArrayList<Integer> dominantGenotypes;
    private int[] genes;
    int[] genesDistribution = new int[100];
    private int startEnergy;
    private double energy;
    private boolean isDead = false;
    private boolean isOffspringOfTrackedAnimal = false;
    private MapDirection mapDirection = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);


    IPositionChangeObserver observer = null;

    //TODO: check if needed
    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection direction) {
        mapDirection = direction;
        position = initialPosition;
    }

    public Animal() {

    }

    public Animal(IWorldMap map){
        this.map = map;
        this.dayOfBirth = this.map.getDay();
        this.numberOfChildren = 0;
        this.startEnergy = map.getStartEnergy();
        this.energy = this.getStartEnergy();
        this.genes = generateRandomGenes();
        addObserver(map);
        setDominantGenotypes();
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.dayOfBirth = this.map.getDay();
        this.numberOfChildren = 0;
        this.startEnergy = map.getStartEnergy();
        this.energy = this.getStartEnergy();
        this.genes = generateRandomGenes();
        addObserver(map);
        this.setPosition(initialPosition);
        setDominantGenotypes();
    }

    public Animal(IWorldMap map, Vector2d initialPosition, int[] genes){
        this.map = map;
        this.dayOfBirth = this.map.getDay();
        this.numberOfChildren = 0;
        this.startEnergy = map.getStartEnergy();
        this.energy = this.getStartEnergy();
        addObserver(map);
        this.setPosition(initialPosition);
        this.genes = genes;
        setDominantGenotypes();
    }

    //Getters
    public int getStartEnergy() { return startEnergy; }

    public int getDayOfBirth() { return dayOfBirth; }

    public double getEnergy() { return this.energy; }

    public int getNumberOfChildren() { return numberOfChildren; }

    public ArrayList<Integer> getDominantGenotypes() { return dominantGenotypes; }

    private int[] generateRandomGenes(){
        Random random = new Random();
        int [] genes = new int[32];
        for (int i = 0; i < genes.length; i++) {
            if (i < 8) genes[i] = i;
            else {
                genes[i] = random.nextInt(8);
            }
        }
        Arrays.sort(genes);
        return genes;
    }

    public int[] getGenes() { return genes; }

    public boolean getIfDead() { return isDead;}

    public boolean getIfIsOffsprintOfTrackedAnimal() { return isOffspringOfTrackedAnimal; }

    public MapDirection getMapDirection() { return mapDirection; }

    public Vector2d getPosition() { return position; }

    //Setters
    public void setPosition(Vector2d vector) {this.position = vector; }

    public void setIfDead() { this.isDead = true; }

    public void setEnergy(double energy) { this.energy = energy; }

    public void setDirection(MapDirection direction) { this.mapDirection = direction; }

    public void setDominantGenotypes() {
        //TODO: implement
    }

    public void setIfIsOffspringOfTrackedAnimal(boolean isOffspringOfTrackedAnimal) {
        this.isOffspringOfTrackedAnimal = isOffspringOfTrackedAnimal;
    }

    public void changeEnergy(double difference) { this.energy += difference; }

    public void changeDirection() {
        Random random = new Random();
        int turnValue = this.genes[random.nextInt(32)];
        int resultValue = (this.mapDirection.getValueOfDirection() + turnValue) % 8;
        this.mapDirection = MapDirection.getDirectionFromValue(resultValue);
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
                    //notifyPositionChanged(position, tmpPosition);
                    position = tmpPosition;
                }
                break;
            case BACKWARD:
                tmpPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(tmpPosition)) {
                    //notifyPositionChanged(position, tmpPosition);
                    position = tmpPosition;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public static ArrayList<Animal> getTwoStrongest(ArrayList<Animal> animals){
        if (animals.size() > 1) {
            Animal tmpAnimal1 = animals.get(1);
            Animal tmpAnimal2 = animals.get(0);

            //first classification who is stronger in the first pair
            if (tmpAnimal1.getEnergy() >= tmpAnimal2.getEnergy()) {
                tmpAnimal1 = animals.get(0);
                tmpAnimal2 = animals.get(1);
            }

            //classification of the rest of left animals
            for (int i = 2; i < animals.size(); i++) {
                if(animals.get(i).getEnergy() > animals.get(1).getEnergy()) {
                    tmpAnimal2 = tmpAnimal1;
                    tmpAnimal1 = animals.get(i);
                } else if (animals.get(i).getEnergy() > tmpAnimal2.getEnergy()) {
                    tmpAnimal2 = animals.get(i);
                }
            }
            ArrayList<Animal> result = new ArrayList<>();
            result.add(tmpAnimal1);
            result.add(tmpAnimal2);
            return result;

        } else if (animals.size() == 1) return animals;

        return null;
    }

    public int[] getGenesFromParents(Animal parentA, Animal parentB) {

        int[] childGenes = new int[32];
        Random random = new Random();
        int firstBreakPoint, secondBreakPoint;
        do {
            firstBreakPoint = random.nextInt(32);
            secondBreakPoint = random.nextInt(32);
        } while (firstBreakPoint < secondBreakPoint);
        for (int i = 0; i < 32; i++) {
            if(i < firstBreakPoint || i >= secondBreakPoint){
                childGenes[i] = parentA.getGenes()[i];
            }
            else {
                childGenes[i] = parentB.getGenes()[i];
            }
        }
        Arrays.sort(childGenes);
        // checking if children have all type of genes
        int current;
        int expected = 0;
        int i = 0;
        while(i < 32){
            current = childGenes[i];
            if(current == expected){
                expected++;
            }
            else{
                int indexToChange = random.nextInt(32);
                childGenes[indexToChange] = expected;
                Arrays.sort(childGenes);
                i = 0;
                current = childGenes[0];
                expected = 0;
            }
            i++;
            // skip checking same values
            while( i < 32 && childGenes[i] == current){
                i++;
            }
        }
        return childGenes;
    }

    public static Animal reproduce (Animal parentA, Animal parentB) {
        double parentAEnergy = parentA.getEnergy();
        double parentBEnergy = parentB.getEnergy();
        if (parentA.getEnergy() >= 0.5 * parentA.getStartEnergy()
            && parentB.getEnergy() >= 0.5 * parentB.getStartEnergy()) {

            double childEnergy = 0.25 * parentAEnergy + 0.25 * parentBEnergy;
            parentA.changeEnergy(-0.25 * parentAEnergy);
            parentB.changeEnergy(-0.25 * parentBEnergy);

            parentA.numberOfChildren++;
            parentB.numberOfChildren++;

            int[] childGenes;
            childGenes = parentA.getGenesFromParents(parentA, parentB);
            Animal child = new Animal(parentA.map, parentA.getPosition(), childGenes);
            child.setEnergy(childEnergy);
            child.setIfIsOffspringOfTrackedAnimal(parentA.isOffspringOfTrackedAnimal
                                                  || parentB.isOffspringOfTrackedAnimal);
            return child;
        }
        return null;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void notifyPositionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver o: observers) {
            o.positionChanged(oldPosition, newPosition, this);
        }
    }

}
