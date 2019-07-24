package paoo.Game;

import paoo.Items.Entity;
import paoo.Items.Item;
import paoo.Items.Monster;
import paoo.Items.Rock;

import java.util.ArrayList;

public class BoardUtility {
    private static ArrayList<Item> items = new ArrayList<>();
    private static Entity entity;
    private static Monster monster;

    static void loadBoardUtility(ArrayList<Item> _items, Entity _entity, Monster _monster) {
        items = _items;
        entity = _entity;
        monster=_monster;
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

    public static void updateEntity() {
        if (entity != null && entity.isVisible()) {
            entity.move(items);
        }
    }

    public static void updateMonster() {
        if (monster != null && monster.isVisible()) {
            monster.move(items);
        }
    }
}
