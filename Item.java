import java.io.Serializable;

public abstract class Item implements ItemInterface, Serializable {
    private int lifePoints;
    private String symbol;
    private Player player;
    public final int MAX_HEALTH = 100;

    private int woodCost;
    private int goldCost;
    private int stoneCost;


    public void kill() throws AgeOfEmpiresException {

        if (this instanceof University) {
            getPlayer().getUniversity().resetProgress();
        } if (this instanceof MainBuilding) {

            for (int a = getPlayer().getWorkerCount()-1; a >= 0; a--) {
                getPlayer().getWorker(a).kill();
            }
            for (int a = getPlayer().getSoldierCount()-1; a >= 0; a--) {
                getPlayer().getSoldier(a).kill();
            }
            for (int a = getPlayer().getTowerCount()-1; a >= 0; a--) {
                getPlayer().getTower(a).kill();
            }
            if (getPlayer().getUniversity() != null) {
                getPlayer().getUniversity().kill();

            }

        }
        getPlayer().getGame().getMap().delete(this);
        getPlayer().delete(this);
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void increaseLifePoints(int value) throws AgeOfEmpiresException {
        if (value < 0) throw new AgeOfEmpiresException();
        lifePoints += value;
    }

    public void decreaseLifePoints (int value) throws AgeOfEmpiresException {
        if (value < 0) throw new AgeOfEmpiresException();

        if (lifePoints - value > 0) {
            lifePoints -= value;
            return;
        }

        kill();

        lifePoints = 0;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public void setWoodCost(int woodCost) {
        this.woodCost = woodCost;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public void setStoneCost(int stoneCost) {
        this.stoneCost = stoneCost;
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) throws AgeOfEmpiresException {
        this.player = player;
        if (player.getUniversity() != null) {
            if (this instanceof Cavalry) {
                increaseLifePoints(player.getUniversity().getCavalaryLevel());
            } else if (this instanceof Catapult) {
                increaseLifePoints(player.getUniversity().getCatapultLevel());
            } else if (this instanceof Soldier) {
                increaseLifePoints(player.getUniversity().getInfantaryLevel());
            }
        }
    }

    @Override
    public int getX() {
        return getX1()+1;
    }

    @Override
    public int getY() {
        return getY1()+1;
    }

    public int getX1() {
        return getPlayer().getGame().getMap().getX(this);
    }

    public int getY1() {
        return getPlayer().getGame().getMap().getY(this);
    }

    @Override
    public int getLifePoints() {
        return lifePoints;
    }
}
