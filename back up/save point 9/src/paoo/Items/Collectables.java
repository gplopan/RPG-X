package paoo.Items;

import paoo.Game.Map;

import java.io.BufferedWriter;
import java.io.IOException;

public class Collectables extends Item{
    private boolean picked=false;
    private int power;
    private int value;

    public Collectables(int x, int y, String file, int pow, int val)
    {
        super(x,y,file);
        power=pow;
        value=val;
    }

    public boolean getPicked() {return picked; }

    public int getPower(){ return power; }

    public int getValue() { return value; }

    void picked(Player p)
    {
        if(p.getBounds().intersects(this.getBounds()))
        {
            vis=false;
            if(!picked) {
                switch (power) {
                    case 0:
                        p.upHealth(value);
                        break;
                    case 1:
                        p.upSpeed(value);
                        break;
                    case 2:
                        p.upShield();
                        break;
                    case 3:
                        p.upPower(value);
                        break;
                    case 4:
                        p.upScore(value);
                        break;
                    default:
                        break;
                }
            }
            picked=true;
            p.inventory.add(this);
        }
    }

    public void save(BufferedWriter bf)
    {
        try {
            bf.append(Integer.toString(x));
            bf.newLine();
            bf.append(Integer.toString(y));
            bf.newLine();
            bf.append(filename);
            bf.newLine();
            bf.append(Integer.toString(power));
            bf.newLine();
            bf.append(Integer.toString(value));
            bf.newLine();
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(-12);
        }
    }
}
