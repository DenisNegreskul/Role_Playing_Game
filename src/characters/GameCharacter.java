package characters;

public abstract class GameCharacter implements Attacker {
    protected String name;
    protected int health;
    protected int gold;
    protected int agility;
    protected int experience;
    protected int strength;

    public GameCharacter(String name, int health, int gold, int agility, int experience, int strength) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.agility = agility;
        this.experience = experience;
        this.strength = strength;
    }

    @Override
    public int attack() {
        if (agility * 3 > Math.random() * 100) return strength;
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
