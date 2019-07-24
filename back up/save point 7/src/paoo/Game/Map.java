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


    private static final int[][] level1 = {
            {4, 2, 2, 2, 4, 4, 4, 4, 4, 2, 2, 2, 4, 4, 4, 2, 2, 2, 2, 2},
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

    private static ArrayList<Item> overlay1=new ArrayList<Item>(
            Arrays.asList(
                    new Item(2*48,14*48,"images/ugh.png",false),
                    new Item(14*48,15*48,"images/ugh.png",false),
                    new Item(17*48,13*48,"images/ugh.png",false),
                    new Item(13*48,13*48,"images/ugh.png",false),
                    new Item(11*48,13*48,"images/ugh.png",false),
                    new Item(19*48,4*48,"images/ugh.png",false),
                    new Item(2*48+10,0,"images/cavern.png",33,63,290,160,false),
                    new Item(15*48,34,"images/cavern.png",77,64,43,1,false),
                    new Item(14*48,0,"images/half2.png",false),
                    new Item(14*48,48,"images/half2.png",false),
                    new Item(15*48+77,34,"images/cavern.png",77,65,139,1,false),
                    new Item(15*48+2*77,34,"images/cavern.png",77,65,139,1,false),
                    new Item(55,-15,"images/cavern.png",34,63,351,33,false),
                    new Item(3*48+7,-15,"images/cavern.png",34,63,351,33,false),
                    new Item(10*48-12,-17,"images/cavern.png",77,65,139,1,false),
                    new Item(2*48,14*48,"images/ugh.png",false),
                    new Item(2*48,14*48,"images/ugh.png",false)
            )
    );

    private static ArrayList<Collectables> powers1=new ArrayList<>(
            Arrays.asList(
                    new Collectables(10*48,48,"images/health10.png",0,2),
                    new Collectables(3*48+12,48,"images/power5.png",3,5),
                    new Collectables(0,8*48,"images/speed2.png",1,2),
                    new Collectables(5*48,8*48,"images/score5.png",4,5),
                    new Collectables(3*48,14*48,"images/score50.png",4,50)
            )
    );

    private static Item savePoint1=new Item(3*48,8*48,"images/savePoint.png",34,34,0,0,true);

    private static final int[][] level2 = {
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 9, 9, 2, 2, 2, 2, 2, 2, 2, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 9, 9, 9, 2, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 2, 2, 9, 2, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 9, 2, 9, 2, 2, 2, 9, 9, 2, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 9, 2, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 9, 9, 9, 9, 9, 2, 2, 9, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 2, 2, 2, 2, 2, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 9, 9, 9, 9, 2, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 2, 2, 2, 2, 9, 9, 2, 9, 9, 2, 2, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 2, 9, 9, 2, 2, 2, 2, 2, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 2, 2, 2, 2, 9, 9, 9, 2, 2, 2, 2, 9, 9, 9, 9, 9, 9, 9},
            {2, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
    };

    private static ArrayList<Item> overlay2=new ArrayList<>(
            Arrays.asList(
                    new Item(23,23,"images/chest1.png")
            )
    );

    private static ArrayList<Collectables> powers2=new ArrayList<Collectables>(
            Arrays.asList(
                    new Collectables(90,140,"images/power15.png", 1,15)
            )
    );

    private static Item  savePoint2 = new Item(78,78,"images/savePoint.png");

    public static Item getSavePoint(String lvl)
    {
        switch (lvl) {
        case "level1":
            return savePoint1;
        case "level2":
            return savePoint2;
        default:
            throw new IllegalArgumentException("Nu exista acest nivel");
    }
    }

    static ArrayList<Item> getOverlay(String lvl)
    {
        switch (lvl) {
            case "level1":
                return overlay1;
            case "level2":
                return overlay2;
            default:
                throw new IllegalArgumentException("Nu exista acest nivel");
        }
    }

    static int[][] getLevel(String lvl)
    {
        switch (lvl) {
            case "level1":
                return level1;
            case "level2":
                return level2;
            default:
                throw new IllegalArgumentException("Nu exista acest nivel");
        }
    }

    public static ArrayList<Collectables> getPowers(String lvl)
    {
        switch (lvl) {
            case "level1":
                return powers1;
            case "level2":
                return powers2;
            default:
                throw new IllegalArgumentException("Nu exista acest nivel");
        }
    }

    static void loadPower(BufferedReader bf) {
        try {
            powers1 = null;
            powers1 = new ArrayList<>();
            String line;
            while (  (line=bf.readLine())!= null) {
                powers1.add(new Collectables(Integer.parseInt(line), Integer.parseInt(bf.readLine()), bf.readLine(), Integer.parseInt(bf.readLine()), Integer.parseInt(bf.readLine())));
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(-13);
        }
    }
}