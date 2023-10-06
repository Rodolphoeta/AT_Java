package medieval;

import java.util.Random;

public class Monster {
    public String type;
    public int health;
    public int attack;
    public int defense;
    public int agility;
    public String damage;
    public String name;

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public Monster(String type, int health, int attack, int defense, int agility, String damage) {
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.agility = agility;
        this.damage = damage;
    }

    public int calculateInitiative() {
        Random rand = new Random();
        return rand.nextInt(10) + agility;
    }

    public int calculateAttack() {
        Random rand = new Random();
        return rand.nextInt(10) + agility + attack;
    }

    public int calculateDefense() {
        Random rand = new Random();
        return rand.nextInt(10) + agility + defense;
    }

    public int calculateDamage() {
        String[] dice = damage.split("d");
        int numDice = Integer.parseInt(dice[0]);
        int sides = Integer.parseInt(dice[1]);

        int totalDamage = 0;
        Random rand = new Random();

        for (int i = 0; i < numDice; i++) {
            totalDamage += rand.nextInt(sides) + 1;
        }

        return totalDamage + attack;
    }
}
