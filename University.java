public class University extends Building implements UniversityInterface{

    private int infantaryLevel = 0;
    private int catapultLevel = 0;
    private int cavalaryLevel = 0;


    public University() {
         
        setSymbol("U");
        setLifePoints(30);
        setGoldCost(50);
        setStoneCost(5);
        setWoodCost(30);

         

    }

    @Override
    public void trainInfantry() throws AgeOfEmpiresException {
        if (getPlayer().getGame().getWhoseTurn() != getPlayer()) throw new AgeOfEmpiresException();

        getPlayer().decreaseGold(50);

        getPlayer().getGame().changeWhoseTurn(getPlayer());

         

        infantaryLevel+=1;
        for (Item element: getPlayer().getSoldiers()) {
            if (!(element instanceof Catapult) && !(element instanceof Cavalry)) {
                element.increaseLifePoints(1);
            }
        }
    }

    @Override
    public void trainCavalry() throws AgeOfEmpiresException {
        if (getPlayer().getGame().getWhoseTurn() != getPlayer()) throw new AgeOfEmpiresException();

        getPlayer().decreaseGold(50);

        getPlayer().getGame().changeWhoseTurn(getPlayer());

         

        cavalaryLevel+=1;
        for (Item element: getPlayer().getSoldiers()) {
            if (element instanceof Cavalry) {
                element.increaseLifePoints(1);
            }
        }
    }

    @Override
    public void trainCatapult() throws AgeOfEmpiresException {
        if (getPlayer().getGame().getWhoseTurn() != getPlayer()) throw new AgeOfEmpiresException();

        getPlayer().decreaseGold(50);

        getPlayer().getGame().changeWhoseTurn(getPlayer());

         

        catapultLevel+= 1;
        for (Item element: getPlayer().getSoldiers()) {
            if (element instanceof Catapult) {
                element.increaseLifePoints(1);
            }
        }
    }

    public void resetProgress() throws AgeOfEmpiresException {

        for (Soldier element: getPlayer().getSoldiers()) {
            if (element instanceof Cavalry) {
                element.decreaseLifePoints(cavalaryLevel);
            } else if (element instanceof Catapult) {
                element.decreaseLifePoints(catapultLevel);
            } else {
                element.decreaseLifePoints(infantaryLevel);
            }
        }

        this.catapultLevel = 0;
        this.cavalaryLevel = 0;
        this.infantaryLevel = 0;
    }

    public int getInfantaryLevel() {
        return infantaryLevel;
    }

    public int getCatapultLevel() {
        return catapultLevel;
    }

    public int getCavalaryLevel() {
        return cavalaryLevel;
    }
}
