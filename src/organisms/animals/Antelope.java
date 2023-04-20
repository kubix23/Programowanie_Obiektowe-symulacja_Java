package organisms.animals;

import organisms.Animals;
import organisms.Organism;
import view.Logs;
import world.World;

import java.util.Random;

public class Antelope extends Animals {
    public Antelope(int posX, int posY, World world) {
        super("Antylopa",4,4,posX,posY,"A",0,world);
    }

    @Override
    public void action(){
        int[] newXY = {0,0};
        this.getNewXY(newXY,2);
        this.move(newXY[0],newXY[1]);
    }

    @Override
    public void collision(Organism attacker){
        if (!this.proliferation(attacker)) {
            Logs.addLog(attacker.getName() + '(' + attacker.getPosX() + ',' + attacker.getPosY() + ')' + " zaatakowal " +
                        name + '(' + posX + ',' + posY + ')');
            Random rand = new Random();
            if (!this.proliferation(attacker)) {
                if (rand.nextInt(2) == 0) {
                    int newXY[] = {0,0}, i = 0;
                    do {
                        this.getNewXY(newXY, 1);
                        i++;
                    } while (world.getCell(newXY[0], newXY[1]) != null &&
                            i < 100);
                    if (i >= 100)return;
                    this.move(newXY[0], newXY[1]);
                }
                else {
                    super.collision(attacker);
                }
            }
        }
    }

}
