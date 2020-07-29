package com.lopapa.obsdeck;

import javax.swing.*;
import java.awt.*;

public class VisualMain {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame frame = new JFrame("OBS AutoScene Swap");
        frame.setSize(600, 400);
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());

//        frame.setLayout(new JSplitPane());
        JPanel csvArea = new JPanel();
        JPanel buttonsArea = new JPanel();
        JSplitPane rootParent = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, csvArea, buttonsArea);

        rootParent.setLayout(new BoxLayout(rootParent, BoxLayout.PAGE_AXIS));


        frame.add(rootParent);

//        rootParent.add(csvArea);
//        rootParent.add(buttonsArea);

        csvArea.add(new JLabel("csvArea"), JSplitPane.LEFT);
        csvArea.add(new JTable(), JSplitPane.RIGHT);

        buttonsArea.add(new JLabel("buttonsArea"), JSplitPane.LEFT);
        buttonsArea.add(new JButton("Press me"), JSplitPane.RIGHT);
        buttonsArea.add(new JButton("Drink me"), JSplitPane.RIGHT);
        buttonsArea.add(new JButton("Hold me"), JSplitPane.RIGHT);

        //frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);
    }
}
