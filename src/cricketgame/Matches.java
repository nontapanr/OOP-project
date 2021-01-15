/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author PatNum
 */
public class Matches implements Serializable{
    private String detail;
    protected String end_matches;
    private Date matchDate;
    
    private ArrayList<Team> teamMatch=new ArrayList<>();

    public Matches() {
    }

    public void setTeamMatch(ArrayList<Team> teamMatch) {
        this.teamMatch = teamMatch;
    
    }

    public ArrayList<Team> getTeamMatch() {
        return teamMatch;
    }

   
    @Override
    public String toString() {
       String out="Match: "+detail+"\nDate: "+matchDate+"\nResult: "+end_matches+"\n";
       for(Team instance:teamMatch){
           out+=instance+"\n";
        
       }
        return out;
    }

    public String getEnd_matches() {
        return end_matches;
    }

    public void setEnd_matches(String end_matches) {
        this.end_matches = end_matches;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
  

    
    
}
