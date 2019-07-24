package paoo.Items;

import paoo.Game.Board;
import java.awt.*;
import java.util.ArrayList;

abstract public class Entity extends Item {
    int dx;
    int dy;
    int i=0;
    int j=0;
    int direction;
    int health;
    long lastChanged=0;
    ArrayList<Rock> rocks;
    Entity(int x, int y, String file)
    {
    super(x,y,file,false);
    }

    public int getHealth() {
        return health;
    }

    public void downHealth(int damage) {
            this.health -= damage;
    }

    void upHealth(int i) {
        this.health += i;
    }

    public void draw(Graphics g, Board b) { g.drawImage(image, x, y,x+sw,y+sh,i*sw,j*sh,(i+1)*sw,(j+1)*sh, b); }

    public void move(ArrayList<Item> items){}

    public ArrayList<Rock> getRocks() {
        return rocks;
    }
}
