package view;

import general.ClassTable;
import organisms.Organism;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

public class Select_organism implements Serializable {
    private static Class table[];
    private JPopupMenu select;
    private World world;
    private JButton buttons[];
    private int x, y;
    private Component parent;

    private GridBagConstraints setLayout(){
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.weighty = 1;
        cons.gridx = 0;
        return cons;
    }

    private void addButton(){
        GridBagConstraints layout= setLayout();
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new JButton(table[i].getSimpleName());
            buttons[i].setFocusPainted(false);
            final Class cla = table[i];
            buttons[i].addActionListener((event) ->{
                buttonAction(event, cla);
                parent.repaint();
            });
            select.add(buttons[i],layout);
        }
    }

    private void buttonAction(ActionEvent event, Class cla){
        try{
            Class[] t = { int.class, int.class, World.class };
            Organism te = (Organism) cla.getConstructor(t).newInstance(x, y, null);
            world.insertOrganism(te);
            world.updateWorld();
        }catch(Exception a){
            System.out.println(a);
        }
        select.setVisible(false);
    }

    public Select_organism(World w,int x, int y){
        if(table == null){
            ClassTable test = new ClassTable();
            Set<Class> classes = test.findAllClassesUsingClassLoader("organisms.animals");
            classes.addAll(test.findAllClassesUsingClassLoader("organisms.plants"));
            table = classes.toArray(new Class[classes.size()]);
            Arrays.sort(table, new Comparator<Class>() {
                @Override
                public int compare(Class o1, Class o2) {
                    int a = o1.getSimpleName().compareTo(o2.getSimpleName());
                    return a;
                }
            });
        }
        this.x = x;
        this.y = y;
        this.world = w;
        select = new JPopupMenu();
        buttons = new JButton[table.length];
        select.setLayout(new GridBagLayout());
        addButton();
        select.pack();
    }

    public void show(Component c,int x, int y){
        select.setVisible(true);
        parent = c;
        select.show(c,x, y);
    }
}
