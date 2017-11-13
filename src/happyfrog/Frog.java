/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog;

import java.awt.Color;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author snail
 */
public class Frog extends JLabel implements Runnable {

    private boolean jumping;
    private GameEngine gameEngine;

    Frog(GameEngine gameEngine) {
        super(new ImageIcon("frog.png"));
        this.gameEngine = gameEngine;

        jumping = false;
        setBounds(150, 150, 30, 30);
        setVisible(true);
        new Thread(this).start();

        gameEngine.getPlayPanel().add(this);
    }

    Frog(GameEngine gameEngine, int positionY) {
        super(new ImageIcon("frog.png"));
        this.gameEngine = gameEngine;

        jumping = false;
        setBounds(150, positionY, 30, 30);
        setVisible(true);
        new Thread(this).start();

        gameEngine.getPlayPanel().add(this);

    }

    //Falls when not jumping
    @Override
    public void run() {
        while (gameEngine.isAlive()) {
            //Only falls when game is not paused
            if (gameEngine.isPlaying()) {
                try {
                    Thread.sleep(10 - gameEngine.getLevel());
                    if (getY() + 30 < 420) {
                        if (!jumping) {
                            setLocation(getX(), getY() + 2);
                        }
                    } else {
                        //Falls to ground, lose;
                        gameEngine.setAlive(false);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Frog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    void jump() {
        //Only jumps if not jumping and game is not paused
        if (!jumping && gameEngine.isPlaying()) {
            new Thread(new FrogJumpThread(this)).start();
        }
    }

    private class FrogJumpThread implements Runnable {

        Frog frog;

        public FrogJumpThread(Frog frog) {
            this.frog = frog;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                frog.setJumping(true);
                try {
                    if (frog.getY() > 0) {
                        frog.setLocation(frog.getX(), frog.getY() - 5);
                    }
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrogJumpThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(65);
                frog.setJumping(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(FrogJumpThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
