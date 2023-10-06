import java.util.Date;

public class BattleRecord {
    private String playerName;
    private String heroClass;
    private boolean playerWon;
    private String monsterType;
    private int rounds;
    private Date date;

    public BattleRecord(String playerName, String heroClass, boolean playerWon, String monsterType, int rounds) {
        this.playerName = playerName;
        this.heroClass = heroClass;
        this.playerWon = playerWon;
        this.monsterType = monsterType;
        this.rounds = rounds;
        this.date = new Date();
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public boolean getPlayerWon() {
        return playerWon;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getRounds() {
        return rounds;
    }

    public Date getDate() {
        return date;
    }
}
