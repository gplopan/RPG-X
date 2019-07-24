package paoo.Game;

import javax.swing.*;

public class GameView extends javax.swing.JFrame {
    static GameView instance=null;
    private JPanel gamePanel;

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
       // setPreferredSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 30));
        setPreferredSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 30));

        gamePanel.setMinimumSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 30));
        gamePanel.setSize(new java.awt.Dimension(Map.BOARD_WIDTH + 16, Map.BOARD_HEIGHT + 30));
        gamePanel.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
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

}
