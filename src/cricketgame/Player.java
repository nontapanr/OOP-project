/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;

import static cricketgame.CricketGame.fontPathBold;
import static cricketgame.CricketGame.styleinCountryAndPlayer;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 *
 * @author PatNum
 */
public class Player extends Person implements Serializable{
    private ArrayList<Matches> report;
    protected int rating;
    protected String position;
    transient protected Button statisticBtn;
    transient protected  Button delBtn;
    public Player(int rating, String name, int age,String position) {
        super(name, age);
        this.report=new ArrayList<>();
        this.rating = rating;
        this.position=position;
        this.statisticBtn=new Button("Player Reports");
        this.delBtn=new Button("X");
        

                  this.statisticBtn.setStyle(" -fx-background-color :#92e5ed  ; "+ "-fx-background-radius: 20px;");
                 this.delBtn.setStyle(" -fx-background-color :#C70039  ; " + "-fx-text-fill: White;" + "-fx-background-radius: 20px;");
               this.statisticBtn.setFont(Font.loadFont(fontPathBold, 14));
                 this.delBtn.setFont(Font.loadFont(fontPathBold, 14));

        
        
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Matches> getReport() {
        return report;
    }

    public void setReport(ArrayList<Matches> report) {
        this.report = report;
    }
    public void addReport(Matches newReport){
        report.add(newReport);
        
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Button getStatisticBtn() {
        return statisticBtn;
    }

    public void setStatisticBtn(Button statisticBtn) {
        this.statisticBtn = statisticBtn;
    }

    public Button getDelBtn() {
        return delBtn;
    }

    public void setDelBtn(Button delBtn) {
        this.delBtn = delBtn;
    }

    @Override
    public String toString() {
        return "Player{" + "Name=" + name +" rating=" + rating + ", statisticBtn=" + statisticBtn + ", delBtn=" + delBtn + ", Position=" + position +'}';
    }
    
   

 

   

   
    
 


    
}
