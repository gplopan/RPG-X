package paoo.Game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import paoo.Greeting;
import paoo.Items.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    private ArrayList<Item> items = new ArrayList<>();
    static private Player player;
    static private ArrayList<Monster> enemy;
    static private int nrEnemy=1;
    static public String level;
    static public Board instance=null;
    private volatile boolean gameOver=false;

    private Board(String lvl) {
        Timer timer = new Timer(20, this);
        timer.start();
        level=lvl;
        addKeyListener(new PlayerKeyListener());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Map.BOARD_WIDTH, Map.BOARD_HEIGHT));
        player = new Player(9 * 16, 400,"images/lina.png");
        initBlocks();
        BoardUtility.loadBoardUtility(items);
        //initBoard();
    }

  /*  private void initBoard() {
        addKeyListener(new PlayerKeyListener());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Map.BOARD_WIDTH, Map.BOARD_HEIGHT));
        player = new Player(9 * 16, 400,"images/lina.png");
        enemy=new ArrayList<>();
        enemy.add(new Monster(48,30,"images/death_scythe.png",50,48,8,20,"rocket"));
        enemy.add(new Monster(100,30,"images/pinkbat.png",32,48,8,10,"rocket"));
        initBlocks();
        BoardUtility.loadBoardUtility(items);
    }*/

    public static Board getInstance(String lvl)
    {
        if(instance==null)
            instance=new Board(lvl);
        return instance;
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
                            items.add(new Item(y * 48, x * 48,"images/stone12.png"));
                            break;
                        case STONE2:
                            items.add(new Item(y * 48, x * 48,"images/stone23.png",false));
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
                            items.add(new Item(y * 48, x * 48,"images/path25.png"));
                            break;
                        case STONE4:
                            items.add(new Item(y * 48, x * 48,"images/stone43.png"));
                            break;
                        case STONE190:
                            items.add(new Item(y * 48, x * 48,"images/stone1290.png"));
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
        for(Item a : Map.getOverlay(level)) {
            if (a.isVisible())
                a.draw(g,this);
        }
        for(Item a : Map.getPowers(level)) {
            if (a.isVisible())
                a.draw(g,this);
        }
        for(Item a : Map.getInteractables(level)) {
            if (a.isVisible())
                a.draw(g,this);
        }
        Map.getGate(level).draw(g,this);
        Map.getSavePoint(level).draw(g,this);
        if (player.isVisible()) {
            player.draw(g,this);
        }
        for(Monster a: enemy) {
            if (a.isVisible()) {
                a.draw(g, this);
            }
        }
        ArrayList<Rock> rocks = new ArrayList<>(player.getRocks());
        for (Rock rock : rocks) {
            if (rock.isVisible()) {
                g.drawImage(rock.getImage(), rock.getX(), rock.getY(), this);
            }
        }
        for(Monster a: enemy) {
            ArrayList<Rock> rocks2 = new ArrayList<>(a.getRocks());
            for (Rock rock : rocks2) {
                if (rock.isVisible()) {
                    g.drawImage(rock.getImage(), rock.getX(), rock.getY(), this);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(!gameOver)
            updateSprites();
            repaint();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println(player.getX()+" "+player.getY());
            for(Monster a: enemy)
                System.out.println(a.getY()+" "+a.getY());
            System.exit(-2);
        }

    }

    private boolean levelUp()
    {
        if(Map.getGate(level).getInteracted())// && player.getBounds().intersects(Map.getGate(level).getBounds()))
        {
           items.get(20 * ((Map.getGate(level).getY()+Map.getGate(level).getSh()/3) / 48) + (Map.getGate(level).getX()) / 48).setPassable(true);
           return true;
        }
        else return false;
    }

    private void updateSprites() {
        if (player.isAlive()) {
            for(Monster a: enemy) {
                BoardUtility.updateEntity(player, a);
                BoardUtility.updateEntity(a, player);
                BoardUtility.updateRocks(a);
            }
            BoardUtility.updateRocks(player);
            GameView.getLabel().setText("      SCORE: " + player.getScore() + "      HEALTH:" + player.getHealth() + "      LEVEL: " + level.toUpperCase());
            if (levelUp()) {
                int code=Integer.parseInt(level.split("level")[1]);
                code++;
                if(code>2)
                {
                    killAndDismember(-1);
                }
                else {
                    killAndDismember(code);
                }
            }
        } else if (!player.isAlive()) {
            gameOver = true;
            killAndDismember(0);
        }
    }

    private void killAndDismember(int code)
    {
        if(code==0)
        {
            GameView.getInstance().removeAll();
            GameView.getInstance().dispose();
            GameView.instance=null;
            Greeting.getInstance("images/game_over.png");
            Greeting.getInstance().setVisible(true);
            instance=null;
        }
        if(code>0)
        {
            if(Map.getGate(level).getBounds().contains(player.getBounds())){
                GameView.getInstance().removeAll();
                GameView.getInstance().dispose();
                GameView.instance=null;
                Greeting.getInstance().loadGame("level"+code);
                instance=null;
                //level="level"+code;
              //  initBlocks();
              //  BoardUtility.loadBoardUtility(items);
            }
        }
        if(code<0)
        {
            GameView.getInstance().removeAll();
            GameView.getInstance().dispose();
            GameView.instance=null;
            Greeting.getInstance("images/game_won.png");
            Greeting.getInstance().setVisible(true);
            instance=null;
        }
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
            bf.append("enemy");
            bf.newLine();
            bf.append(Integer.toString(nrEnemy));
            bf.newLine();
            for(Monster a: enemy)
                a.save(bf);
            bf.append("collectables");
            bf.newLine();
            for(Collectables a: Map.getPowers(level)) {
                if(a.isVisible())
                    a.save(bf);
            }
            bf.append("interactables");
            bf.newLine();
            for(Interactable a: Map.getInteractables(level))
                a.save(bf);
            bf.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-6);
        }
        Connection c;
        Statement statement;
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
        try {
            player.load(bf);
            nrEnemy = Integer.parseInt(bf.readLine());
            enemy=new ArrayList<>(nrEnemy);
            for(int i=0;i<nrEnemy;i++){
                Monster a=new Monster(0,0,bf.readLine());
                a.load(bf);
                enemy.add(a);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(-20);
        }
        Map.loadPower(bf);
        Map.loadInteractables(bf);
    }
}
