public class Worker extends Human implements WorkerInterface {

    public Worker() {
         
        setSymbol("W");
        setLifePoints(2);
        setGoldCost(1);
        setStoneCost(0);
        setWoodCost(0);

         
        setSpeed(3);
        setRange(1);
        int[] a = {3,3,3,3,3,3,3,3,3};
        setDamageArray(a);
        setHasDiamondRange(false);
    }

    @Override
    public void build(Building b) throws AgeOfEmpiresException {
        if (getPlayer().getGame().getWhoseTurn() != getPlayer()) throw new AgeOfEmpiresException();

        if (!(b instanceof Tower || b instanceof University)) throw new AgeOfEmpiresException();

        if (getPlayer().getGame().getMap().responsibleInCell(getX1(), getY1()) != this) throw new AgeOfEmpiresException();

        getPlayer().getGame().changeWhoseTurn(getPlayer());

         



        if (b instanceof Tower) {
            getPlayer().decreaseGold(5);
            getPlayer().decreaseStone(40);
            getPlayer().decreaseWood(10);



            b.setPlayer(this.getPlayer());
            getPlayer().add(b);

            getPlayer().getGame().getMap().create(b, getX1(), getY1());

        } else if (b instanceof University) {
            if (getPlayer().getUniversity() == null) {

                getPlayer().decreaseGold(50);
                getPlayer().decreaseStone(5);
                getPlayer().decreaseWood(30);

                b.setPlayer(this.getPlayer());
                getPlayer().setUniversity((University) b);

                getPlayer().getGame().getMap().create(b, getX1(), getY1());
            }
        } else {
            throw new AgeOfEmpiresException();
        }
    }
}
