package organisms;

import view.Logs;
import world.World;

public abstract class Animals extends Organism{

    public Animals(String name, int strength, int initiation, int posX, int posY, String symbol, int age, World world){
        super(name,strength,initiation,posX,posY,symbol,age,world);
    }

    @Override
    public void move(int newX, int newY){
        if (world.getCell(newX,newY) != null) {
            world.getCell(newX, newY).collision(this);
        }
        else {
            world.setCell(posX, posY, null);
            world.setCell(newX, newY, this);
            this.posX = newX;
            this.posY = newY;
        }
    }

    @Override
    public void action(){
        int[] newXY = {0,0};
        this.getNewXY(newXY,1);
        this.move(newXY[0],newXY[1]);
    }

    @Override
    public void collision(Organism attacker){
        if (!this.proliferation(attacker)) {
            Logs.addLog(attacker.name + '(' + attacker.posX + ',' + attacker.posY + ')' + " zaatakowal " +
                   name + '(' + posX + ',' + posY + ')');

            if (attacker.strength >= this.strength) {
                World temp = world;
                int newX = posX;
                int newY = posY;
                world.removeOrganism(this);
                attacker.move(newX, newY);
                Logs.addLog(" wygrywajac\n");
            }
		else {
                world.removeOrganism(attacker);
                Logs.addLog(" przegrywajac\n");
            }

        }
    }
}
