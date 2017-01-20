package model;

import java.time.LocalDate;

/**
 *
 * @author ironkk
 */
public class Player {

    private String name;
    
     private LocalDate birth;

    private int nassists;

    private int nbaskets;

    private int nrebots;

    private String pos;
    
    private Team team;

    public Player() {
    }

    public Player(String name, LocalDate birth, int nassists, int nbaskets, int nrebots, String pos, Team team) {
        this.name = name;
        this.birth = birth;
        this.nassists = nassists;
        this.nbaskets = nbaskets;
        this.nrebots = nrebots;
        this.pos = pos;
        this.team = team;
    }

    
    
    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
    

    
    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getNrebots() {
        return nrebots;
    }

    public void setNrebots(int nrebots) {
        this.nrebots = nrebots;
    }

    public int getNbaskets() {
        return nbaskets;
    }

    public void setNbaskets(int nbaskets) {
        this.nbaskets = nbaskets;
    }

    public int getNassists() {
        return nassists;
    }

    public void setNassists(int nassists) {
        this.nassists = nassists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", birth=" + birth + ", nassists=" + nassists + ", nbaskets=" + nbaskets + ", nrebots=" + nrebots + ", pos=" + pos + ", team=" + team + '}';
    }

    
}
