public class Unit {

    String name = "X";
    private int health = 100;
    private int defence = 50;
    private int power = 50;
    private float cc = 0.1f; // Critical Chance
    private float pc = 0.1f; // Parry Chance
    private boolean alive = true;

    public Unit(String name, int health, int defence, int power, float cc, float pc) {
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.power = power;
        this.cc = cc;
        this.pc = pc;
    }

    protected String getname() {
        return this.name;
    }

    protected int getHealth() {
        return this.health;
    }

    protected int getPower() {
        return this.power;
    }

    protected void attack(Unit unit) {
        if (Math.random() < this.pc) {
            Util.parryEvent(this.name, unit.getname());
            return;
        }
        
        int damage = this.power;
        if (Math.random() < this.cc) {
            unit.getDamage((int)(damage * 1.5));
            Util.critEvent(this.name, unit.getname(), (int)(damage * 1.5));
        } else {
            unit.getDamage(this.power);
            Util.attackEvent(this.name, unit.getname(), damage);
        }
    }

    protected void getDamage(int damage) {
        int effectiveDamage = damage - (this.defence / 2);
        if (effectiveDamage < 0) {
            effectiveDamage = 0;
        }
        this.health -= effectiveDamage;
        if (this.health <= 0) {
            this.alive = false;
        }
    }

    protected boolean pulse() {
        return this.alive;
    }
}
