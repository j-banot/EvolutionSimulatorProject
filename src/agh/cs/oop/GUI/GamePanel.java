package agh.cs.oop.GUI;

import agh.cs.oop.Constants.Constants;
import agh.cs.oop.Objects.Animal;
import agh.cs.oop.Objects.Plant;
import agh.cs.oop.Objects.Vector2d;
import agh.cs.oop.Objects.WorldMap;
import agh.cs.oop.Tools.GraphicTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class GamePanel extends JPanel implements MouseListener{

    private WorldMap map;
    private Timer timer;
    private GameFrame gameFrame;
    private int sizeOfSquare;
    private int xMargin;
    private int yMargin;
    private boolean markDominant = false;
    private Animal trackedAnimal = null;
    private boolean isFindAnimalToTrackModeOn = false;
    private boolean isAnimalTracked = false;
    private boolean isDeadAnnounced = false;
    private int numberOfChildrenWhenTrackingStarted = 0;
    private int dayOfTrackedAnimalDeath = 0;
    private GraphicTools graphics;

    public GamePanel(int sizeOfSquare, int xMargin, int yMargin, WorldMap map, GameFrame gameFrame,
                     Timer timer) throws IOException{
        initializeVariables(sizeOfSquare, xMargin, yMargin, map, gameFrame, timer);
        initializeLayout();
    }

    private void initializeVariables(int sizeOfSquare, int xMargin, int yMargin,
                                     WorldMap map, GameFrame gameFrame, Timer timer) throws IOException {
        graphics = new GraphicTools();
        this.sizeOfSquare = sizeOfSquare;
        this.xMargin = xMargin;
        this.yMargin = yMargin;
        this.map = map;
        this.gameFrame = gameFrame;
        this.timer = timer;
        addMouseListener(this);
    }

    private void initializeLayout() {
        setPreferredSize(new Dimension(Constants.BOARD_WIDTH - xMargin, Constants.BOARD_HEIGHT - yMargin));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage[][] mapGrid = new BufferedImage[Constants.NUMBER_OF_SQUARES_X][Constants.NUMBER_OF_SQUARES_Y];
        for (int x = 0; x < Constants.NUMBER_OF_SQUARES_X; x++) {
            for (int y = 0; y < Constants.NUMBER_OF_SQUARES_Y; y++) {
                if (!map.isOnJungle(x, y)) {
                    if (!map.isOccupied(new Vector2d(x, y))) {
                        mapGrid[x][y] = graphics.getSAND_SQUARE();
                    }
                    else if (map.objectAt(new Vector2d(x, y)) instanceof Plant) {
                        mapGrid[x][y] = graphics.getPLANT_ON_SAND();
                    } else {
                        Animal tmpAnimal = (Animal) map.objectAt(new Vector2d(x, y));
                        double energy = tmpAnimal.getEnergy();
                        if (energy < Constants.LEVEL1) mapGrid[x][y] = graphics.getWHITE_DINO_ON_SAND();
                        else if (energy < Constants.LEVEL2) mapGrid[x][y] = graphics.getBLUE_DINO_ON_SAND();
                        else if (energy < Constants.LEVEL3) mapGrid[x][y] = graphics.getRED_DINO_ON_SAND();
                        else mapGrid[x][y] = graphics.getPINK_DINO_ON_SAND();

                    }
                }
                else {
                    if (!map.isOccupied(new Vector2d(x, y))) {
                        mapGrid[x][y] = graphics.getGRASS_SQUARE();
                    }
                    else if (map.objectAt(new Vector2d(x, y)) instanceof Plant) {
                        mapGrid[x][y] = graphics.getPLANT_ON_GRASS();
                    } else {
                        Animal tmpAnimal = (Animal) map.objectAt(new Vector2d(x, y));
                        double energy = tmpAnimal.getEnergy();
                        if (energy < Constants.LEVEL1) mapGrid[x][y] = graphics.getWHITE_DINO_ON_GRASS();
                        else if (energy < Constants.LEVEL2) mapGrid[x][y] = graphics.getBLUE_DINO_ON_GRASS();
                        else if (energy < Constants.LEVEL3) mapGrid[x][y] = graphics.getRED_DINO_ON_GRASS();
                        else mapGrid[x][y] = graphics.getPINK_DINO_ON_GRASS();

                    }
                }
            }
        }
        for (int x = 0; x < Constants.NUMBER_OF_SQUARES_X; x++) {
            for (int y = 0; y < Constants.NUMBER_OF_SQUARES_Y; y++) {
                g.drawImage(mapGrid[x][y], x * sizeOfSquare, y * sizeOfSquare, sizeOfSquare, sizeOfSquare, null);
            }
        }
    }

    public void doOneLoop() {
        update();
        repaint(); // paintComponent method is going to be called
        if(this.trackedAnimal != null && isAnimalTracked && this.trackedAnimal.getIfDead() && !this.isDeadAnnounced){
            this.dayOfTrackedAnimalDeath = map.getDay();
            this.isDeadAnnounced = true;
            this.timer.stop();
            this.gameFrame.statisticsPanel.timerStop();
            showMessageDialog(null, "Tracked animal died on " + map.getDay() + " day [*]");
        }
        if(this.isAnimalTracked()){
            gameFrame.statisticsPanel.updateTrackedAnimalStats();
        }
    }

    public boolean isAnimalTracked() {
        return isAnimalTracked;
    }

    public boolean isFindAnimalToTrackModeOn() {
        return isFindAnimalToTrackModeOn;
    }


    public void toggleIsAnimalTracked() {
        this.isAnimalTracked = !this.isAnimalTracked;
    }

    public void toggleIsFindAnimalToTrackModeOn(){
        this.isFindAnimalToTrackModeOn = !this.isFindAnimalToTrackModeOn;
    }

    private void update() {
        map.nextDay();
    }

    public void setFindAnimalToTrackModeON(boolean trackAnimalModeON) {
        isFindAnimalToTrackModeOn = trackAnimalModeON;
    }

    public void setTrackedAnimal(Animal trackedAnimal) {
        this.trackedAnimal = trackedAnimal;
    }

    public int getNumberOfChildrenWhenTrackingStarted() {
        return numberOfChildrenWhenTrackingStarted;
    }

    public Animal getTrackedAnimal() {
        return trackedAnimal;
    }

    public int getDayOfTrackedAnimalDeath() {
        return dayOfTrackedAnimalDeath;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(!this.timer.isRunning() && !isFindAnimalToTrackModeOn){
            int x = e.getX() / sizeOfSquare;
            int y = e.getY() / sizeOfSquare;
            Object animal = map.objectAt(new Vector2d(x, y));
            if(animal instanceof Animal){
                StringBuilder genotype = new StringBuilder();
                int[] genotypeArray = ((Animal) animal).getGenes();
                for(int gen: genotypeArray){
                    genotype.append(" ").append(gen);
                }
                showMessageDialog(null, "Animal genotype: " + genotype);
            }
        }
        else if(!this.timer.isRunning() && isFindAnimalToTrackModeOn()){
            int x = e.getX() / sizeOfSquare;
            int y = e.getY() / sizeOfSquare;
            Object animal = map.objectAt(new Vector2d(x, y));
            if(animal instanceof Animal){
                this.isDeadAnnounced = false;
                this.numberOfChildrenWhenTrackingStarted = ((Animal) animal).getNumberOfChildren();
                ((Animal) animal).setIsOffspringOfTrackedAnimal(true);
                setTrackedAnimal((Animal)animal);
                toggleIsAnimalTracked();
                this.gameFrame.statisticsPanel.updateTrackedAnimalStats();
                repaint();
                toggleIsFindAnimalToTrackModeOn(); //setting false
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
