package general;

import organisms.Human;
import organisms.Organism;
import view.Window_game;
import view.lisener.PlayerMove;
import world.SaveLoad;
import world.World;

import java.awt.*;

public class Game {
    private Window_game main;
    private World world;
    private Human human;
    private SaveLoad saveLoad;

    public Game(Window_game main,World world, Human human, SaveLoad saveLoad) {
        this.main = main;
        setWorld(world);
        setHuman(human);
        setSaveLoad(saveLoad);
        world.updateWorld();
    }

    public World getWorld() {
        return world;
    }

    public Human getHuman() {
        return human;
    }

    public void setWorld(World world){
        if (world == null)return;
        this.world = world;
        for (int i = 0; i < world.getOrganismsSize(); i++) {
            Organism temp = world.getOrganism(i);
            if (temp instanceof Human) {
                temp.setWorld(world);
                setHuman((Human) temp);
                break;
            }
        }
        if(main.getControl_panel() != null ){
            world.updateWorld();
            saveLoad = new SaveLoad(world,saveLoad.getPath());
            main.setWorld_panel(world.getWorldPanel());
        }
    }

    public void setHuman(Human human) {
        if(this.human != null)world.removeOrganism(this.human);
        human.setWorld(world);
        world.insertOrganism(human);
        this.human = human;
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new PlayerMove(human,world));
    }

    public SaveLoad getSaveLoad() {
        return saveLoad;
    }

    public void setSaveLoad(SaveLoad saveLoad) {
        saveLoad.setWorld(world);
        this.saveLoad = saveLoad;
    }

    public void run(){
            world.takeTurn();
            world.updateWorld();
    }
}
