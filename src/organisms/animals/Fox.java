package organisms.animals;

import organisms.Animals;
import world.World;

public class Fox extends Animals {
    public Fox(int posX, int posY, World world) {
        super("Lis",3,7,posX,posY,"L",0,world);
    }

    @Override
    public void action(){
        int newXY[] = {0,0}, i = 0;
        do {
            this.getNewXY(newXY, 1);
            i++;
        } while (world.getCell(newXY[0], newXY[1]) != null &&
                world.getCell(newXY[0], newXY[1]).getStrength() > strength &&
                i < 100);
        if (i >= 100)return;
        this.move(newXY[0], newXY[1]);
    }
}
