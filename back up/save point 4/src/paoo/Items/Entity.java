package paoo.Items;

import paoo.Game.Board;
import paoo.Game.Map;
import java.awt.*;
import java.util.ArrayList;

abstract public class Entity extends Item {
    public int dx;
    public int dy;
    public int i=0;
    public int j=0;
    public int direction;
    public int health;
    public int lives;
    public long lastChanged=0;

    public Entity(int x, int y, String file)
    {
        super(x,y,file,false);
    }

    public int getHealth() {
        return health;
    }

    public void downHealth() {
            this.health -= 1;
    }

    public void upHealth() {
        this.health += 1;
    }

    public int getLives() {
        return this.lives;
    }

    public void upLives() {
        this.lives += 1;
    }

    public void draw(Graphics g, Board b) { g.drawImage(image, x, y,x+sw,y+sh,i*sw,j*sh,(i+1)*sw,(j+1)*sh, b); }

    public void move(ArrayList<Item> items){}

    abstract public ArrayList<Rock> getRocks();
}
