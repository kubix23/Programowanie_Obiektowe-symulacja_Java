package organisms.plants;

import organisms.Plants;
import world.World;

public class Grass extends Plants {
    public Grass(int posX, int posY, World world) {
        super("Trawa",0,0,posX,posY,"t",0,world);
    }
}
