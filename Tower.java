public class Tower extends Building implements TowerInterface{

    private int range = 7;
    private int[] damageArray = new int[9];

     

    public Tower() {
         
        setSymbol("T");
        setLifePoints(50);
        setGoldCost(5);
        setStoneCost(40);
        setWoodCost(10);

         
        int[] a = {2,2,2,2,2,2,2,2,2};
        setDamageArray(a);
        setRange(7);


    }

    public int findDmg(Tower attacker, Item defender) {

        int damage = 0;

        if (defender instanceof Spearman)
            damage = attacker.getDamageArray()[0];
        if (defender instanceof Catapult)
            damage = attacker.getDamageArray()[1];
        if (defender instanceof Archer)
            damage = attacker.getDamageArray()[2];
        if (defender instanceof Swordsman)
            damage = attacker.getDamageArray()[3];
        if (defender instanceof Cavalry)
            damage = attacker.getDamageArray()[4];
        if (defender instanceof Worker)
            damage = attacker.getDamageArray()[5];
        if (defender instanceof University)
            damage = attacker.getDamageArray()[6];
        if (defender instanceof Tower)
            damage = attacker.getDamageArray()[7];
        if (defender instanceof MainBuilding)
            damage = attacker.getDamageArray()[8];

        return damage;
    }

    public boolean canAttack(int x, int y) throws AgeOfEmpiresException {

         
        if ((Math.abs(getX1() - x) + Math.abs(getY1() - y)) > range) return false;


        Item enemy = getPlayer().getGame().getMap().responsibleInCell(x, y);
         
        if (enemy == null) return false;
         
        if (enemy.getPlayer() == getPlayer()) return false;


        if (enemy.getLifePoints() == 0 || this.getLifePoints() == 0) return false;  

        return true;
    }

    @Override
    public void attack(int x, int y) throws AgeOfEmpiresException {
        attack1(x-1,y-1);
    }

    public void attack1(int x, int y) throws AgeOfEmpiresException {
        if (getPlayer().getGame().getWhoseTurn() != getPlayer()) throw new AgeOfEmpiresException();

        if (!canAttack(x, y)) throw new AgeOfEmpiresException();

        getPlayer().getGame().changeWhoseTurn(getPlayer());

         



        Item enemy = getPlayer().getGame().getMap().responsibleInCell(x, y);

        if (enemy instanceof Human && ((Human) enemy).getHasAttackPriority()) {
            if (((Human) enemy).canAttack(getX1(), getY1())) {
                decreaseLifePoints(((Human) enemy).findDmg((Human) enemy, this));
                if (getLifePoints() == 0) return;
            }
            enemy.decreaseLifePoints(findDmg(this, enemy));
        } else {
            enemy.decreaseLifePoints(findDmg(this, enemy));
            if (enemy.getLifePoints() == 0) return;
            if (enemy instanceof Human) {
                if (((Human)enemy).canAttack(getX1(), getY1())) {
                    decreaseLifePoints(((Human)enemy).findDmg((Human) enemy, this));
                }
            } else if (enemy instanceof Tower) {
                if (((Tower) enemy).canAttack(getX1(), getY1())) {
                    decreaseLifePoints(findDmg((Tower) enemy, this));
                }
            }
        }
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int[] getDamageArray() {
        return damageArray;
    }

    public void setDamageArray(int[] damageArray) {
        this.damageArray = damageArray;
    }
}
