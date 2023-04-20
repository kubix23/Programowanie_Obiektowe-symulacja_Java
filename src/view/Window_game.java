package view;

import organisms.Human;
import world.SaveLoad;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Window_game implements Serializable {
    private static final JFrame frame = new JFrame("Gra");
    private World_panel world_panel;
    private Control_panel control_panel;
    private Logs log_panel;

    public static JFrame getFrame() {
        return frame;
    }

    public void setWorld_panel(World_panel world_panel) {
        frame.remove(this.world_panel.getPanel());
        this.world_panel = world_panel;
        this.world_panel.getWorld().updateWorld();
        this.world_panel.getPanel().setSize(200,200);
        this.world_panel.getPanel().setMinimumSize(new Dimension(200,200));
        add();
        world_panel.getPanel().invalidate();
        frame.repaint();
    }

    public Control_panel getControl_panel() {
        return control_panel;
    }

    public Window_game(World world, Human human, SaveLoad saveLoad){
        log_panel = new Logs();
        Logs.addLog("Autor: Jakub Buczkowski 188894");
        this.world_panel = world.getWorldPanel();
        this.control_panel = new Control_panel(this,world,human, saveLoad);

        add();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(860,600));
        frame.setMinimumSize(new Dimension(600,400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void add(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        frame.setLayout(layout);
        gbc.weightx = 3;
        frame.add(world_panel.getPanel(),gbc);
        gbc.gridx = 1;
        gbc.weightx = 2;
        gbc.weighty = 5;
        frame.add(log_panel.getScrollPane(),gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        gbc.gridwidth = 2;
        frame.add(control_panel.getControl(),gbc);
    }
}
