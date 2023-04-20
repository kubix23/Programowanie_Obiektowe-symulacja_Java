package view;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.io.Serializable;

public class CustomShape extends Area implements Serializable {
    private String name = "";
    private int x;
    private int y;
    public CustomShape(GeneralPath path, int x, int y) {
        super(path);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawCell(Graphics2D g2d){
        if(this.hasName()){
            FontMetrics fm = g2d.getFontMetrics();
            int x = this.getBounds().x + (this.getBounds().width - fm.stringWidth(this.getName())) / 2;
            int y = this.getBounds().y + ((this.getBounds().height - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(this.getName(), x,y);
        }
        g2d.draw(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasName(){
        return !name.isEmpty();
    }
}
