package paoo.Items;
import paoo.Game.Map;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<Rock> rocks;
    private long lastFired = 0;
    public boolean shield = false;
    private int speed=4;

    public Player(int x, int y, int lives, String file) {
        super(x, y,file);
        rocks = new ArrayList<>();
        direction = 2;
        this.lives = lives;
        sw=32;
        sh=48;
    }

    public ArrayList<Rock> getRocks() {
        return rocks;
    }

    public void downHealth() {
        if (shield == false) {
            this.health -= 1;
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


    public void fire() {
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
        int time;
        int key = e.getKeyCode();
        time = 250;
        if (key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - lastFired) > time) {
            fire();
            lastFired = System.currentTimeMillis();
        }
        else if (key == KeyEvent.VK_LEFT) {
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


}
