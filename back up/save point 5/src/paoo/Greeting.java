package paoo;

import paoo.Game.Board;
import paoo.Game.GameView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Greeting extends JFrame{
    private JButton start=new JButton("Start");
    private JButton load=new JButton("Load");
    private JLabel label=new JLabel(new ImageIcon("images/titlecard.png"));
    private File savePoint;
    private String level="level";
    private Board panel;

    Greeting()
    {
        add(label);
        FlowLayout layout=new FlowLayout();
        label.setLayout(null);
        label.add(start);
        start.setBounds(140,333,70,20);
        load.setBounds(346,333,70,20);
        label.add(load);
        pack();
        init();

    }
    private void init()
    {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameView theView = new GameView();
                panel = new Board(level);
                panel.revalidate();
                panel.repaint();
                theView.getPanel().add(panel);
                panel.requestFocusInWindow();
                theView.setVisible(true);
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        start.setForeground(Color.WHITE);
        start.setContentAreaFilled(false);
        load.setForeground(Color.WHITE);
        load.setContentAreaFilled(false);

        setTitle("LINA'S ADVENTURES IN THE UNDERWORLD");
        requestFocusInWindow();
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new Greeting().setVisible(true); }
        });
    }

    private void loadGame()
    {
        System.out.println("loading game...");
        Connection c;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:savePoint.db");
            statement=c.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT Pointer FROM saved");
            savePoint=new File(resultSet.getString("Pointer"));
            statement.close();
            c.close();
            BufferedReader bf = new BufferedReader(new FileReader(savePoint));
            level = bf.readLine();
            panel=new Board(level);
            dispose();
            GameView theView = new GameView();
            panel.load(bf);
            panel.revalidate();
            panel.repaint();
            theView.getPanel().add(panel);
            panel.requestFocusInWindow();
            theView.setVisible(true);
            bf.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-11);
        }
        catch ( Exception e )
        {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(-14);
        }


    }
}
