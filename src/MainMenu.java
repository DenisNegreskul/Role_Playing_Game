import characters.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MainMenu {
    private static BufferedReader bufferedReader;
    private static Hero hero;
    private static Battle battle;
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Enter your hero's name:");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            bufferedReader = br;
            hero = new Hero(bufferedReader.readLine(), 100, 0,
                    10 + (int) (Math.random() * 10), 0, 20 + (int) (Math.random() * 10));
            battle = new Battle(hero);
            new Thread(battle).start();
            printNavigation();
            command(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        switch (string) {
            case "1" -> {
                System.out.println("Trader is not here yet");
                command(bufferedReader.readLine());
            }
            case "2" -> {
                commitFight();
            }
            case "3" -> System.exit(1);
            case "yes" -> command("2");
            case "no" -> {
                printNavigation();
                command(bufferedReader.readLine());
            }
        }

        command(bufferedReader.readLine());
    }

    private static void printNavigation() {
        System.out.println("Where do you wish to go?");
        System.out.println("1. To the trader");
        System.out.println("2. To the dark forest");
        System.out.println("3. Exit");
    }

    private static void commitFight() {
        synchronized (battle) {
            battle.setEnemy(createMonster());
            battle.notify();
            try {
                battle.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (hero.getHealth() > 0) {
                System.out.printf("%s won! Now you have %d experience and %d gold and also retained %d health points.\n",
                        hero.getName(), hero.getExperience(), hero.getGold(), hero.getHealth());
                System.out.println("Do you wish to continue journey or to go back to the city? (yes/no)");
                try {
                    command(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Game Over");
                System.exit(1);
            }
        }
    }

    private static GameCharacter createMonster() {
        if (random.nextBoolean()) return new Goblin(
                "Goblin",
                50,
                10,
                10,
                100,
                20
        );
        else return new Skeleton(
                "Skeleton",
                25,
                20,
                20,
                100,
                10
        );
    }
}
