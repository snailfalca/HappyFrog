/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Bounds;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author snail
 */
public class GameEngine {

    private int points;
    private boolean alive;
    private Frog frog;
    private GameGUI gameGUI;
    private JPanel playPanel;
    private PipesManager pipesManager;

    /*Constructor:
    boolean resume: If true: Load saved game
    gameGUI: Game's graphic interface
     */
    public GameEngine(boolean resume, GameGUI gameGUI) {
        if (resume) {
            //If resume: Load saved data in SaveGame.txt
            FileReader fr = null;
            try {
                //Set init vaiables
                this.alive = true;
                this.gameGUI = gameGUI;
                this.playPanel = gameGUI.getPlayPanel();

                //Prepare to load data
                fr = new FileReader("SaveGame.txt");
                BufferedReader br = new BufferedReader(fr);

                //Load points
                this.points = Integer.parseInt(br.readLine());
                gameGUI.setPlaying(false);

                //Load frog
                frog = new Frog(this, Integer.parseInt(br.readLine()));

                //Read pipeses
                ArrayList<Pipes> pipeses = new ArrayList<>();
                String str = br.readLine();
                while (str != null) {
                    int positionX = Integer.parseInt(str.substring(0, str.indexOf('\t'))),
                            height = Integer.parseInt(str.substring(str.indexOf('\t') + 1, str.lastIndexOf('\t'))),
                            width = Integer.parseInt(str.substring(str.lastIndexOf('\t') + 1));
                    pipeses.add(new Pipes(this, positionX, height, width));
                    str = br.readLine();
                }
                //Create pipesManager
                this.pipesManager = new PipesManager(this, pipeses);

                //Close readers
                br.close();
                fr.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            gameGUI.setTextPauseBtn("Play");
        } else {
            //If not resume: Create new game
            this.points = 0;
            this.gameGUI = gameGUI;

            alive = true;
            playPanel = gameGUI.getPlayPanel();
            frog = new Frog(this);
            this.pipesManager = new PipesManager(this);
        }
    }

    //Return true if frog is alive
    public boolean isAlive() {
        return alive;
    }

    //If false: End game
    public void setAlive(boolean alive) {
        if (!alive) {
            this.alive = false;
            showGameResult(gameGUI.isSaving());
            gameGUI.stopPlaying();
        } else {
            this.alive = true;
        }
    }

    //Get bounds of (JLabel) frog 
    public Rectangle getFrogBounds() {
        return frog.getBounds();
    }

    //Return playPanel on gameGUI
    public JPanel getPlayPanel() {
        return playPanel;
    }

    //Make frog jumps
    public void frogJump() {
        frog.jump();
    }

    //Remove things on playPanel
    public void cleanUp() {
        frog.setVisible(false);
        pipesManager.cleanUp();
    }

    //Return false if the game is paused
    public synchronized boolean isPlaying() {
        return gameGUI.isPlaying();
    }

    //Add 1 to points
    void addPoint() {
        points += 1;
        gameGUI.setPoint(points);
    }

    //Show message with points and medals
    private void showGameResult(boolean saving) {
        if (!saving) {
            gameGUI.showEndGameGUI(points);
        }
    }

    //Get frog, pipes and score data in String
    ArrayList<String> getSavingData() {
        ArrayList<String> saveStrs = new ArrayList<>();

        saveStrs.add(Integer.toString(points));
        saveStrs.add(Integer.toString(frog.getY()));

        for (String string : pipesManager.getSaveData()) {
            saveStrs.add(string);
        }

        return saveStrs;
    }

    //TODO: Return level based on points
    int getLevel() {
        return 0;
    }

    //Save frog's Y, pipeses' Xs and points;
    void saveGameToFile() {
        FileWriter fw;
        try {
            fw = new FileWriter("SaveGame.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            ArrayList<String> saveStrs = getSavingData();

            for (String str : saveStrs) {
                bw.write(str);
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
