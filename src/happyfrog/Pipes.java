/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog;

import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author snail
 */
public class Pipes {

    private boolean pointAdded;
    private GameEngine gameEngine;
    private JButton upPipe, downPipe;
    int height, width;

    public Pipes(GameEngine gameEngine) {
        this.pointAdded = false;
        this.gameEngine = gameEngine;

        Random r = new Random();
        height = r.nextInt(10) * 15 + 51;
        width = r.nextInt(31) + 35;

        upPipe = new JButton(new ImageIcon());
        downPipe = new JButton();
        upPipe.setBounds(788, -10, width, height);
        downPipe.setBounds(788, height + 110, width, 688 - height);
        upPipe.setBackground(Color.BLACK);
        downPipe.setBackground(Color.BLACK);

        gameEngine.getPlayPanel().add(upPipe);
        gameEngine.getPlayPanel().add(downPipe);

        upPipe.setVisible(true);
        downPipe.setVisible(true);

        new Thread(new PipesMovement()).start();
    }

    public Pipes(GameEngine gameEngine, int positionX, int height, int width) {
        this.pointAdded = false;
        this.gameEngine = gameEngine;
        this.height = height;
        this.width = width;

        upPipe = new JButton(new ImageIcon());
        downPipe = new JButton();
        upPipe.setBounds(positionX, -10, width, height);
        downPipe.setBounds(positionX, height + 110, width, 688 - height);
        upPipe.setBackground(Color.BLACK);
        downPipe.setBackground(Color.BLACK);

        gameEngine.getPlayPanel().add(upPipe);
        gameEngine.getPlayPanel().add(downPipe);

        upPipe.setVisible(true);
        downPipe.setVisible(true);

        new Thread(new PipesMovement()).start();
    }

    void move() {
        int x = upPipe.getX();
        upPipe.setLocation(x - 1, upPipe.getY());
        downPipe.setLocation(x - 1, downPipe.getY());
    }

    void remove() {
        upPipe.setVisible(false);
        downPipe.setVisible(false);
    }

    int locationX() {
        return upPipe.getX();
    }

    boolean touchFrog() {
        if ((upPipe.getBounds().intersects(gameEngine.getFrogBounds()))
                || (downPipe.getBounds().intersects(gameEngine.getFrogBounds()))) {
            return true;
        }
        return false;
    }

    //Move pipes and handle if frog touches pipes
    private class PipesMovement implements Runnable {

        @Override
        public void run() {
            while (upPipe.getX() > -20 && gameEngine.isAlive()) {
                //Only moves if game is not paused 
                if (gameEngine.isPlaying()) {
                    try {
                        Thread.sleep(5);
                        move();
                        if (touchFrog()) {
                            gameEngine.setAlive(false);
                        }
                        if (!pointAdded && upPipe.getX() < gameEngine.getFrogBounds().getX()) {
                            gameEngine.addPoint();
                            pointAdded = true;
                        }
                    } catch (InterruptedException ex) {
                        System.out.println("Pipe movement error!");
                    }
                }
            }
            if (gameEngine.isAlive()) {
                remove();
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
