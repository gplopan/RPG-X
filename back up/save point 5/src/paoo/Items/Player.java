package paoo.Items;
import paoo.Game.Board;
import paoo.Game.Map;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class Player extends Entity {
    private long lastFired = 0;
    private boolean shield = false;
    private int speed=1;
    private int power;
    private int score=0;

    public Player(int x, int y, String file) {
        super(x, y,file);
        rocks = new ArrayList<>();
        direction = 2;
        sw=32;
        sh=48;
        power=15;
        health=30;
    }

    public void downHealth(int damage) {
        if (shield) {
            this.health -= 2*damage/3;
        }
        else
        {
            health -= damage;
        }
    }

    void upSpeed(int i) { speed+=i; }

    void upShield() { shield=true; }

    public boolean getShield() {return shield;}

    void upPower(int value) { power=value; }

    void upScore(int points){ score+=points; }

    private void collect()
    {
        for(Collectables a : Map.getPowers()) {
            a.picked(this);
        }
    }

    private void actuallyMove()
    {
        x += dx;
        y += dy;

        if (x > Map.BOARD_WIDTH - width+3*sw) {
            x = Map.BOARD_WIDTH - width+3*sw;
        }

        if (y > Map.BOARD_HEIGHT - (height-3*sh)) {
            y = Map.BOARD_HEIGHT - (height-3*sh);
        }
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }

    public void move(ArrayList<Item> items) {
        switch (direction)
        {
            case 0:
                if (items.get(20*((y+sh/2)/48)+x/48).getPassable() && items.get(20*((y+sh/2)/48)+(x+sw)/48).getPassable())
                    actuallyMove();
                else {
                    y = y - dy+1;
                    actuallyMove();
                }
                break;
            case 1:
                if (items.get(20*((y+sh)/48)+(x+sw)/48).getPassable())
                    actuallyMove();
                else {
                    x = x - dx-1;
                    actuallyMove();
                }
                break;
            case 2:
                if (items.get(20*((y+sh)/48)+x/48).getPassable() && items.get(20*((y+sh)/48)+(x+sw)/48).getPassable())
                    actuallyMove();
                else {
                    y = y - dy-1;
                    actuallyMove();
                }
                break;
            case 3:
                if (items.get(20*((y+sh)/48)+x/48).getPassable())
                    actuallyMove();
                else {
                    x = x - dx+1;
                    actuallyMove();
                }
                break;
        }
    }


    private void fire() {
        Rock aRock;
        if (direction == 0) {
            aRock = new Rock(x+sw/2, y+sh/2, 0, false,"rocket");
        }
        else if (direction == 1) {
            aRock = new Rock(x+sw/2 , y+sh/2 , 1, false,"rocket");
        }
        else if (direction == 2) {
            aRock = new Rock(x+sw/2, y+sh/2, 2, false,"rocket");
        }
        else {
            aRock = new Rock(x+sw/2, y+sh/2 , 3, false,"rocket");
        }
        rocks.add(aRock);
    }


    public void keyPressed(KeyEvent e) {
        int time=500;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - lastFired) > time) {
            fire();
            lastFired = System.currentTimeMillis();
        }
        if(key == KeyEvent.VK_C)
        {
            collect();
        }
        if(key == KeyEvent.VK_S)
        {
            if(x>Map.savePoint1.getX() && x+sw/2<=Map.savePoint1.getX()+Map.savePoint1.sw) {
                Board.save(new File("savePoint"));
            }
        }
        if (key == KeyEvent.VK_LEFT) {
            dx = -speed;
            dy = 0;
            j=1;
            if(System.currentTimeMillis()-lastChanged>250)
            {
                if(i<3)
                {
                    i++;
                    lastChanged=System.currentTimeMillis();
                }
                else
                    i=0;
            }
            direction = 3;
        }
        else if (key == KeyEvent.VK_RIGHT) {
            dx = speed;
            dy = 0;
            j=2;
            if(System.currentTimeMillis()-lastChanged>250)
            {
                if(i<3)
                {
                    i++;
                    lastChanged=System.currentTimeMillis();
                }
                else
                    i=0;
            }
            direction = 1;
        }
        else if (key == KeyEvent.VK_UP) {
            j=3;
            dy = -speed;
            dx = 0;
            if(System.currentTimeMillis()-lastChanged>250)
            {
                if(i<3)
                {
                    i++;
                    lastChanged=System.currentTimeMillis();
                }
                else
                    i=0;
            }
            direction = 0;
        }
        else if (key == KeyEvent.VK_DOWN) {
            j=0;
            dy = speed;
            dx = 0;
            if(System.currentTimeMillis()-lastChanged>250)
            {
                if(i<3)
                {
                    i++;
                    lastChanged=System.currentTimeMillis();
                }
                else
                    i=0;
            }
            direction = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        i=0;
    }

    public void save(BufferedWriter bf)
    {
        try{
            bf.append(Integer.toString(x));
            bf.newLine();
            bf.append(Integer.toString(y));
            bf.newLine();
            bf.append(Integer.toString(health));
            bf.newLine();
            bf.append(Integer.toString(power));
            bf.newLine();
            bf.append(Integer.toString(speed));
            bf.newLine();
            bf.append(Integer.toString(score));
            bf.newLine();
            bf.append(Boolean.toString(shield));
            bf.newLine();
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-4);
        }
    }

    public void load(BufferedReader bf)
    {
        try{
            x=Integer.parseInt(bf.readLine());
            y=Integer.parseInt(bf.readLine());
            health=Integer.parseInt(bf.readLine());
            power=Integer.parseInt(bf.readLine());
            speed=Integer.parseInt(bf.readLine());
            score=Integer.parseInt(bf.readLine());
            shield=Boolean.parseBoolean(bf.readLine());
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(-10);
        }
    }
}
