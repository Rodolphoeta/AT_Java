import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportProgram {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("temp/nickname.csv"));
            List<BattleLog> battleLogs = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(parts[0]);
                String hero = parts[1];
                String outcome = parts[2];
                String monster = parts[3];
                int rounds = Integer.parseInt(parts[4]);

                BattleLog battleLog = new BattleLog(date, hero, outcome, monster, rounds);
                battleLogs.add(battleLog);
            }

            
            String mostPlayedHero = findMostPlayedHero(battleLogs);

            String mostFacedMonster = findMostFacedMonster(battleLogs);

            int totalPoints = calculateTotalPoints(battleLogs);

            System.out.println("Herói mais jogado: " + mostPlayedHero);
            System.out.println("Monstro mais enfrentado: " + mostFacedMonster);
            System.out.println("Quantidade total de Pontos: " + totalPoints);

            reader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static String findMostPlayedHero(List<BattleLog> logs) {
        
        return "Exemplo";
    }

    private static String findMostFacedMonster(List<BattleLog> logs) {
         return "Exemplo";
    }

    private static int calculateTotalPoints(List<BattleLog> logs) {
        int totalPoints = 0;
        for (BattleLog log : logs) {
            totalPoints += 100 - log.getRounds();
        }
        return totalPoints;
    }
}
