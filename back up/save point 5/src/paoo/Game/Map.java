package paoo.Game;

import paoo.Items.Collectables;
import paoo.Items.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    public static final int BOARD_WIDTH = 48 * 20;
    public static final int BOARD_HEIGHT = 48 * 16;


    private static final int[][] level = {
            {4, 4, 1, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2, 2, 2, 2, 2},
            {4, 11, 10, 11, 11, 10, 4, 4, 4, 4, 1, 4, 4, 4, 4, 2, 2, 2, 2, 2},
            {4, 4, 4, 4, 4, 1, 4, 10, 11, 11, 10, 4, 10, 11, 11, 11, 11, 11, 11, 11},
            {4, 4, 4, 4, 4, 1, 4, 1, 4, 4, 4, 4, 1, 11, 4, 4, 4, 4, 4, 4},
            {4, 4, 11, 10, 11, 10, 11, 10, 11, 11, 11, 11, 10, 4, 4, 4, 4, 4, 11, 4},
            {4, 4, 4, 1, 4, 1, 4, 4, 4, 1, 4, 4, 10, 11, 10, 4, 4, 4, 4, 1},
            {4, 4, 4, 1, 4, 1, 4, 4, 4, 4, 4, 4, 1, 4, 4, 1, 4, 1, 4, 4},
            {4, 4, 4, 1, 10, 1, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 10, 11, 11},
            {11, 11, 11, 10, 10, 11, 11, 11, 11, 11, 10, 11, 10, 4, 4, 4, 4, 10, 4, 4},
            {4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 4, 4, 1, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 11, 11, 10, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 1, 4, 4, 4, 4, 4, 4, 10, 11, 11},
            {4, 4, 4, 4, 4, 4, 4, 1, 4, 4, 1, 4, 4, 4, 4, 11, 11, 10, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 10, 11, 11, 11, 4, 4, 4, 11, 11, 10, 4, 11, 11},
            {11, 11, 4, 11, 11, 11, 10, 11, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 11, 4, 11, 11, 11, 11, 11}
    };

    static ArrayList<Item> overlay=new ArrayList<Item>(
            Arrays.asList(
                    new Item(2*48,14*48,"images/ugh.png",false),
                    new Item(14*48,15*48,"images/ugh.png",false),
                    new Item(17*48,13*48,"images/ugh.png",false),
                    new Item(13*48,13*48,"images/ugh.png",false),
                    new Item(11*48,13*48,"images/ugh.png",false),
                    new Item(19*48,4*48,"images/ugh.png",false),
                    new Item(2*48+10,0*48,"images/cavern.png",33,63,290,160,false),
                    new Item(2*48,14*48,"images/ugh.png",false),
                    new Item(2*48,14*48,"images/ugh.png",false)
            )
    );

    static ArrayList<Collectables> powers=new ArrayList<>(
            Arrays.asList(
                    new Collectables(0*48,8*48,"images/P_Orange05.png",0,2),
                    new Collectables(5*48,8*48,"images/I_Diamond.png",1,2)
            )
    );

    public static Item savePoint1=new Item(3*48,8*48,"images/savePoint.png",34,34,0,0,true);


    private static final int[][] level2 = {
            {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 3, 3, 0, 3, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0},
            {4, 0, 0, 3, 3, 0, 3, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0},
            {4, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0},
            {4, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2},
            {4, 6, 6, 7, 6, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2},
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2},
            {4, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

    };

    static int[][] getLevel(String lvl)
    {
        switch (lvl) {
            case "level":
                return level;
            case "level2":
                return level2;
            default:
                throw new IllegalArgumentException("Nu exista acest nivel");
        }

    }

    public static ArrayList<Collectables> getPowers()
    {
        return powers;
    }

    public static void loadPower(BufferedReader bf) {
        try {
            powers = null;
            powers = new ArrayList<>();
            String line;
            while (  (line=bf.readLine())!= null) {
                powers.add(new Collectables(Integer.parseInt(line), Integer.parseInt(bf.readLine()), bf.readLine(), Integer.parseInt(bf.readLine()), Integer.parseInt(bf.readLine())));
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-13);
        }
    }
}
