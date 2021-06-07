package agh.cs.oop.Objects;

import agh.cs.oop.Interfaces.IMapElement;
import agh.cs.oop.Interfaces.IPositionChangeObserver;
import agh.cs.oop.Objects.Vector2d;

public class Plant implements IMapElement {

    private Vector2d position;
    private int energy;

    public Plant(int plantEnergy) {
        this.energy = plantEnergy;
    }

    public Plant(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getPlantEnergy() { return energy; }

    @Override
    public String toString() { return "*"; }
}
