package organisms.plants;

import organisms.Organism;
import organisms.Plants;
import world.World;

public class Guarana extends Plants {
    public Guarana(int posX, int posY, World world) {
        super("Guarana",0,0,posX,posY,"g",0,world);
    }

    @Override
    public void collision(Organism attacker){
        attacker.setStrength(attacker.getStrength() + 3);
        super.collision(attacker);
    }
}
