package organisms.plants;

import organisms.Organism;
import organisms.Plants;
import world.World;

public class Belladonna extends Plants {
    public Belladonna(int posX, int posY, World world) {
        super("Wilcza_jagoda", 99, 0, posX, posY,"j",0,world);
    }

    @Override
    public void collision(Organism attacker){
        super.collision(attacker);
        if(attacker.getStrength() < this.strength){
            world.removeOrganism(this);
        }

    }
}
