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
    static private JButton start=new JButton("Start");
    static private JButton load=new JButton("Load");
    static private JLabel label;
    private File savePoint;
    private static String level="level1";
    static private Board panel;
    static private GameView theView;
    private static Greeting instance=null;

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
                loadGame("level1");
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame("Pointer");
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
                Greeting.getInstance().setVisible(true);
            }
        });
    }

    public void loadGame(String label)
    {
        instance=null;
        dispose();
        Connection c;
        Statement statement;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:savePoint.db");
            statement=c.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT "+label+" FROM saved");
            savePoint=new File(resultSet.getString(label));
            statement.close();
            c.close();
            BufferedReader bf = new BufferedReader(new FileReader(savePoint));
            level = bf.readLine();
            panel=Board.getInstance(level);
            theView = GameView.getInstance();
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
