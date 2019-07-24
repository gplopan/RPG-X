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
    int power;
    long lastFired = 0;
    ArrayList<Rock> rocks;

    Entity(int x, int y, String file)
    {
        super(x,y,file,false);
        rocks = new ArrayList<>();
    }

    public int getHealth() {
        return health;
    }

    public void downHealth(Rock rock) {
        this.health -= rock.getPower();
        if(health<=0)
        {
            vis=false;
        }
    }

    void upHealth(int i) {
        this.health += i;
    }

    public void draw(Graphics g, Board b) { g.drawImage(image, x, y,x+sw,y+sh,i*sw,j*sh,(i+1)*sw,(j+1)*sh, b); }

    public void move(ArrayList<Item> items){}

    public ArrayList<Rock> getRocks() {
        return rocks;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,sw,sh);
    }
}
