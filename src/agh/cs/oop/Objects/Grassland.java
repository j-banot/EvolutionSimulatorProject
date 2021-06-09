package agh.cs.oop.Objects;

import agh.cs.oop.Interfaces.IPositionChangeObserver;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.sqrt;

public class Grassland extends AbstractWorldMap implements IPositionChangeObserver {

    int grassFieldsCount;

    public Grassland(int grassFields) {
        this.grassFieldsCount = grassFields;
        super.mapHeight = (int) sqrt(grassFieldsCount*10);
        super.mapWidth = (int) sqrt(grassFieldsCount*10);

        for (int i = 0; i < grassFields; i++) {
            boolean canBePlaced = false;
            while (!canBePlaced) { canBePlaced = this.generateGrassField(); }
        }
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Vector2d min = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d max = new Vector2d(0, 0);
        for (Animal animal : animals.values()) {
            Vector2d tmpPosition = animal.getPosition();
            min = tmpPosition.lowerLeft(min);
            max = tmpPosition.upperRight(max);
        }
        for (Plant plantField : grass.values()) {
            Vector2d tmpPosition = plantField.getPosition();
            min = tmpPosition.lowerLeft(min);
            max = tmpPosition.upperRight(max);
        }
        return map.draw(min, max);
    }

    public boolean generateGrassField() {
        // generate a vector from range (0, 0) - (sqrt(n*10), sqrt(n*10))
        int randX = ThreadLocalRandom.current().nextInt(0, (int) sqrt(grassFieldsCount * 10));
        int randY = ThreadLocalRandom.current().nextInt(0, (int) sqrt(grassFieldsCount * 10));
        //try to place grass field, if the place is occupied generate new coordinates
        Plant tmpPlant = new Plant(new Vector2d(randX, randY));
        return this.placeGrass(tmpPlant);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {

    }

    @Override
    public int getStartEnergy() {
        return 0;
    }

    @Override
    public int getDay() {
        return 0;
    }
}
