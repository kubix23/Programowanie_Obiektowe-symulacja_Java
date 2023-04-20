package organisms.animals;

import organisms.Animals;
import organisms.Organism;
import view.Logs;
import world.World;

public class Turtle extends Animals {
    public Turtle(int posX, int posY, World world) {
        super("Zolw",2,1,posX,posY,"Z",0,world);
    }

    @Override
    public void collision(Organism attacker) {
        if (!this.proliferation(attacker)){
            Logs.addLog(attacker.getName() + '(' + attacker.getPosX() + ',' + attacker.getPosY() + ')' + " zaatakowal " +
                    name + '(' + posX + ',' + posY + ')');
            if (attacker.getStrength() >= 5 && attacker.getStrength() >= this.strength) {
                World temp = world;
                int newX = posX;
                int newY = posY;
                world.removeOrganism(this);
                attacker.move(newX, newY);
                Logs.addLog("wygrywajac\n");
            }else{
                Logs.addLog("remisujac\n");
            }
        }
    }

}
