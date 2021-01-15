/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MyFamily
 */
public class Team implements Serializable{
    private String teamName;
    
    private ArrayList<Player> playerTeam=new ArrayList<>();
    
    //private ArrayList<Player> playerTeam=new ArrayList<Player>();
//
//private CountryList countryTeamsList=new CountryList();

  //  private String countryName;

    public Team() {
    }

    public Team(String teamName, ArrayList<Player> notRandomPlayerTeam) {
       
        
        this.teamName = teamName;
        addRandomNewPlayerInTeam(notRandomPlayerTeam);
    }

   
    

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Player> getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(ArrayList<Player> playerTeam) {
        this.playerTeam = playerTeam;
    }

    public void addRandomNewPlayerInTeam(ArrayList<Player> newPlayerTeam){
 
    ArrayList<Integer> tempRandomNumber=new ArrayList<>();
    
ArrayList<String> tempPosition=new ArrayList<>();
     
         playerTeam.clear();
        for(int playerListIndex=0;playerListIndex<11;){
           
        int randomNumber=new Random().nextInt(newPlayerTeam.size());  
        
        if(isNewRandomNumber(randomNumber,tempRandomNumber)&&isNewPosition(newPlayerTeam.get(randomNumber).position,tempPosition)){
 
         playerTeam.add(newPlayerTeam.get(randomNumber));
             playerListIndex++;
             tempRandomNumber.add(randomNumber);
            tempPosition.add(newPlayerTeam.get(randomNumber).position);
        }      
        }      
        
       tempPosition.clear();
         tempRandomNumber.clear();
 
        
    }
private boolean isNewRandomNumber(int newRandomNumber,ArrayList<Integer>tempRandomNumber){
  

          for(int j=0;j<tempRandomNumber.size();j++){
            
        if(newRandomNumber==tempRandomNumber.get(j)){
          
           return false;
        }
        }
        return true;
  }

    private boolean isNewPosition(String newPosition,ArrayList<String>tempPosition){
       
        
        for(int i=0;i<tempPosition.size();i++){

            if(newPosition == null ? tempPosition.get(i) == null : newPosition.equals(tempPosition.get(i))){
                
                return false;
            }     
        }
        
        return true;
        
        
        
    }
    
    
    
    @Override
    public String toString() {
                String out=teamName+'\n';
             int i=1;   
        for(Player instance:playerTeam){
            
            out+=instance+"\n";
          
        }
        return out;
    }
    
    
    //    public void addRandomNewPlayerInTeam(){
//    Random randomObject=new Random();
//    ArrayList<Integer> tempRandomNumber=new ArrayList<>();
//    boolean IsNewRandomNumber=true;
//     
//   
//        for(int countryIndex=0;countryIndex<countryTeamsList.getCountryList().size();countryIndex++){
//       
//        for(int playerListIndex=0;playerListIndex<11;){
//        int randomNumber=randomObject.nextInt(countryTeamsList.getCountryList().get(countryIndex).getPlayerList().size());  
//        
//        if(isNewRandomNumber(randomNumber,tempRandomNumber)||playerListIndex==0){
//   
//         playerTeam.add(countryTeamsList.getCountryList().get(countryIndex).getPlayerList().get(randomNumber));
//             playerListIndex++;
//             tempRandomNumber.add(randomNumber);
//        }      
//        }      
//         countryTeamsList.getCountryList().get(countryIndex).setPlayerList((ArrayList<Player>) playerTeam.clone());
//         playerTeam.clear();
//         tempRandomNumber.clear();
// 
//        }
//    }

    
    
    
}
