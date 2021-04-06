package agh.cs.oop;

public class SimulationEngine implements IEngine{

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        for (Vector2d position : positions) {
            map.place(new Animal(map, position));
        }
    }

    @Override
    public void run() {
        
    }
}
