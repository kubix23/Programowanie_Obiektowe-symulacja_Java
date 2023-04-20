package organisms;

import view.Logs;
import world.World;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public abstract class Organism implements Serializable {

    protected String name = "";
    protected int strength = 0;
    protected int initiation = 0;
    protected int posX = 0;
    protected int posY = 0;
    protected String symbol = "";
    protected int age= 0;
    protected World world = null;

    public Organism(String name, int strength, int initiation, int posX, int posY, String symbol, int age, World world) {
        this.name = name;
        this.strength = strength;
        this.initiation = initiation;
        this.posX = posX;
        this.posY = posY;
        this.symbol = symbol;
        this.age = age;
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiation() {
        return initiation;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getSymbol(){
        return symbol;
    }

    public int getAge() {
        return age;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void getNewXY(int[] newXY, int range){
        Random rand = new Random();
        if(world.isHex()){
            do{
                newXY[0] = posX + rand.nextInt(range*2 + 1) - range;
                newXY[1] = posY + rand.nextInt(range*2 + 1) - range;

                if(posY % 2 == 1){
                    if((newXY[0] == posX + 1 && newXY[1] == posY + 1) ||
                            (newXY[0] == posX + 1 && newXY[1] == posY - 1)) {
                        newXY[0] = -1;
                        continue;
                    }
                }else{
                    if((newXY[0] == posX - 1 && newXY[1] == posY + 1) ||
                            (newXY[0] == posX - 1 && newXY[1] == posY - 1)){
                        newXY[0] = -1;
                        continue;
                    }
                }
            }while(newXY[0] >= world.getSizeX() || newXY[0] < 0 ||
                    newXY[1] >= world.getSizeY() || newXY[1] < 0 ||
                    (newXY[0] == posX && newXY[1] == posY));
        }else{
            do{
                newXY[0] = posX + rand.nextInt(range*2 + 1) - range;
                newXY[1] = posY + rand.nextInt(range*2 + 1) - range;
            }while(newXY[0] >= world.getSizeX() || newXY[0] < 0 ||
                    newXY[1] >= world.getSizeY() || newXY[1] < 0 ||
                    (newXY[0] == posX && newXY[1] == posY));
        }

    }

    public void yearOlder(int years){
        age += years;
    }

    public boolean proliferation(Organism attacker){
        Random rand = new Random();
        if (this.getClass().equals(attacker.getClass()) && this.age >= 10 && attacker.age >= 10 && rand.nextInt(age) < Math.sqrt(age)) {
            int newXY[] = {0,0}, i = 0;
            do {
                getNewXY(newXY, 1);
                i++;
            } while (world.getCell(newXY[0], newXY[1]) != null && i < 100);
            if (i < 100) {
                Organism temp = null;
                try {
                    Class[] t = { int.class, int.class, World.class };
                    temp = (Organism) this.getClass().getConstructor(t).newInstance(0, 0, null);
                }catch (Exception e) {
                    System.out.println(e);
                }
                temp.setPosX(newXY[0]);
                temp.setPosY(newXY[1]);
                world.insertOrganism(temp);
                Logs.addLog(name + '(' + posX + ',' + posY + ')' + " rozmnozyl sie z " +
                        attacker.name + '(' + attacker.posX + ',' + attacker.posY + ')' + " rodzac " +
                        temp.name + '(' + temp.getPosX() + ',' + temp.getPosY() + ')' + '\n');
            }
        }
        if(!(this.getClass().equals(attacker.getClass())))return false;
        return true;
    }

    public abstract void action();
    public abstract void collision(Organism attacker);
    public abstract void move(int newX, int newY);
}
