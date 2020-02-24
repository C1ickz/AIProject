/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_03_8puzzle;



/**
 *
 * @author ryanharris
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class MCGame extends JComponent {

    private int ballx = 150;
    private int bally = 30;
    private int ballx1 = 100;
    private int bally1 = 10;
    private int paddlex = 0;
    private int ballySpeed = 7;
    private int ballxSpeed = 5;
    private int bally1Speed = 14;
    private int ballx1Speed = 10;
    public int score = 0;
    public int score1 = 0;
    private int scorefinal;
    public int bestscore;
    public int bestscore1;
    public boolean gameOver, started;
    Agent agent = new Agent("M&C");
            Sequence actions = agent.getSolution();

    public static void main(String[] args) {
        Sequence actions = agent.getSolution();
        for(int i =0; i < actions.size(); i++){
            System.out.println(actions.get(i));
        }
        JFrame MCGame = new JFrame("M&C Game");
        MCGame g = new MCGame();
        MCGame.add(g);
        MCGame.pack();
        MCGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MCGame.setLocationRelativeTo(null);
        MCGame.setVisible(true);

     

    }

 
    @Override
    public Dimension getPreferredSize() {

        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //grass on each side
        g.setColor(Color.green.darker());
        g.fillRect(0,0,150,800);
        g.fillRect(650,0,150,800);
        
        //the water
        g.setColor(Color.CYAN);
        g.fillRect(150,0,500,800);
        
        //the boat
        g.setColor(new Color(102,51,0));
        g.fillRect(150,300, 60, 100);
        
        
        //the missonaries
        g.setColor(Color.red);
        int missionaryX = 50;
        int missionaryY = 350;
        int size = 40;
        g.setColor(Color.black);
        g.fillRect(missionaryX,missionaryY,size,size);
        g.fillRect(missionaryX,missionaryY + 50,size,size);
        g.fillRect(missionaryX,missionaryY + 100,size,size);


        //the cannibals
        int cannibalX = 50;
        int cannibalY = 300;
        g.setColor(Color.red);
        g.fillRect(cannibalX,cannibalY,size,size);
        g.fillRect(cannibalX,cannibalY-50, size,size);
        g.fillRect(cannibalX,cannibalY -100,size,size);

    }
}