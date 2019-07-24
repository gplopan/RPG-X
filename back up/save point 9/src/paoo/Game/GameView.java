package paoo.Game;

import javax.swing.*;
import java.awt.*;

public class GameView extends javax.swing.JFrame {
    static public GameView instance=null;
    private JPanel gamePanel;
    static private JLabel label;

    private GameView(){
        initComponents();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameView.getInstance().setVisible(true);
            }
        });
   }

    private void initComponents(){
        gamePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LINA'S ADVENTURES IN THE UNDERWORLD");
        setPreferredSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 50));

        gamePanel.setMinimumSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 30));
        gamePanel.setSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 30));
        gamePanel.setLayout(new BoxLayout(gamePanel,BoxLayout.PAGE_AXIS));
        gamePanel.setBackground(new Color(32,62,86));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                       // .addComponent(label,)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   //.addComponent(label)
        );
        label=new JLabel("      SCORE: 0      HEALTH: 30      LEVEL: LEVEL 1");
        label.setOpaque(true);
        label.setBackground(new Color(32, 62, 86));
        label.setForeground(Color.WHITE);
        gamePanel.add(label);
        pack();
    }

    public JPanel getPanel(){
        return gamePanel;
    }

    static public GameView getInstance()
    {
      if(instance==null)
          instance=new GameView();
      return instance;
    }

    static JLabel getLabel(){ return label; }
}
