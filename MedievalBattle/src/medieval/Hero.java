package medieval;

import java.util.Random;

public class Hero {
    public String name;
    public int health;
    public int attack;
    public int defense;
    public int agility;
    public String damage;
    public String type;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Hero(String name, int health, int attack, int defense, int agility, String damage) {
        this.name = name;
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