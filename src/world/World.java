package world;

import organisms.Organism;
import view.World_panel;

import java.io.Serializable;
import java.util.LinkedList;

public class World implements Serializable {
    private World_panel worldPanel;
    private int sizeX;
    private int sizeY;
    private int turn;
    private LinkedList<Organism> organisms;
    private Organism[] map;
    private boolean hex;

    public boolean isHex() {
        return hex;
    }

    public World(int sizeX, int sizeY, boolean hex) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.map = new Organism[sizeX * sizeY];
        this.hex = hex;
        organisms = new LinkedList<Organism>();
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
                setCell(i,j,null);
            }
        }
        this.worldPanel = new World_panel(this, hex);
    }

    public World(World old){
        this(old.sizeX,old.sizeY,old.hex);
    }

    public void setOrganisms(LinkedList<Organism> organisms) {
        this.organisms = organisms;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getOrganismsSize(){
        return organisms.size();
    }

    public Organism getOrganism(int i){
        return organisms.get(i);
    }

    public Organism getCell(int x, int y){
        return map[y * sizeX + x];
    }

    public World_panel getWorldPanel() {
        return worldPanel;
    }

    public void setCell(int x, int y, Organism organism){
        map[y * sizeX + x] = organism;
    }

    public void takeTurn(){
        for(int i = 0; i < organisms.size(); i++){
            worldPanel.updatePoint(organisms.get(i).getPosX(),organisms.get(i).getPosY(),"");
            organisms.get(i).yearOlder(1);
            organisms.get(i).action();
        }
        worldPanel.getPanel().repaint();
        turn++;
    }

    public void insertOrganism(Organism organism){
        if(getCell(organism.getPosX(),organism.getPosY()) == null){
            int i = 0;
            for(;i < organisms.size(); i++){
                if(organism.getInitiation() > organisms.get(i).getInitiation())break;
                if(organism.getInitiation() == organisms.get(i).getInitiation() && organism.getAge() > organisms.get(i).getAge())break;
            }
            organisms.add(i,organism);
            setCell(organism.getPosX(), organism.getPosY(), organism);
            organism.setWorld(this);
        }
    }

    public LinkedList<Organism> getOrganisms() {
        return organisms;
    }

    public void removeOrganism(Organism organism){
        worldPanel.updatePoint(organism.getPosX(), organism.getPosY(), "");
        organisms.remove(organism);
        setCell(organism.getPosX(), organism.getPosY(), null);
    }

    public void updateWorld(){
        for(Organism temp : organisms){
            worldPanel.updatePoint(temp.getPosX(), temp.getPosY(), temp.getSymbol());
        }
        worldPanel.getPanel().repaint();
    }
}
