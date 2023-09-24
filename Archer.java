public class Archer extends Soldier{

    public Archer() {
         
        setSymbol("O");
        setLifePoints(5);
        setGoldCost(2);
        setStoneCost(0);
        setWoodCost(5);


         
        setSpeed(2);
         
        setRange(5);
        int[] a = {2,2,2,2,2,2,1,1,1};
        setDamageArray(a);

    }
}
