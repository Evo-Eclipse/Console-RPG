public class Player {
    private String name;
    private Unit[] units;
    private boolean ai;

    public Player(String name, int numUnits, boolean ai) {
        this.name = name;
        this.units = new Unit[numUnits];
        this.ai = ai;
    }

    public String getname() {
        return this.name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public Unit[] getunits() {
        return this.units;
    }
    public void setunits() {
        if (ai) {
            this.units[0] = new Knight(Util.getRandomKnightName());
            this.units[1] = new Wizard(Util.getRandomWizardName());
            this.units[2] = new Terminator(Util.getRandomTerminator());

            for (int i = units.length - 1; i > 0; i--) {
                int j = (int)(Math.random() * (i + 1));

                Unit temp = units[i];
                units[i] = units[j];
                units[j] = temp;
            }
        } else {
            this.units = Util.uchoose(units.length);
        }
    }

    public boolean hasAliveUnits() {
        for (Unit unit : units) {
            if (unit.pulse()) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidUnitChoice(int index) {
        return index >= 0 && index < units.length && units[index].pulse();
    }

    public void playerAttack(Player opponent) {
        Util.displayu(this);
        int attackerIndex = Util.chooseAU(this);
        Unit attacker = this.units[attackerIndex];

        Util.displayu(opponent);
        int defenderIndex = Util.chooseTU(opponent);
        Unit defender = opponent.getunits()[defenderIndex];

        attacker.attack(defender);
    }


    public void aiAttack(Player opponent) {

        int attackerIndex = this.chooseAIAttacker();
        Unit attacker = this.units[attackerIndex];

        int defenderIndex = opponent.chooseAITarget();
        Unit defender = opponent.getunits()[defenderIndex];

        attacker.attack(defender);
    }

    private int chooseAIAttacker() {
        double choice = Math.random();
        if (choice < 0.6) {
            return indexOfLowestHealth();
        } else if (choice < 0.7) {
            return indexOfHighestAttack();
        } else {
            return chooseRandom();
        }
    }


    private int chooseAITarget() {
        double choice = Math.random();
        if (choice < 0.7) {
            return indexOfHighestHealth();
        } else if (choice < 0.9) {
            return chooseRandom();
        } else {
            return indexOfHighestAttack(); // Желание самоубиться?
        }
    }

    private int indexOfLowestHealth() {
        int minHealthIndex = -1;
        int minHealth = Integer.MAX_VALUE;

        for (int i = 0; i < units.length; i++) {
            if (units[i].pulse() && units[i].getHealth() < minHealth) {
                minHealth = units[i].getHealth();
                minHealthIndex = i;
            }
        }

        return minHealthIndex;
    }

    private int indexOfHighestAttack() {
        int maxAttackIndex = -1;
        int maxAttack = Integer.MIN_VALUE;

        for (int i = 0; i < units.length; i++) {
            if (units[i].pulse() && units[i].getPower() > maxAttack) {
                maxAttack = units[i].getPower();
                maxAttackIndex = i;
            }
        }

        return maxAttackIndex;
    }

    private int indexOfHighestHealth() {
        int maxHealthIndex = -1;
        int maxHealth = Integer.MIN_VALUE;

        for (int i = 0; i < units.length; i++) {
            if (units[i].pulse() && units[i].getHealth() > maxHealth) {
                maxHealth = units[i].getHealth();
                maxHealthIndex = i;
            }
        }

        return maxHealthIndex;
    }

    private int chooseRandom() {
        int choice;
        do {
            choice = (int)(Math.random() * this.units.length);
        } while (!isValidUnitChoice(choice));
        return choice;
    }
}
