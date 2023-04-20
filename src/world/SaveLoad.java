package world;

import com.sun.org.apache.xpath.internal.operations.Or;
import organisms.Organism;
import view.Logs;

import java.io.*;

public class SaveLoad {
    private World world;
    private String path;

    public SaveLoad(World world, String path) {
        this.world = world;
        this.path = path;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void saveGame(){
        try(ObjectOutputStream write= new ObjectOutputStream (new FileOutputStream(path)))
        {
            write.writeObject(world);
            write.writeInt(world.getOrganismsSize());
            for(Organism o : world.getOrganisms()){
                write.writeObject(o);
            }
            Logs.addLog("ZAPISANO GRE\n");
        }
        catch(NotSerializableException nse)
        {
            System.out.println(nse);
        }
        catch(IOException eio)
        {
            System.out.println(eio);
        }
    }

    public String getPath() {
        return path;
    }

    public World loadGame(){
        try(ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path)))
        {
            World temp = new World((World) inFile.readObject());
            int number = inFile.readInt();
            for(int i = 0; i < number; i++){
                temp.insertOrganism(((Organism) inFile.readObject()));
            }
            Logs.addLog("WCZYTANO GRE\n");
            return temp;
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return null;
    }
}
