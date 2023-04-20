package organisms.plants;

import organisms.Animals;
import organisms.Organism;
import organisms.Plants;
import world.World;

import java.util.Random;

public class Pine_borscht extends Plants {
    public Pine_borscht(int posX, int posY, World world) {
        super("Barszcz",10,0,posX,posY,"s",0,world);
    }

    @Override
    public void action(){
        Random rand = new Random();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(world.isHex()){
                    if(posY%2 == 0){
                        if((i == -1 && j == -1) || (i == 1 && j == -1))continue;
                    }else{
                        if((i == -1 && j == 1) || (i == 1 && j == 1))continue;
                    }
                }

                if (posX + j < world.getSizeX() && posX + j >= 0 &&
                    posY + i < world.getSizeY() && posY + i >= 0 ) {
                    Organism temp = world.getCell(posX + j, posY + i);
                    if (temp != null) {
                        if (temp instanceof Animals) {
                            temp.collision(this);
                        }
                    }
                }
            }
        }
        if (rand.nextInt(8) == 0) {
            this.proliferation(this);
        }
    }
}
