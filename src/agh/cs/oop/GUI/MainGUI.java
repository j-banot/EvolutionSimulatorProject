package agh.cs.oop.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI implements ActionListener {
    private JFrame frame;
    private JPanel startPanel;
    private JPanel mapPanel;
    private JButton startButton;
    private JLabel description;

    public MainGUI() {
        frame = new JFrame();
        startPanel = new JPanel();
        mapPanel = new JPanel();
        startButton = new JButton("START");
        description = new JLabel("This is a game Evolution Project. To start simulation press START button");

        startPanel.setBorder(BorderFactory.createEmptyBorder(300, 30, 10, 30));
        startPanel.setLayout(new FlowLayout());
        startPanel.add(description);
        startPanel.add(startButton);
        startPanel.setBackground(new Color(134, 197, 218));

        mapPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mapPanel.setLayout(new FlowLayout());
        mapPanel.setBackground(new Color(181, 249, 211));

        frame.add(startPanel, BorderLayout.CENTER);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Evolution Project");
        frame.setSize(1280,720);
        //frame.pack();
        frame.setVisible(true);

        startButton.addActionListener(this);
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(75, 107, 200));
        startButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        startButton.setPreferredSize(new Dimension(150, 70));

        description.setFont(new Font("Tahoma", Font.PLAIN, 30));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.remove(startPanel);
        frame.add(mapPanel);
        frame.validate();
    }
}
