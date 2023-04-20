package organisms;

import view.Logs;
import world.World;

import java.util.Random;

public abstract class Plants extends Organism{

    private view.Logs Logs;

    public Plants(String name, int strength, int initiation, int posX, int posY, String symbol, int age, World world){
        super(name,strength,initiation,posX,posY,symbol,age,world);
    }

    @Override
    public void move(int newX, int newY) {};

    @Override
    public void action() {
        Random rand = new Random();
        if (rand.nextInt(4) == 0) {
            this.proliferation(this);
        }
    }
    @Override
    public void collision(Organism attacker) {
            Logs.addLog(attacker.name + '(' + attacker.posX + ',' + attacker.posY + ')' + " zblizyl sie do " +
                   name + '(' + posX + ',' + posY + ')');

            if (attacker.getStrength() >= this.strength) {
                World temp = world;
                int newX = posX;
                int newY = posY;
                world.removeOrganism(this);
                attacker.move(newX, newY);
                Logs.addLog(" i go zjadl\n");
            }
        else {
                world.removeOrganism(attacker);
                Logs.addLog(" i przegral z roslina\n");
            }
    }
}
