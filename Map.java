import java.io.Serializable;
import java.util.ArrayList;


public class Map implements MapInterface, Serializable {

    private String[][] map = new String[50][100];  
    private ArrayList<Item>[][] mapWhole = new ArrayList[50][100];
     

    public final String EMPTY_CELL = "_";
    private Game game;


    public Map() throws AgeOfEmpiresException {
        for(int a = 0; a < mapWhole.length; a++)
            for(int b = 0; b < mapWhole[0].length; b++)
                mapWhole[a][b] = new ArrayList<Item>();
        updateMatrice();

    }
    public void setGame(Game game) { this.game = game; }

    @Override
    public String status() throws AgeOfEmpiresException {
        updateMatrice();

        String output = "";
        for(int i = 0; i < map.length;i++)
        {
            for(int j = 0; j < map[0].length; j++){
                output += map[i][j];}
            output += "\n";
        }
        return output;
    }

    public void updateMatrice() throws AgeOfEmpiresException {
         

        for (int a = 0; a < mapWhole.length; a++) {
            for (int b = 0; b < mapWhole[0].length; b++) {

                Item responsible = responsibleInCell(b, a);

                 
                if (responsible == null) {
                    map[a][b] = EMPTY_CELL;

                } else {
                    map[a][b] = responsible.getSymbol();
                }
            }
        }
    }
 
    public Item responsibleInCell(int x, int y) {

         
         
         
         
         
         
        ArrayList<Item> cell = mapWhole[y][x];
        if (cell.size() == 0) return null;
        if (cell.size() == 1) return cell.get(0);
        if (cell.size() > 1) {
            for (int i = 0; i < cell.size(); i++) {  
                if (cell.get(i) instanceof MainBuilding) return cell.get(i);
            }

            for (int i = 0; i < cell.size(); i++) {  
                if (cell.get(i) instanceof Building) return cell.get(i);
            }
            throw new RuntimeException();
        }
        return null;
    }
    public ArrayList<Item> getElementOfMapWhole(int x, int y) { return mapWhole[y][x]; }

    public void create(Item item, int x, int y) throws AgeOfEmpiresException {
        if (mapWhole[y][x].contains(item)) throw new AgeOfEmpiresException();
        mapWhole[y][x].add(item);


        updateMatrice();
    }
    public void delete(Item item) throws AgeOfEmpiresException {
        for (int a = 0; a < mapWhole.length; a++)
            for (int b = 0; b < mapWhole[a].length; b++)
                for (int c = 0; c < mapWhole[a][b].size(); c++)
                    if (mapWhole[a][b].get(c) == item)
                        mapWhole[a][b].remove(c);

        updateMatrice();
    }

    public void move(Item item, int toX, int toY) throws AgeOfEmpiresException {
        if (item instanceof Building) throw new AgeOfEmpiresException();

        delete(item);
        create(item, toX, toY);
    }

    public int getX(Item item) {  
        for (int a = 0; a < mapWhole.length; a++)
            for (int b = 0; b < mapWhole[a].length; b++)
                for (int c = 0; c < mapWhole[a][b].size(); c++)
                    if (mapWhole[a][b].get(c) == item)
                        return b;

        return -1;
    }
    public int getY(Item item) {  
        for (int a = 0; a < mapWhole.length; a++)
            for (int b = 0; b < mapWhole[a].length; b++)
                for (int c = 0; c < mapWhole[a][b].size(); c++)
                    if (mapWhole[a][b].get(c) == item)
                        return a;

        return -1;
    }
}



