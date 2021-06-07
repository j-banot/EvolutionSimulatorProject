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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement {

    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private IWorldMap map;
    private int dayOfBirth;
    private int numberOfChildren;
    private ArrayList<Integer> dominantGenotypes;
    private int[] genes;
    private int startEnergy;
    private double energy;
    private boolean isDead = false;
    private boolean isOffspringOfTrackedAnimal = false;
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    private MapDirection mapDirection;
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
        this.genes = getRandomGenes();
        addObserver(map);
        setDominantGenotypes();
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.dayOfBirth = this.map.getDay();
        this.numberOfChildren = 0;
        this.startEnergy = map.getStartEnergy();
        this.energy = this.getStartEnergy();
        this.genes = getRandomGenes();
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

    private int[] getRandomGenes(){
        //TODO: implement
        return new int[1];
    }

    public int[] getGenes() { return genes; }

    public boolean getIfDead() { return isDead;}

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    //Setters
    public void setPosition(Vector2d vector) {this.position = vector; }

    public void setIfDead() { this.isDead = true; }

    public void setEnergy(double energy) { this.energy = energy; }

    public void setDirection(MapDirection direction) { this.direction = direction; }

    public void setDominantGenotypes() {
        //TODO: implement
    }

    public void changeEnergy(double difference) { this.energy += difference; }

    public void changeDirection() {
        //TODO: implement
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
                    positionChanged(position, tmpPosition);
                    position = tmpPosition;
                }
                break;
            case BACKWARD:
                tmpPosition = position.subtract(mapDirection.toUnitVector());
                if (map.canMoveTo(tmpPosition)) {
                    positionChanged(position, tmpPosition);
                    position = tmpPosition;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public static ArrayList<Animal> getTwoStrongest(ArrayList<Animal> animals){
        //TODO: implement
        return null;
    }

    public static Animal reproduce (Animal parentA, Animal parentB) {
        //TODO: implement
        return null;
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
    private void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver o: observers){
            o.positionChanged(oldPosition, newPosition, this);
        }
    }

}
