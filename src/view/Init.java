package view;

import organisms.Human;
import world.SaveLoad;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Init {
    private int sizeX = 20, sizeY = 20;
    private boolean hex = false;
    private int hx = 0, hy = 0;
    private JTextField tf1,tf2,tf3,tf4,tf5;

    String saveLoadFile = "save.dat";

    private JFrame s = new JFrame();

    private GridBagConstraints setLayout(){
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.weighty = 1;
        cons.gridx = 0;
        return cons;
    }

    public Init(){
        s.setLayout(new GridBagLayout());
        JLabel l1 = new JLabel("Wymiar X: ");
        tf1 = new JTextField(String.valueOf(sizeX));
        JLabel l2 = new JLabel("Wymiar Y: ");
        tf2 = new JTextField(String.valueOf(sizeY));
        JLabel l3 = new JLabel("Pozycja człowieka X:");
        tf3 = new JTextField(String.valueOf(hx));
        JLabel l4 = new JLabel("Pozycja człowieka Y:");
        tf4 = new JTextField(String.valueOf(hy));
        JLabel l5 = new JLabel("Miejsce zapisu i odczytu pliku");
        tf5 = new JTextField(saveLoadFile);
        JRadioButton rb1=new JRadioButton("Kwadrat");
        rb1.setSelected(true);
        rb1.setBounds(100,50,100,30);
        JRadioButton rb2=new JRadioButton("Szesciokat");
        rb2.setBounds(100,100,100,30);
        ButtonGroup bg=new ButtonGroup();
        JButton b=new JButton("OK");
        b.addActionListener((e)->{
            sizeX = Integer.parseInt(tf1.getText());
            sizeY = Integer.parseInt(tf2.getText());
            hx = Integer.parseInt(tf3.getText());;
            hy = Integer.parseInt(tf4.getText());
            saveLoadFile = tf5.getText();
            if(rb1.isSelected()){
                hex = false;
            }else if(rb2.isSelected()){
                hex = true;
            }
            new Window_game(new World(sizeX, sizeY, hex), new Human(hx, hy, null), new SaveLoad(null, saveLoadFile));
            s.dispose();
        });

        s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.pack();
        s.setSize(new Dimension(860,600));
        s.setLocationRelativeTo(null);

        GridBagConstraints a = setLayout();
        s.add(l1,a); s.add(tf1,a); s.add(l2,a);
        s.add(tf2,a); s.add(l3,a); s.add(tf3,a);
        s.add(l4,a); s.add(tf4,a); s.add(l5,a);
        s.add(tf5,a); bg.add(rb1);bg.add(rb2);
        s.add(rb1,a); s.add(rb2,a); s.add(b,a);
        s.setVisible(true);

    }

}
