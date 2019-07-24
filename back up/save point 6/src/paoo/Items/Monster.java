package paoo.Items;

import paoo.Game.Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Entity {
    private boolean blocked=false;
    private String rockFile;

    public Monster(int x, int y, String sprite)
    {
        super(x,y,sprite);
        sh=48;
        sw=50;
        direction = 1;
        health=20;
        rockFile="rocket";
        isEnemy=true;
        power=15;
    }

    public Monster(int x, int y, String sprite,int sw, int sh, int pow, int hth, String rock)
    {
        super(x,y,sprite);
        vis=true;
        this.sh=sh;
        this.sw=sw;
        power=pow;
        health=hth;
        rockFile=rock;
        direction=1;
        isEnemy=true;
    }


    private void actuallyMove() {
        switch (direction) {
            case 0:
                j = 3;
                dy = -2;
                dx = 0;
                if (System.currentTimeMillis() - lastChanged > 250) {
                    if (i < 3) {
                        i++;
                        lastChanged = System.currentTimeMillis();
                    } else
                        i = 0;
                }
                break;
            case 1:
                dx = 2;
                dy = 0;
                j = 2;
                if (System.currentTimeMillis() - lastChanged > 250) {
                    if (i < 3) {
                        i++;
                        lastChanged = System.currentTimeMillis();
                    } else
                        i = 0;
                }
                break;
            case 2:
                j = 0;
                dy = 2;
                dx = 0;
                if (System.currentTimeMillis() - lastChanged > 250) {
                    if (i < 3) {
                        i++;
                        lastChanged = System.currentTimeMillis();
                    } else
                        i = 0;
                }
                break;
            case 3:
                dx = -2;
                dy = 0;
                j = 1;
                if (System.currentTimeMillis() - lastChanged > 250) {
                    if (i < 3) {
                        i++;
                        lastChanged = System.currentTimeMillis();
                    } else
                        i = 0;
                }
                break;
        }

        x += dx;
        y += dy;

        if (x > Map.BOARD_WIDTH - width + 3 * sw) {
            x = Map.BOARD_WIDTH - width + 3 * sw;
        }

        if (y > Map.BOARD_HEIGHT - (height - 3 * sh)) {
            y = Map.BOARD_HEIGHT - (height - 3 * sh);
        }
        if (x < 1) {
            x = 1;
            blocked=true;
        }
        if (y < 1) {
            y = 1;
            blocked=true;
        }
        if ((System.currentTimeMillis() - lastFired) > 2500) {
            fire();
            lastFired = System.currentTimeMillis();
        }
    }


    public void move(ArrayList<Item> items)
    {
        if(!blocked) {
            switch (direction) {
                case 0:
                    if (items.get(20 * ((y + sh / 2) / 48) + (x+sw/3) / 48).getPassable() && items.get(20 * ((y + sh / 2) / 48) + (x + sw-sw/3) / 48).getPassable())
                        actuallyMove();
                    else {
                        y = y - dy + 1;
                        blocked=true;
                        actuallyMove();
                    }
                    break;
                case 1:
                    if (items.get(20 * ((y + sh) / 48) + (x + sw) / 48).getPassable())
                        actuallyMove();
                    else {
                        x = x - dx - 1;
                        blocked=true;
                        actuallyMove();
                    }
                    break;
                case 2:
                    if (items.get(20 * ((y + sh) / 48) + (x+sw/3) / 48).getPassable() && items.get(20 * ((y + sh) / 48) + (x + sw-sw/3) / 48).getPassable())
                        actuallyMove();
                    else {
                        y = y - dy - 1;
                        blocked=true;
                        actuallyMove();
                    }
                    break;
                case 3:
                    if (items.get(20 * ((y + sh) / 48) + x / 48).getPassable())
                        actuallyMove();
                    else {
                        x = x - dx + 1;
                        blocked=true;
                        actuallyMove();
                    }
                    break;
            }
        }
        else
        {
            direction = ThreadLocalRandom.current().nextInt(0, 4);
            blocked=false;
        }

    }

    private void fire()
    {
        Rock aRock;
        if (direction == 0) {
            aRock = new Rock(x + sw / 2, y + sh / 2, 0, true, rockFile);
        }
        else if (direction == 1) {
            aRock = new Rock(x + sw / 2, y + sh / 2, 1, true, rockFile);
        }
        else if (direction == 2) {
            aRock = new Rock(x+sw/2, y+sh/2, 2, true,rockFile);
        }
        else {
            aRock = new Rock(x+sw/2, y+sh/2 , 3, true,rockFile);
        }
        rocks.add(aRock);
        aRock.setPower(power);
    }


    public void save(BufferedWriter bf)
    {
        try{
            bf.append(Boolean.toString(vis));
            bf.newLine();
            bf.append(Integer.toString(x));
            bf.newLine();
            bf.append(Integer.toString(y));
            bf.newLine();
            bf.append(Integer.toString(health));
            bf.newLine();
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-5);
        }
    }

    public void load(BufferedReader bf)
    {
        try{
            vis=Boolean.parseBoolean(bf.readLine());
            x=Integer.parseInt(bf.readLine());
            y=Integer.parseInt(bf.readLine());
            health=Integer.parseInt(bf.readLine());
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-9);
        }
    }
}
