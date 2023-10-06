import medieval.Hero;
import medieval.Monster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static BattleRecord[] battleRecords;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o seu nickname: ");
        String playerName = scanner.nextLine();

        String heroClass = chooseHeroClass(scanner);

        Hero hero = null;
        switch (heroClass) {
            case "Guerreiro":
                hero = new Hero("Guerreiro", 12, 4, 3, 3, "2d4");
                break;
            case "Bárbaro":
                hero = new Hero("Bárbaro", 13, 6, 1, 3, "2d6");
                break;
            case "Paladino":
                hero = new Hero("Paladino", 15, 2, 5, 1, "2d4");
                break;
        }

        String[] monsterTypes = { "Morto-vivo", "Orc", "Kobold" };
        String monsterType = monsterTypes[new Random().nextInt(monsterTypes.length)];

        Monster monster = null;
        switch (monsterType) {
            case "Morto-vivo":
                monster = new Monster("Morto-vivo", 25, 4, 0, 1, "2d4");
                break;
            case "Orc":
                monster = new Monster("Orc", 20, 6, 2, 2, "1d8");
                break;
            case "Kobold":
                monster = new Monster("Kobold", 20, 4, 2, 4, "3d2");
                break;
        }

        int rounds = 0;
        while (true) {
            rounds++;

            int heroInitiative = hero.calculateInitiative();
            int monsterInitiative = monster.calculateInitiative();

            System.out.println("Valor dos dados do Herói: " + heroInitiative);
            System.out.println("Valor dos dados do Monstro: " + monsterInitiative);

            if (heroInitiative > monsterInitiative) {
                battleRound(hero, monster);
            } else if (monsterInitiative > heroInitiative) {
                battleRound(hero, monster);
            } else {
                continue;
            }

            if (hero.health <= 0 || monster.health <= 0) {
                saveBattleLog(playerName, heroClass, hero.health > 0, monsterType, rounds);
                break;
            }

        }
        boolean playerWon = hero.health > 0;
        
        System.out.println(playerWon ? "Você venceu!" : "Você perdeu...");
    }

    

    public static String chooseHeroClass(Scanner scanner) {
        System.out.println("Escolha a classe do herói:");
        System.out.println("1. Guerreiro");
        System.out.println("2. Bárbaro");
        System.out.println("3. Paladino");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                return "Guerreiro";
            case 2:
                return "Bárbaro";
            case 3:
                return "Paladino";
            default:
                return "Classe Inválida";
        }
    }

    public static void battleRound(Hero attacker, Monster defender) {
        
        int attackerAtk = attacker.calculateAttack();
        int attackerDfn = attacker.calculateDefense();
        int defenderAtk = defender.calculateAttack();
        int defenderDfn = defender.calculateDefense();


        System.out.println(attacker.name + " ataca com " + attackerAtk);
        System.out.println(defender.type + " defende com " + defenderDfn);

        if (attackerAtk >= defenderDfn) {
            int damage = attacker.calculateDamage();
            defender.health -= damage;

            System.out.println(defender.type + " sofre " + damage + " de dano!\n");
          
        } else {
            System.out.println(attacker.name + " Errou");
        } 
        
        System.out.println(defender.type + " ataca com " + defenderAtk);
        System.out.println(attacker.name + " defende com " + attackerDfn);

        if (defenderAtk >= attackerDfn ) {
            int damage = defender.calculateDamage();
            attacker.health -= damage;
                
            System.out.println(attacker.name + " sofre " + damage + " de dano!\n");    
        } else {
            System.out.println(defender.type + " Errou");
        }
    }

    // NOVO METODO

    public static void searchRecords(String playerName) {
        int totalPlayed = 0;
        int totalWon = 0;
        int totalLost = 0;
    
        int guerreiroCount = 0;
        int barbaroCount = 0;
        int paladinoCount = 0;
    
        int orcCount = 0;
        int mortoVivoCount = 0;
        int koboldCount = 0;
    
        for (BattleRecord record : battleRecords) {
            if (record.getPlayerName().equals(playerName)) {
                totalPlayed++;
    
                if (record.getPlayerWon()) {
                    totalWon++;
                } else {
                    totalLost++;
                }
    
                switch (record.getHeroClass()) {
                    case "Guerreiro":
                        guerreiroCount++;
                        break;
                    case "Bárbaro":
                        barbaroCount++;
                        break;
                    case "Paladino":
                        paladinoCount++;
                        break;
                }
    
                switch (record.getMonsterType()) {
                    case "Orc":
                        orcCount++;
                        break;
                    case "Morto-vivo":
                        mortoVivoCount++;
                        break;
                    case "Kobold":
                        koboldCount++;
                        break;
                }
            }
        }
    
        System.out.println(playerName + " jogou " + totalPlayed + " vezes.");
        System.out.println("Ganhou " + totalWon + " batalhas.");
        System.out.println("Perdeu " + totalLost + " batalhas.");
        System.out.println("Jogou com Guerreiro " + guerreiroCount + " vezes.");
        System.out.println("Jogou com Bárbaro " + barbaroCount + " vezes.");
        System.out.println("Jogou com Paladino " + paladinoCount + " vezes.");
        System.out.println("Enfrentou Orc " + orcCount + " vezes.");
        System.out.println("Enfrentou Morto-vivo " + mortoVivoCount + " vezes.");
        System.out.println("Enfrentou Kobold " + koboldCount + " vezes.");
    }
    

    // FIM DO METODO NOVO

    
    public static void saveBattleLog(String playerName, String heroClass, boolean playerWon, String monsterType, int rounds) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String formattedDate = formatter.format(date);

            File tempDir = new File("temp");
            if (!tempDir.exists()) {
                tempDir.mkdir();
            }

            FileWriter writer = new FileWriter("temp/" + playerName + ".csv", true);

            writer.append(formattedDate);
            writer.append(';');
            writer.append(heroClass);
            writer.append(';');
            writer.append(playerWon ? "GANHOU" : "PERDEU");
            writer.append(';');
            writer.append(monsterType);
            writer.append(';');
            writer.append(Integer.toString(rounds));
            writer.append('\n');

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}