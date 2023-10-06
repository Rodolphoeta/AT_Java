import java.util.Date;

public class BattleLog {
    private Date date;
    private String hero;
    private String outcome;
    private String monster;
    private int rounds;

    public BattleLog(Date date, String hero, String outcome, String monster, int rounds) {
        this.date = date;
        this.hero = hero;
        this.outcome = outcome;
        this.monster = monster;
        this.rounds = rounds;
    }

    public Date getDate() {
        return date;
    }

    public String getHero() {
        return hero;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getMonster() {
        return monster;
    }

    public int getRounds() {
        return rounds;
    }
}
