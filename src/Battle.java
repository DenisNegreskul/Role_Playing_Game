import characters.GameCharacter;
import characters.Hero;

public class Battle implements Runnable {
    private final Hero hero;
    private GameCharacter enemy;

    public Battle(Hero hero) {
        this.hero = hero;
    }

    public GameCharacter getEnemy() {
        return enemy;
    }

    public void setEnemy(GameCharacter enemy) {
        this.enemy = enemy;
    }

    public void fight() {
        int turn = 1;
        boolean isFightEnded = false;
        while (!isFightEnded) {
            System.out.println("----Turn: " + turn + "----");
            if (turn++ % 2 != 0) {
                isFightEnded = hit(enemy, hero);
            } else {
                isFightEnded = hit(hero, enemy);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hit(GameCharacter attacker, GameCharacter defender) {
        int damage = attacker.attack();
        defender.setHealth(defender.getHealth() - damage);
        if (damage <= 0) {
            System.out.printf("%s missed!\n", attacker.getName());
        } else {
            System.out.printf("%s hit %s for %d damage!\n", attacker.getName(), defender.getName(), damage);
        }

        if (defender.getHealth() <= 0) {
            if (defender instanceof Hero) {
                System.out.println("YOU DIED");
            } else {
                attacker.setExperience(attacker.getExperience() + defender.getExperience());
                attacker.setGold(attacker.getGold() + defender.getGold());
                System.out.printf("%s has died! You gain %d experience and %d gold!\n", defender.getName(),
                        defender.getExperience(), defender.getGold());
            }
            return true;
        }
        return false;
    }

    @Override
    synchronized public void run() {
        try {
            wait();
            while (true) {
                fight();
                notify();
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
