package organisms.plants;

import organisms.Plants;
import world.World;

public class Dandelion extends Plants {
    public Dandelion(int posX, int posY, World world) {
        super("Mlecz",0,0,posX,posY,"m",0,world);
    }

    @Override
    public void action(){
        for (int i = 0; i < 3; i++) {
            super.action();
        }
    }
}
