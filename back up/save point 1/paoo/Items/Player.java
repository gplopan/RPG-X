package paoo.Items;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import paoo.Game.Map;

public class Player extends Entity {
    private ArrayList<Rock> rocks;
    private long lastFired = 0;
    public int starLevel = 0;
    public boolean shield = false;

    public Player(int x, int y, int lives, String file) {
        super(x, y,file);
        rocks = new ArrayList<>();
        direction = 2;
        this.lives = lives;
        sw=32;
        sh=48;
    }

    public void upStarLevel() {
        starLevel += 1;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public ArrayList<Rock> getRocks() {
        return rocks;
    }

    public void downHealth() {
        if (shield == false) {
            this.health -= 1;
        }
    }


    public void move() {
        x += dx;
        y += dy;

        if (x > BOARD_WIDTH - width+3*sw) {
            x = BOARD_WIDTH - width+3*sw;
        }

        if (y > BOARD_HEIGHT - (height-3*sh)) {
            y = BOARD_HEIGHT - (height-3*sh);
        }
        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }


    public void fire() {
        Rock aRock;
        if (direction == 0) {
            aRock = new Rock(x+sw/2, y+sh/2, 0, false);
        }
        else if (direction == 1) {
            aRock = new Rock(x+sw/2 , y+sh/2 , 1, false);
        }
        else if (direction == 2) {
            aRock = new Rock(x+sw/2, y+sh/2, 2, false);
        }
        else {
            aRock = new Rock(x+sw/2, y+sh/2 , 3, false);
        }
        if (starLevel == 3) {
            aRock.upgrade();
        }
        rocks.add(aRock);
    }


    public void keyPressed(KeyEvent e) {
        int time;
        int key = e.getKeyCode();
        if (starLevel == 0) {
            time = 700;
        }
        else {
            time = 250;
        }
        if (key == KeyEvent.VK_SPACE && (System.currentTimeMillis() - lastFired) > time) {
            fire();
            lastFired = System.currentTimeMillis();
        }
        else if (key == KeyEvent.VK_LEFT) {
            dx = -2; //used to be 1, +for test speed
            dy = 0;
            if (starLevel > 1) {
                dx = -2;
            }
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
            dx = 2;
            dy = 0;
            if (starLevel > 1) {
                dx = 2;
            }
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
            dy = -2;
            dx = 0;
            if (starLevel > 1) {
                dy = -2;
            }
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
            dy = 2;
            dx = 0;
            if (starLevel > 1) {
                dy = 2;
            }
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
    }


}
