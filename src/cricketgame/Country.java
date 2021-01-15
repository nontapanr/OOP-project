/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 *
 * @author PatNum
 */
public class Country implements Serializable{
    protected ArrayList<Player> playerList=new ArrayList<>();
    protected String name;
    
    protected Date date;
    protected String info;
    protected int ICCTestWeight_Matches;
    protected int ICCTestPoints;
    protected int ICCTestRating;
    protected int ICCTestAddRating;
    transient protected int num;
    transient protected Button addNewplayerBtn;
    transient protected Button deleteCountryBtn;
    public Country(String name) {
        this.name = name;
        this.date=new Date();
        this.addNewplayerBtn=new Button("Add New Player");
        this.deleteCountryBtn= new Button("Delete Country");
             this.deleteCountryBtn.setPrefSize(CricketGame.buttonWidth-120, CricketGame.buttonHeight-20);
                this.addNewplayerBtn.setPrefSize(CricketGame.buttonWidth-120, CricketGame.buttonHeight-20);
               this.addNewplayerBtn.setStyle(" -fx-background-color :#C70039 ; "+"-fx-text-fill: White;"+"-fx-background-radius: 20px;");
              this.deleteCountryBtn.setStyle(" -fx-background-color :#C70039  ; "+"-fx-text-fill: White;"+"-fx-background-radius: 20px;");
                this.addNewplayerBtn.setFont(Font.loadFont(CricketGame.fontPathBold,14));
              this.deleteCountryBtn.setFont(Font.loadFont(CricketGame.fontPathBold,14));
                this.deleteCountryBtn.setStyle(" -fx-background-color :  #85D9B9; " );
        this.deleteCountryBtn.setStyle(" -fx-background-color : #85D9B9; " );
       
    }

    public Country(String name, int weight_matches, int points, int rating) {
   
        this.name = name;
         this.date=new Date();
        this.ICCTestWeight_Matches= weight_matches;
        this.ICCTestPoints = points;
        this.ICCTestRating = rating;
        this.addNewplayerBtn=new Button("Add New Player");
        this.deleteCountryBtn= new Button("Delete Country");
       this.deleteCountryBtn.setPrefSize(CricketGame.buttonWidth-120, CricketGame.buttonHeight-20);
                this.addNewplayerBtn.setPrefSize(CricketGame.buttonWidth-120, CricketGame.buttonHeight-20);
               this.addNewplayerBtn.setStyle(" -fx-background-color :#C70039 ; "+"-fx-text-fill: White;"+"-fx-background-radius: 20px;");
              this.deleteCountryBtn.setStyle(" -fx-background-color :#C70039  ; "+"-fx-text-fill: White;"+"-fx-background-radius: 20px;");
                this.addNewplayerBtn.setFont(Font.loadFont(CricketGame.fontPathBold,14));
              this.deleteCountryBtn.setFont(Font.loadFont(CricketGame.fontPathBold,14));
                this.deleteCountryBtn.setStyle(" -fx-background-color :  #85D9B9; " );
        this.deleteCountryBtn.setStyle(" -fx-background-color : #85D9B9; " );
       
    }

    public Country(String name, int ICCTestWeight_Matches) {
        this.name = name;
        this.ICCTestWeight_Matches = ICCTestWeight_Matches;
         this.date=new Date();
        this.addNewplayerBtn=new Button("Add New Player");
        this.deleteCountryBtn= new Button("Delete Country");
     this.deleteCountryBtn.setPrefSize(CricketGame.buttonWidth-120, CricketGame.buttonHeight-20);
                this.addNewplayerBtn.setPrefSize(CricketGame.buttonWidth-120, CricketGame.buttonHeight-20);
               this.addNewplayerBtn.setStyle(" -fx-background-color :#C70039 ; "+"-fx-text-fill: White;"+"-fx-background-radius: 20px;");
              this.deleteCountryBtn.setStyle(" -fx-background-color :#C70039  ; "+"-fx-text-fill: White;"+"-fx-background-radius: 20px;");
                this.addNewplayerBtn.setFont(Font.loadFont(CricketGame.fontPathBold,14));
              this.deleteCountryBtn.setFont(Font.loadFont(CricketGame.fontPathBold,14));
                this.deleteCountryBtn.setStyle(" -fx-background-color :  #85D9B9; " );
        this.deleteCountryBtn.setStyle(" -fx-background-color : #85D9B9; " );
       
    }
 
    public int findPlayer(String name){
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).name == null ? name == null : playerList.get(i).name.equals(name)){
                return i;//return i as index
            }
        }
        return -1;
    }
    public boolean player_add(Player in){
        
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).name == null ? in.name == null : playerList.get(i).name.equals(in.name))//Same Player
            {        
                //System.out.println("Update "+playerList.get(i).name+" in "+this.name+".");
                return false;
            }
        }
        playerList.add(in);
        sort_alphabetical_CI();
        return true;
    }
        public boolean player_remove(String name){
        
        for(int i=0;i<playerList.size();i++){
          if(playerList.get(i).name == null ? name == null : playerList.get(i).name.equals(name)){
              playerList.remove(i);
              return true;
          }
        }
           return false;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getICCTestWeight_Matches() {
        return ICCTestWeight_Matches;
    }

    public int getICCTestPoints() {
        return ICCTestPoints;
    }

    public int getICCTestRating() {
        return ICCTestRating;
    }
        
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    
      public void sort_alphabetical_CI(){
        Collections.sort(playerList, (Player p1, Player p2) -> {
            String s1=p1.name;
            String s2=p2.name;
            return s1.compareToIgnoreCase(s2);
        });
    }
    @Override
    public String toString() {
        return "Country{" + "playerList=" + playerList + ", name=" + name + ", date=" + date + ", info=" + info + ", weight_matches=" + ICCTestWeight_Matches + ", point=" + ICCTestPoints + ", rating=" + ICCTestRating + '}';
    }

  
    
}
