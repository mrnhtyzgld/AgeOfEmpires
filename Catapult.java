public class Catapult extends Soldier{
    public Catapult() {
         
        setSymbol("C");
        setLifePoints(10);
        setGoldCost(20);
        setStoneCost(5);
        setWoodCost(30);


         
        setSpeed(1);
        setRange(10);
        setNegativeRange(5);
        int[] a = {MAX_HEALTH,MAX_HEALTH,MAX_HEALTH,MAX_HEALTH,MAX_HEALTH,MAX_HEALTH,30,30,30};
        setDamageArray(a);


    }
}
