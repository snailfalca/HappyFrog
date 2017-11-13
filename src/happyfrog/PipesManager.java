/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog;

import java.util.ArrayList;

/**
 *
 * @author snail
 */
public class PipesManager implements Runnable {

    private ArrayList<Pipes> pipesOnScreen = new ArrayList<>();
    private GameEngine gameEngine;

    public PipesManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        pipesOnScreen.add(new Pipes(gameEngine));
        new Thread(this).start();
    }

    public PipesManager(GameEngine gameEngine, ArrayList<Pipes> pipeses) {
        this.gameEngine = gameEngine;
        for (Pipes pipes : pipeses) {
            pipesOnScreen.add(pipes);
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (gameEngine.isAlive()) {
            //Only create if game is not paused
            if (gameEngine.isPlaying()) {
                try {
                    if (pipesOnScreen.get(0).locationX() < 0) {
                        pipesOnScreen.remove(0);
                    }
                    if (pipesOnScreen.get(pipesOnScreen.size() - 1).locationX() < 615) {
                        pipesOnScreen.add(new Pipes(gameEngine));
                    }

                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    //Remove all piples
    void cleanUp() {
        for (Pipes pipes : pipesOnScreen) {
            pipes.remove();
        }
    }

    ArrayList<String> getSaveData() {
        ArrayList<String> saveStrs = new ArrayList<>();

        for (Pipes pipes : pipesOnScreen) {
            saveStrs.add("" + pipes.locationX() + "\t" + pipes.getHeight() + "\t" + pipes.getWidth());
        }

        return saveStrs;
    }

}
