package paoo;

import paoo.Game.Board;
import paoo.Game.GameView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Greeting extends JFrame{
    private String text;
    private boolean pressed=false;
    public JButton button=new JButton("OK");
    public TextField textField=new TextField(20);
    public JLabel label=new JLabel(new ImageIcon("images/background.jpg"));

    public Greeting()
    {
        add(label);
        label.add(new Label("THIS IS SHIT AND I WANT IT TO END"));
        label.add(new Label("Enter the player's name"));
        label.setLayout(new FlowLayout());
        label.add(textField);
        label.add(button);
        pack();
        init();

    }
    private void init()
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressed=true;
                text=textField.getText();
                dispose();
                GameView theView = new GameView();
                Board panel = new Board("level");
                panel.revalidate();
                panel.repaint();
                theView.getPanel().add(panel);
                panel.requestFocusInWindow();
                theView.setVisible(true);
            }
        });
        setTitle("NEW COOL GAME");
        requestFocusInWindow();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getText() {
        if(pressed) {
            return text;
        }
        else
            return "aaa";
    }


    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new Greeting().setVisible(true); }
        });
    }
}
