package paoo.Game;

import paoo.Items.*;

import java.util.ArrayList;

class BoardUtility{
    private static ArrayList<Item> items = new ArrayList<>();

    static void loadBoardUtility(ArrayList<Item> _items) {
        items = _items;
    }


    static void collision(Entity ent, Entity ent2) {
        ArrayList<Rock> rocks = ent.getRocks();
        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            if (rock.isVisible()) {
                rock.collide(ent2);
            }
        }
    }


    static void updateRocks(Entity ent) {
        //if(ent!=null && ent.isVisible()) {
            ArrayList<Rock> rocks = ent.getRocks();
            for (int i = 0; i < rocks.size(); i++) {
                Rock rock = rocks.get(i);
                if (rock.isVisible()) {
                    rock.move();
                } else {
                    rocks.remove(rock);
                }
            }
       // }
    }


    static void updateEntity(Entity ent,Entity ent2) {
        if (ent != null && ent.isVisible()) {
            ent.move(items);
            if(ent2.isVisible())
                collision(ent,ent2);
        }
    }
}
