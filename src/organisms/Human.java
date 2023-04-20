package organisms;

import view.lisener.CustomListener;
import view.Logs;
import world.World;

public class Human extends Animals implements CustomListener {
    int dx = 0;
    int dy = 0;
    int skillCooldown = 0;
    boolean moved = false;

    public Human(int posX, int posY, World world){
        super("Czlowiek", 5, 4, posX, posY, "#", 0, world);
    }

    public void moveSpeed(int dx, int dy){
        if(world.isHex()){
            if(dy != 0){
                if(posY%2 == 0){
                    this.dx = dx == 1 ? 1 : 0;
                }else{
                    this.dx = dx == 1 ? 0 : -1;
                }
            }else{
                this.dx = dx;
            }

            this.dy = dy;
        }else{
            if(dx != 0)this.dx = dx;
            if(dy != 0)this.dy = dy;
        }

    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public void skill(){
        if (skillCooldown == 0) {
            Logs.addLog("Czlowiek uzyl umiejetnosci Calopalenie niszczac:\n");
            skillCooldown = 5;
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (posX + j < world.getSizeX() && posX + j >= 0 &&
                            posY + i < world.getSizeY() && posY + i >= 0) {
                        if(world.isHex()){
                            if(posY%2 == 0){
                                if((i == -1 && j == -1) || (i == 1 && j == -1))continue;
                            }else{
                                if((i == -1 && j == 1) || (i == 1 && j == 1))continue;
                            }
                        }
                        Organism temp = world.getCell(posX + j, posY + i);
                        if (temp != null && temp != this) {
                            Logs.addLog("\t--" + temp.getName() + '(' + temp.getPosX() + ',' + temp.getPosY() + ')' + '\n');
                            world.removeOrganism(temp);
                        }
                    }
                }
            }
            world.updateWorld();
        }
        else {
            Logs.addLog("Zostalo " + skillCooldown + " lat do uzycia Calopalenia\n");
        }
    }

    @Override
    public void action() {
        if (dx != 0 || dy != 0) {
            int newX, newY;
            newY = posY + dy;
            newX = posX + dx;
            if (newY >= 0 &&
                newY < world.getSizeY() &&
                newX >= 0 &&
                newX < world.getSizeX()) {
                this.move(newX, newY);
            }
            dx = 0;
            dy = 0;
        }
        if (skillCooldown != 0) {
            skillCooldown -= 1;
            Logs.addLog("Zostalo " + skillCooldown + " lat do uzycia Calopalenia\n");
        }
        moved = false;
        this.update(moved);
    }

}
