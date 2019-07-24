package paoo.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import paoo.Items.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    private ArrayList<Item> items = new ArrayList<>();
    private Player player;
    private Item rando;
    private Item trial;
    private String level;

    public Board(String lvl) {
        Timer timer = new Timer(20, this);
        timer.start();
        level=lvl;
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new MonsterKeyListener());
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Map.BOARD_WIDTH, Map.BOARD_HEIGHT));
        player = new Player(10 * 16, 400, 4,"images/sprite.png");
        rando=new Item(14*16,400,"images/I_Map.png");
        initBlocks();
        BoardUtility.loadBoardUtility(items, player);
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
                            items.add(new Item(y * 48, x * 48,"images/stone2.png"));
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
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }
        for(Item a : Map.overlay) {
            if (a.isVisible())
                a.draw(g,this);
        }
        if(rando.isVisible())
            g.drawImage(rando.getImage(),rando.getX(),rando.getY(),this);
        if (player.isVisible()) {
            player.draw(g,this);
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
        BoardUtility.updateMonster();
        BoardUtility.updateRocks();
    }

    private class MonsterKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }

}
