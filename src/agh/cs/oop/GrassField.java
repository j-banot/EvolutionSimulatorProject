package agh.cs.oop;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{

    int grassFieldsCount;

    public GrassField (int grassFields) {
        this.grassFieldsCount = grassFields;
        super.mapHeight = (int) sqrt(grassFieldsCount*10);
        super.mapWidth = (int) sqrt(grassFieldsCount*10);

        for (int i = 0; i < grassFields; i++) {
            boolean canBePlaced = false;
            while (!canBePlaced) {
                // generate a vector from range (0, 0) - (sqrt(n*10), sqrt(n*10))
                int randX = ThreadLocalRandom.current().nextInt(0, (int) sqrt(grassFieldsCount * 10));
                int randY = ThreadLocalRandom.current().nextInt(0, (int) sqrt(grassFieldsCount * 10));
                Grass tmpGrass = new Grass(new Vector2d(randX, randY));
                //try to place grass field, if the place is occupied generate new coordinates
                canBePlaced = this.placeGrass(tmpGrass);
            }
        }
    }

    @Override
    public String toString() {
        MapVisualizer map = new MapVisualizer(this);
        Vector2d min = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Vector2d max = new Vector2d(0, 0);
        for (Animal animal : animals) {
            Vector2d tmpPosition = animal.getPosition();
            min = tmpPosition.lowerLeft(min);
            max = tmpPosition.upperRight(max);
        }
        for (Grass grassField : grass) {
            Vector2d tmpPosition = grassField.getPosition();
            min = tmpPosition.lowerLeft(min);
            max = tmpPosition.upperRight(max);
        }
        return map.draw(min, max);
    }
}
