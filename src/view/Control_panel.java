package view;

import general.Game;
import organisms.Human;
import view.lisener.TextSizeChange;
import world.SaveLoad;
import world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.io.Serializable;

public class Control_panel implements Serializable {
    private final Game game;
    private final JPanel control;
    private final JButton nextTurn;
    private final JButton load;
    private final JButton save;
    private final JButton quit;
    private ComponentListener sizeChange;

    private void addNextTurn(){
        nextTurn.setFocusPainted(false);
        nextTurn.addActionListener (e -> {
            game.run();
        });
        nextTurn.setEnabled(false);
        game.getHuman().addListener((is)->{
            nextTurn.setEnabled(is);
        });
        nextTurn.addComponentListener(new TextSizeChange(nextTurn,"Playbill"));
        control.add(nextTurn);
    }

    private void addLoad(){
        load.setFocusPainted(false);
        load.addActionListener (e -> {
            World temp = game.getSaveLoad().loadGame();
            game.setWorld(temp);
            temp.updateWorld();
        });
        load.addComponentListener(new TextSizeChange(load,"Playbill"));
        control.add(load);
    }

    private void addSave(){
        save.setFocusPainted(false);
        save.addActionListener (e -> {
            game.getSaveLoad().saveGame();
        });
        save.addComponentListener(new TextSizeChange(save,"Playbill"));
        control.add(save);
    }

    private void addQuit(){
        quit.setFocusPainted(false);
        quit.addActionListener (e -> {
            System.exit(0);
        });
        quit.addComponentListener(new TextSizeChange(quit,"Playbill"));
        control.add(quit);
    }

    public Control_panel(Window_game main,World world, Human human, SaveLoad saveLoad){
        control = new JPanel(new GridLayout());
        game = new Game(main,world,human,saveLoad);
        nextTurn = new JButton("NEXT");
        load = new JButton("LOAD");
        save = new JButton("SAVE");
        quit = new JButton("QUIT");
        addNextTurn();
        addLoad();
        addSave();
        addQuit();
        control.setMaximumSize(new Dimension(100,100));
    }

    public JPanel getControl() {
        return control;
    }
}
