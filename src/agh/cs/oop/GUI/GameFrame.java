package agh.cs.oop.GUI;

/**
 * Class implementing main frame that is root for other GUI components
 */

import agh.cs.oop.Constants.Constants;
import agh.cs.oop.Objects.WorldMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;

public class GameFrame extends JFrame implements ActionListener{

    WorldMap map;
    // size of grid square
    int sizeOfSquare;
    int xMargin;
    int yMargin;
    Timer timer;
    StatisticsPanel statisticsPanel;
    GamePanel gamePanel;

    public GameFrame() throws IOException {
        initializeVariables();
        initializeLayout();
    }

    private void initializeLayout() throws IOException {
        this.add(statisticsPanel, BorderLayout.LINE_START);
        this.add(gamePanel, BorderLayout.LINE_END);
        setTitle(Constants.TITLE);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void initializeVariables() throws IOException {
        this.sizeOfSquare = (int) ((Math.min(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT) /
                Math.max(Constants.NUMBER_OF_SQUARES_X, Constants.NUMBER_OF_SQUARES_Y)));
        this.xMargin = Constants.BOARD_WIDTH - sizeOfSquare * Constants.NUMBER_OF_SQUARES_X;
        this.yMargin = Constants.BOARD_HEIGHT - sizeOfSquare * Constants.NUMBER_OF_SQUARES_Y;
        this.map = new WorldMap(Constants.NUMBER_OF_SQUARES_Y, Constants.NUMBER_OF_SQUARES_X, Constants.START_ENERGY,
                                Constants.MOVE_ENERGY, Constants.PLANT_ENERGY, Constants.SIZE_OF_JUNGLE_SQUARE);
        map.placeNAnimalsOnMap(Constants.NUMBER_OF_ANIMALS);
        this.timer = new Timer(Constants.GAME_SPEED, this);
        this.gamePanel = new GamePanel(this.sizeOfSquare, this.xMargin, this.yMargin, map, this, timer);
        this.statisticsPanel = new StatisticsPanel(this.gamePanel, map, this.timer, this.yMargin);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.doOneLoop();
        if(map.getIfAllAnimalsAreDead()) {
            timer.stop();
            showMessageDialog(null, "All animals are dead!");
            statisticsPanel.disableButtons();
        } else {
            statisticsPanel.updateMapStats();
        }
    }
}
