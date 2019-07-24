package paoo.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import paoo.Items.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    private ArrayList<Item> items = new ArrayList<>();
    static private Player player;
    static private Monster enemy;
    static private String level;

    public Board(String lvl) {
        Timer timer = new Timer(20, this);
        timer.start();
        level=lvl;
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new PlayerKeyListener());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Map.BOARD_WIDTH, Map.BOARD_HEIGHT));
        player = new Player(9 * 16, 400,"images/lina.png");
        enemy = new Monster(48,30,"images/death_scythe.png");
        initBlocks();
        BoardUtility.loadBoardUtility(items, player,enemy);
    }

    private void initBlocks() {
        int type;
        try {
            for (int x = 0; x < Map.getLevel(level).length; x++) {
                for (int y = 0; y < Map.getLevel(level)[0].length; y++) {
                    type = Map.getLevel(level)[x][y];
                    BlockType bType = BlockType.getTypeFromInt(type);
                    switch (bType) {
                        case GRASS:
                            items.add(new Item(y * 48, x * 48,"images/grass.png"));
                            break;
                        case STONE1:
                            items.add(new Item(y * 48, x * 48,"images/stone1.png"));
                            break;
                        case STONE2:
                            items.add(new Item(y * 48, x * 48,"images/stone2.png",false));
                            break;
                        case STONE3:
                            items.add(new Item(y * 48, x * 48,"images/stone3.png"));
                            break;
                        case LAVA:
                            items.add(new Item(y * 48, x * 48, "images/lava.png",false));
                            break;
                        case WOOD1:
                            items.add(new Item(y * 48, x * 48,"images/wood1.png"));
                            break;
                        case WOOD2:
                            items.add(new Item(y * 48, x * 48,"images/wood2.png"));
                            break;
                        case WOOD3:
                            items.add(new Item(y * 48, x * 48,"images/wood3.png"));
                            break;
                        case PATH1:
                            items.add(new Item(y * 48, x * 48,"images/path1.png"));
                            break;
                        case PATH2:
                            items.add(new Item(y * 48, x * 48,"images/path2.png"));
                            break;
                        case STONE4:
                            items.add(new Item(y * 48, x * 48,"images/stone4.png"));
                            break;
                        case STONE190:
                            items.add(new Item(y * 48, x * 48,"images/stone190.png"));
                            break;
                        case PATH190:
                            items.add(new Item(y * 48, x * 48,"images/path190.png"));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e);
            System.exit(-1);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        Toolkit.getDefaultToolkit().sync();
    }


    private void drawObjects(Graphics g) {
        for (Item a : items) {
            if (a.isVisible()) {
                a.draw(g,this);
            }
        }
        for(Item a : Map.overlay) {
            if (a.isVisible())
                a.draw(g,this);
        }
        for(Item a : Map.powers) {
            if (a.isVisible())
                a.draw(g,this);
        }
        if (player.isVisible()) {
            player.draw(g,this);
        }
        if (enemy.isVisible()) {
            enemy.draw(g,this);
        }
        if(Map.savePoint1.isVisible()) {
            Map.savePoint1.draw(g,this);
        }
        ArrayList<Rock> rocks = new ArrayList<>(player.getRocks());
        for (Rock rock : rocks) {
            if (rock.isVisible()) {
                g.drawImage(rock.getImage(), rock.getX(), rock.getY(), this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            updateSprites();
            repaint();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            System.exit(-2);
        }
    }

    private void updateSprites(){
        BoardUtility.updateEntity();
        BoardUtility.updateMonster();
        BoardUtility.updateRocks();
    }

    private class PlayerKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

    static public void save(File savePoint)
    {
        try{
            BufferedWriter bf=new BufferedWriter(new FileWriter(savePoint));
            bf.write(level);
            bf.newLine();
            player.save(bf);
            if(enemy!=null)
                enemy.save(bf);
            for(Collectables a: Map.powers) {
                if(a.isVisible())
                    a.save(bf);
            } bf.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-6);
        }
        Connection c=null;
        Statement statement=null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:savePoint.db");
            c.setAutoCommit(false);
            statement=c.createStatement();
            String s="INSERT INTO saved(Pointer) "+"VALUES('"+savePoint.getName()+"')";
            statement.executeUpdate(s);
            c.commit();
            statement.close();
            c.close();
        }
        catch ( Exception e )
        {
            System.out.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(-14);
        }

    }

    public void load(BufferedReader bf)
    {
        player.load(bf);
        enemy.load(bf);
        Map.loadPower(bf);
    }
}
