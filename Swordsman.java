public class Swordsman extends Soldier{

    public Swordsman() {
         
        setSymbol("K");
        setLifePoints(5);
        setGoldCost(2);
        setStoneCost(0);
        setWoodCost(2);

         
        setSpeed(2);
        setRange(1);
        int[] a = {3,3,3,3,3,3,3,3,3};
        setDamageArray(a);
        setHasDiamondRange(false);
    }
}
