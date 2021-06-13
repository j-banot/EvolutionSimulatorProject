package agh.cs.oop.Objects;

/**
 * Plant class specifies behaviour of second type of object that can be placed on WorldMap besides Animal
 */

import agh.cs.oop.Interfaces.IMapElement;

public class Plant implements IMapElement {

    private Vector2d position;
    private int energy;

    public Plant(int plantEnergy) {
        this.energy = plantEnergy;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getPlantEnergy() { return energy; }

    @Override
    public String toString() { return "*"; }
}
