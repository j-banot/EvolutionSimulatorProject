package agh.cs.oop.Objects;

import agh.cs.oop.Objects.AbstractWorldMap;
import agh.cs.oop.Interfaces.IPositionChangeObserver;

public class RectangularMap extends AbstractWorldMap implements IPositionChangeObserver {

    public RectangularMap(int height, int width) {
        mapHeight = height;
        mapWidth = width;
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
