package paoo.Items;

import java.util.ArrayList;

public class Monster extends Entity {
    Monster(int x, int y, String file)
    {
        super(x,y,file );
    }

    public ArrayList<Rock> getRocks()
    {
         ArrayList<Rock> a=new ArrayList<Rock>();
         return a;
    }
}
