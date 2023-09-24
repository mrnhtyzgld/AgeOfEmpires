public class Cavalry extends Soldier{

    public Cavalry() {
         
        setSymbol("A");
        setLifePoints(20);
        setGoldCost(10);
        setStoneCost(0);
        setWoodCost(3);


         
        setSpeed(9);
        setRange(1);
        int[] a = {10,10,10,10,5,10,5,5,5};  
        setDamageArray(a);
        setHasDiamondRange(false);
    }
}
