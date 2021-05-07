package agh.cs.oop.Objects;

import agh.cs.oop.Objects.AbstractWorldMap;
import agh.cs.oop.Interfaces.IPositionChangeObserver;

public class RectangularMap extends AbstractWorldMap implements IPositionChangeObserver {

    public RectangularMap(int height, int width) {
        mapHeight = height;
        mapWidth = width;
    }
}
