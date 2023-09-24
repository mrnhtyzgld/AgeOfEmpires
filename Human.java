
public abstract class Human extends Item implements HumanInterface {

    private int speed, range;
    private int negativeRange = 0;
    private boolean hasAttackPriority = false;
    private boolean hasDiamondRange = true;
    private int[] damageArray = new int[9];


    @Override
    public void move(int x, int y) throws AgeOfEmpiresException {
        move1(x-1,y-1);
    }

    public void move1(int x, int y) throws AgeOfEmpiresException {
        if (getPlayer().getGame().getWhoseTurn() != getPlayer()) throw new AgeOfEmpiresException();  
        if (!canMove(x, y)) throw new AgeOfEmpiresException();

        getPlayer().getGame().changeWhoseTurn(getPlayer());

         



        Item cellToGo = getPlayer().getGame().getMap().responsibleInCell(x, y);

        if (cellToGo == null) {  
            getPlayer().getGame().getMap().move(this, x, y);
            return;
        }
        if (cellToGo instanceof MainBuilding) {  
            getPlayer().getGame().getMap().move(this, getPlayer().getMainBuilding().getX1(), getPlayer().getMainBuilding().getY1());
        }
    }


    public boolean canMove(int x, int y) {
        if ((Math.abs(getX1() - x) + Math.abs(getY1() - y)) > speed) return false;
        if (getX1() == x && getY1() == y) return false;


        Item cellToGo = getPlayer().getGame().getMap().responsibleInCell(x, y);

        if (! (cellToGo instanceof MainBuilding || cellToGo == null)) return false;
        if (cellToGo != null && cellToGo.getPlayer() != getPlayer()) return false;


        return true;
    }




    public int findDmg(Human attacker, Item defender) {  
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
        if (defender instanceof Worker) {
            damage = attacker.getDamageArray()[5];
            return damage;
        }
        if (defender instanceof University)
            damage = attacker.getDamageArray()[6];
        if (defender instanceof Tower)
            damage = attacker.getDamageArray()[7];
        if (defender instanceof MainBuilding)
            damage = attacker.getDamageArray()[8];
        if (attacker instanceof Archer) {
            if (Math.abs(attacker.getY1() - defender.getY1()) < 2 & Math.abs(attacker.getX1() - defender.getX1()) < 2)
                damage = 2;
        }

        if (getPlayer().getUniversity() != null) {
            Soldier friend = (Soldier) attacker;
            if (friend instanceof Cavalry)
                damage += getPlayer().getUniversity().getCavalaryLevel();
            else if (friend instanceof Catapult)
                damage += getPlayer().getUniversity().getCatapultLevel();
            else if (friend instanceof Soldier)
                damage += getPlayer().getUniversity().getInfantaryLevel();
        }
        return damage;
    
    }

    public boolean canAttack(int x, int y) throws AgeOfEmpiresException {

         
        if (hasDiamondRange) {
            if ((Math.abs(getX1() - x) + Math.abs(getY1() - y)) > range) return false;
            if (getNegativeRange() >= (Math.abs(getX1() - x) + Math.abs(getY1() - y))) return false;
        }
        if (!hasDiamondRange) {
            if (Math.abs(getX1() - x) > range || Math.abs(getY1() - y) > range) return false;
            if (getNegativeRange() >= Math.abs(getX1() - x) && getNegativeRange() >= Math.abs(getY1() - y)) return false;
        }
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

        if (enemy instanceof Human && !hasAttackPriority && ((Human)enemy).hasAttackPriority) {
            if (((Human) enemy).canAttack(getX1(), getY1())) {
                decreaseLifePoints(findDmg((Human)enemy, this));
                if (getLifePoints() == 0) return;  
            }
            enemy.decreaseLifePoints(findDmg(this, enemy));  

        } else {

            enemy.decreaseLifePoints(findDmg(this, enemy));
            if (enemy.getLifePoints() == 0) return;
            if (!(enemy instanceof Tower) & !(enemy instanceof Human)) return;
            if (enemy instanceof Tower) {
                if ( ! ((Tower) enemy).canAttack(getX1(), getY1())) return;
                decreaseLifePoints(((Tower) enemy).findDmg((Tower) enemy, this));
            }
            if (enemy instanceof Human) {
                if (! ((Human) enemy).canAttack(getX1(), getY1())) return;
                decreaseLifePoints(findDmg((Human) enemy, this));
            }
        }
    }

    public int[] getDamageArray() {
        return damageArray;
    }

    public void setDamageArray(int[] damageArray) {
        this.damageArray = damageArray;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean getHasDiamondRange() {
        return hasDiamondRange;
    }

    public void setHasDiamondRange(boolean hasDiamondRange) {
        this.hasDiamondRange = hasDiamondRange;
    }

    public int getNegativeRange() {
        return negativeRange;
    }
    public void setNegativeRange(int negativeRange) {
        this.negativeRange = negativeRange;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean getHasAttackPriority() {
        return hasAttackPriority;
    }
    public void setHasAttackPriority(boolean hasAttackPriority) {
        this.hasAttackPriority = hasAttackPriority;
    }
}
