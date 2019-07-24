package paoo.Items;

import paoo.Game.Board;
import paoo.Game.Map;

import javax.swing.*;
import java.awt.*;

public class Item {
    int x;
    int y;
    int sw=48;
    int sh=48;
    int rcx=0;
    int rcy=0;
    int width;
    int height;
    boolean vis;
    Image image;
    String filename;
    boolean isEnemy=false;
    private boolean passable=true;

    public Item(int x, int y, String name)
    {
        this.x = x;
        this.y = y;
        vis = true;
        filename=name;
        loadImage(filename);
        getImageDimensions();
    }

    public Item(int x, int y, String name, Boolean pass)
    {
        this.x = x;
        this.y = y;
        vis = true;
        filename=name;
        loadImage(filename);
        getImageDimensions();
        passable=pass;
    }

    public Item(int x, int y, String name,int sww,int shh,int ii, int jj, Boolean pass)
    {
        this.x = x;
        this.y = y;
        vis = true;
        filename=name;
        loadImage(filename);
        getImageDimensions();
        passable=pass;
        sh=shh;
        sw=sww;
        rcx=ii;
        rcy=jj;
    }

    void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    void loadImage(String imageName) {
        filename=imageName;
        ImageIcon i = new ImageIcon(imageName);
        image = i.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSh(){ return sh;}

    public int getSw(){ return sw;}

    public boolean isVisible() {
        return vis;
    }

    public boolean isEnemy() { return isEnemy; }

    public void setVisible(Boolean visible) { vis = visible; }

    boolean getPassable() { return passable; }

    public void setPassable(Boolean pass) { passable=pass; }

    public String getFilename(){ return filename; }

    public void draw(Graphics g, Board b) { g.drawImage(image,x,y,x+sw,y+sh,rcx,rcy,rcx+sw,rcy+sh,b); }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
