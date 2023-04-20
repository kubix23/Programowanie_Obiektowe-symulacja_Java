package view;

import world.World;

import javax.swing.*;
import java.io.Serializable;

public class World_panel implements Serializable {
    private World world;
    private Hex_panel panel;
    private final int sizeX;
    private final int sizeY;

    public World_panel(World world, boolean hex){
        this.sizeX = world.getSizeX();
        this.sizeY = world.getSizeY();
        this.world = world;

        if(hex){
            panel = new Hex_panel(world,sizeX,sizeY,6);
        }else {
            panel = new Hex_panel(world,sizeX,sizeY,4);
        }
        panel.setBounds(0,0,getWidth(),getHeight());
    }

    public int getWidth() {
        return panel.getWidth();
    }

    public JPanel getPanel() {
        return panel;
    }

    public int getHeight() {
        return panel.getHeight();
    }

    public World getWorld(){
        return world;
    }

    public void updatePoint(int x, int y, String text){
        panel.getShape(x,y).setName(text);
    }
}
