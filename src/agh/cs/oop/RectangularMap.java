package agh.cs.oop;

public class RectangularMap extends AbstractWorldMap implements IPositionChangeObserver{

    public RectangularMap(int height, int width) {
        mapHeight = height;
        mapWidth = width;
    }
}
