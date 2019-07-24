package paoo.Game;

import paoo.Items.Entity;
import paoo.Items.Item;
import paoo.Items.Monster;
import paoo.Items.Rock;

import java.util.ArrayList;

public class BoardUtility {
    private static ArrayList<Item> items = new ArrayList<>();
    private static Entity entity;

    static void loadBoardUtility(ArrayList<Item> _items, Entity _entity) {
        items = _items;
        entity = _entity;
    }

    static void updateRocks() {
        ArrayList<Rock> rocks = entity.getRocks();

        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            if (rock.isVisible()) {
                rock.move();
            } else if (!rock.isVisible()) {
                rocks.remove(rock);
            }
        }
    }

    public static void updateMonster() {
        if (entity != null && entity.isVisible()) {
            entity.move(items);
        }
    }
}
