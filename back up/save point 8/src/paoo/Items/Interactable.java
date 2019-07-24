package paoo.Items;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;

public class Interactable extends Item {
    private int swa=48;
    private int sha=48;
    private int rcxa=0;
    private int rcya=0;
    private boolean interacted=false;
    private String afterName;
    private String key;

    public Interactable(int x, int y, String name1, String name2, String key)
    {
        super(x,y,name1);
        afterName=name2;
        this.key=key;
    }

    public Interactable(int x, int y, String name1, int sww, int shh, int ii, int jj, String key)
    {
        super(x,y,name1,sww,shh,ii,jj,false);
        this.key=key;
        afterName=name1;
    }

    public void setCoord(int sww, int shh, int ii, int jj)
    {
        sw=sww;
        sh=shh;
        rcx=ii;
        rcy=jj;
    }

    public void setAfterCoord(int sww, int shh, int ii, int jj)
    {
        swa=sww;
        sha=shh;
        rcxa=ii;
        rcya=jj;
    }

    public boolean getInteracted(){ return interacted; }

    void interact(Player p) {
        for (Item a : p.inventory.getInventory()) {
            if (key.equals(a.filename)) {
                if (p.getBounds().intersects(this.getBounds())) {
                    if(!interacted) {
                        this.loadImage(afterName);
                        setCoord(swa, sha, rcxa, rcya);
                        vis = true;
                        p.upScore(100);
                        interacted=true;
                    }
                }
            }
        }
    }

    public void save(BufferedWriter bf){
        try {
            bf.append(Integer.toString(x));
            bf.newLine();
            bf.append(Integer.toString(y));
            bf.newLine();
            bf.append(filename);
            bf.newLine();
            bf.append(afterName);
            bf.newLine();
            bf.append(key);
            bf.newLine();

        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(-18);
        }
    }
}
