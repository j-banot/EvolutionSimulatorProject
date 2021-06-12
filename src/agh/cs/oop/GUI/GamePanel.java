package agh.cs.oop.GUI;

import agh.cs.oop.Objects.Animal;
import agh.cs.oop.Objects.WorldMap;
import agh.cs.oop.Tools.ImageTools;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {

    private WorldMap map;
    private Timer timer;
    private GameFrame gameFrame;
    private int sizeOfSquare;
    private int xMargin;
    private int yMargin;
    private boolean markDominant = false;
    private Animal trackedAnimal = null;
    private boolean isTrackingModeOn = false;
    private boolean isAnimalBeingTracked = false;
    private boolean isDeadAnnounced = false;
    private int numberOfChildrenOnTrackingStart = 0;
    private int dayOfTrackedAnimalDeath = 0;
    private ImageTools images;

    public GamePanel(int sizeOfSquare, int xMargin, int yMargin, WorldMap map, GameFrame gameFrame, Timer timer) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
