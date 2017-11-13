/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package happyfrog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameGUI extends javax.swing.JFrame {

    private boolean playing, saving;
    private GameEngine gameEngine;

    public GameGUI() {
        boolean resume = (JOptionPane.
                showConfirmDialog(rootPane, "Resume game?") == 0);
        initComponents();
        playing = true;
        saving = false;
        playPanel.requestFocus();
        if (resume) {
            gameEngine = new GameEngine(true, this);
        } else {
            gameEngine = new GameEngine(false, this);
        }
    }

    public static void main(String[] args) {
        //SET LOCK AND FEEL
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new GameGUI().setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playPanel = new javax.swing.JPanel();
        pauseBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        quitBtn = new javax.swing.JButton();
        pointsTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        playPanel.setBackground(new java.awt.Color(255, 255, 255));
        playPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                playPanelFocusLost(evt);
            }
        });
        playPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                playPanelKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout playPanelLayout = new javax.swing.GroupLayout(playPanel);
        playPanel.setLayout(playPanelLayout);
        playPanelLayout.setHorizontalGroup(
            playPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        playPanelLayout.setVerticalGroup(
            playPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        pauseBtn.setText("Pause");
        pauseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        quitBtn.setText("Quit");
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });

        pointsTxt.setText("Points: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pauseBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pointsTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseBtn)
                    .addComponent(saveBtn)
                    .addComponent(quitBtn)
                    .addComponent(pointsTxt))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void quitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitBtnActionPerformed

    private void playPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_playPanelKeyPressed
        //Jump by Space key
        if (evt.getKeyCode() == 32) {
            gameEngine.frogJump();
        }
        //Pause/Resume by P key
        if (evt.getKeyCode() == 80) {
            handlePausingGame();
        }
    }//GEN-LAST:event_playPanelKeyPressed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (gameEngine.isAlive()) {
            //Save game data when alive
            saveGame();
        } else {
            //Ask if user want to save game data when not alive
            if (JOptionPane.showConfirmDialog(this, "Game over!\nStill want to save?") == 0) {
                saveGame();
            } else {
                JOptionPane.showMessageDialog(this, "Saving aborted!");
            }
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void pauseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseBtnActionPerformed
        handlePausingGame();
    }//GEN-LAST:event_pauseBtnActionPerformed

    private void playPanelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_playPanelFocusLost
        playPanel.requestFocus();
    }//GEN-LAST:event_playPanelFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pauseBtn;
    private javax.swing.JPanel playPanel;
    private javax.swing.JLabel pointsTxt;
    private javax.swing.JButton quitBtn;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables

    //Create a new game
    void newGame() {
        playing = true;
        playPanel.repaint();
        playPanel.requestFocus();
        gameEngine.cleanUp();
        gameEngine = new GameEngine(false, this);
        pauseBtn.setText("Pause");
        pointsTxt.setText("Points: 0");
    }

    //Set playing to false & set pauseBtn text to Play
    void stopPlaying() {
        playing = false;
        pauseBtn.setText("Play");
    }

    public JPanel getPlayPanel() {
        return playPanel;
    }

    boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    void setPoint(int point) {
        pointsTxt.setText("Points: " + point);
    }

    private void handlePausingGame() {
        if (playing) {
            playing = false;
            pauseBtn.setText("Play");
        } else {
            if (gameEngine.isAlive()) {
                playing = true;
                pauseBtn.setText("Pause");
            } else {
                newGame();
            }
        }
        playPanel.requestFocus();
    }

    public boolean isSaving() {
        return saving;
    }

    void setTextPauseBtn(String play) {
        pauseBtn.setText("Play");
    }

    private void saveGame() {
        saving = true;
        gameEngine.saveGameToFile();
        gameEngine.setAlive(false);
        JOptionPane.showMessageDialog(rootPane, "Game saved!");
        gameEngine.cleanUp();
    }

    final int NO_MEDAL = 1,
            SILVER = 2,
            GOLD = 3;

    //Show end game panel based on points
    void showEndGameGUI(int points) {
        String endGameMessage = "Game over!"
                + "\nYour final points: " + points + "!";

        if (points >= NO_MEDAL && points < SILVER) {
            endGameMessage = endGameMessage.
                    concat("\nYou achieved a bronze medal!");
        } else if (points < GOLD) {
            endGameMessage = endGameMessage.
                    concat("\nYou achieved a silver medal!");
        } else {
            endGameMessage = endGameMessage.
                    concat("\nYou achieved a gold medal!");
        }

        JOptionPane.showMessageDialog(playPanel, endGameMessage);
    }

}
