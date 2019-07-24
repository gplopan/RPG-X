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

import static javax.swing.SwingUtilities.isEventDispatchThread;

public class Greeting extends JFrame{
    static private JButton start=new JButton("Start");
    static private JButton load=new JButton("Load");
    static private JLabel label;
    private File savePoint;
    private String level="level";
    static Board panel;
    static public GameView theView;
    private static Greeting instance=null;
    private boolean loaded=false;

    private Greeting()
    {
        label=new JLabel(new ImageIcon("images/titlecard.png"));
        add(label);
        label.setLayout(null);
        label.add(start);
        start.setBounds(140, 333, 70, 20);
        load.setBounds(346, 333, 70, 20);
        label.add(load);
        pack();
        init();
    }

    private Greeting(String file)
    {
        label=new JLabel(new ImageIcon(file));
        add(label);
        label.setLayout(null);
        label.add(start);
        start.setBounds(140, 333, 70, 20);
        load.setBounds(346, 333, 70, 20);
        label.add(load);
        pack();
        init();
    }

    private void init()
    {
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance=null;
                dispose();
                theView = GameView.getInstance();
                panel = Board.getInstance(level);
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
                if(!loaded)
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

    public static Greeting getInstance(String str)
    {
        if(instance==null)
            instance=new Greeting(str);
        return instance;
    }

    public static Greeting getInstance()
    {
        if(instance==null)
            instance= new Greeting();
        return instance;
    }

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().isAlive());
                Greeting.getInstance().setVisible(true);
            }
        });
    }

    private void loadGame()
    {
        instance=null;
        loaded=true;
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
            panel=Board.getInstance(level);
            dispose();
            GameView theView =GameView.getInstance();
            panel.load(bf);
            panel.revalidate();
            panel.repaint();
            theView.getPanel().add(panel);
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
