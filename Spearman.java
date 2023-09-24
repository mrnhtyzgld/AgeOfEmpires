public class Spearman extends Soldier{

    public Spearman() {
         
        setSymbol("S");
        setLifePoints(5);
        setGoldCost(3);
        setStoneCost(0);
        setWoodCost(2);

         
        setSpeed(2);
        setHasAttackPriority(true);
        int[] a = {2,2,2,2,10,2,2,2,2};
        setDamageArray(a);
        setRange(1);
        setHasDiamondRange(false);



    }
}
