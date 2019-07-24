package paoo.Items;

import paoo.Game.Board;
import paoo.Game.Map;

import javax.swing.*;
import java.awt.*;

public class Item {
    public int x;
    public int y;
    public int sw=48;
    public int sh=48;
    private int rcx=0;
    private int rcy=0;
    public int width;
    public int height;
    public boolean vis;
    public Image image;
    public String filename;
    public boolean passable=true;

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

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {
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

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) { vis = visible; }

    public boolean getPassable() { return passable; }

    public void setPassable(Boolean pass) { passable=pass; }

    public void draw(Graphics g, Board b) { g.drawImage(image,x,y,x+sw,y+sh,rcx,rcy,rcx+sw,rcy+sh,b); }
}
