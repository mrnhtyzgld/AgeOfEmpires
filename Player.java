import java.io.Serializable;
import java.util.ArrayList;
public class Player implements PlayerInterface, Serializable {
    private int woodCount;
    private int goldCount;
    private int stoneCount;
    private Game game;

    public void setWoodCount(int woodCount) {
        this.woodCount = woodCount;
    }

    public void setGoldCount(int goldCount) {
        this.goldCount = goldCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    private ArrayList<Tower> towers = new ArrayList<>(0);
    private ArrayList<Worker> workers = new ArrayList<>(0);
    private ArrayList<Soldier> soldiers = new ArrayList<>(0);
    private University university = null;  
    private MainBuilding mainBuilding = new MainBuilding();


 

    public Player() {
        woodCount = 100;
        goldCount = 101;  
        stoneCount = 100;
    }

    public Game getGame() { return game; }

    public void setGame(Game game) { this.game = game; }

    @Override
    public void pass() throws AgeOfEmpiresException {
        if (getGame().getWhoseTurn() != this) throw new AgeOfEmpiresException();
        getGame().changeWhoseTurn(this);

         

    }
 
    @Override
    public void purchase(Item item) throws AgeOfEmpiresException {
        if (item instanceof Building) throw new AgeOfEmpiresException();

        if (getGame().getWhoseTurn() != this) throw new AgeOfEmpiresException();
        if(workers.size() + soldiers.size() == 20) throw new AgeOfEmpiresException();
        getGame().changeWhoseTurn(this);

         



        decreaseWood(item.getWoodCost());
        decreaseGold(item.getGoldCost());
        decreaseStone(item.getStoneCost());

        item.setPlayer(this);

        getGame().getMap().create(item, mainBuilding.getX1(), mainBuilding.getY1());

        add(item);
    }
    public void increaseWood(int number)  {
        woodCount += number;
    }

    public void increaseGold(int number)  {
        goldCount += number;
    }
    public void increaseStone(int number) {
        stoneCount += number;
    }
    public void decreaseWood(int number) throws AgeOfEmpiresException {
        if (woodCount - number < 0) throw new AgeOfEmpiresException();
        woodCount -= number;
    }
    public void decreaseGold(int number) throws AgeOfEmpiresException {
        if (goldCount - number < 0) throw new AgeOfEmpiresException();
        goldCount -= number;
    }
    public void decreaseStone(int number) throws AgeOfEmpiresException {
        if (stoneCount - number < 0) throw new AgeOfEmpiresException();
        stoneCount -= number;
    }
    @Override
    public int getWood() {return woodCount;}
    @Override
    public int getGold() {return goldCount;}
    @Override
    public int getStone() {return stoneCount;}



    @Override
    public int getTowerCount() {return towers.size();}
    @Override
    public Tower getTower(int index) {return towers.get(index);}

    @Override
    public int getWorkerCount() {return workers.size();}
    @Override
    public Worker getWorker(int index) {return workers.get(index);}
    @Override
    public int getSoldierCount() {return soldiers.size();}
    @Override
    public Soldier getSoldier(int index) {return soldiers.get(index);}
    @Override
    public University getUniversity() {return university;}
    public void setUniversity(University university) throws AgeOfEmpiresException {
        if (this.university != null) throw new AgeOfEmpiresException();
        this.university = university;
    }
    public MainBuilding getMainBuilding() {return mainBuilding;}
    public void setMainBuilding(MainBuilding mainBuilding) {this.mainBuilding = mainBuilding;}
    public ArrayList<Tower> getTowers() {return towers;}
    public ArrayList<Worker> getWorkers() {return workers;}
    public ArrayList<Soldier> getSoldiers() {return soldiers;}

    public void delete(Item item) {
        if (item instanceof Worker) {
            workers.remove(((Worker)item));
        } if (item instanceof Soldier) {
            soldiers.remove(((Soldier) item));
        } if (item instanceof Tower) {
            towers.remove(((Tower) item));
        } if (item instanceof MainBuilding) {
            mainBuilding = null;
        } if (item instanceof University)  {
            university = null;
        }
    }
    public void add(Item e) {
        if (e instanceof Worker) {
            workers.add((Worker) e);
        } else if (e instanceof Tower) {
            towers.add((Tower) e);
        } else if (e instanceof Soldier) {
            soldiers.add((Soldier) e);
        }
    }
}
