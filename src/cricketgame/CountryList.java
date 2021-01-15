/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

/**
 *
 * @author PatNum
 */
public class CountryList extends InOutStream{
    private ArrayList<Country> countryList=new ArrayList<>();// ArrayList contain Country
    
    
    
//Phol edited
  public CountryList() {
  
    }
//Phol edited
    public CountryList(String file,String file2,String file3) {
        super(file,file2,file3);
    }

public String displayPlayerReports(String countryName,String playerName){
    String out="Name: "+countryList.get(findCountry(countryName)).playerList.get(findPlayerInCountry(countryName,playerName)).name+"\n";
  for(int i=0;i<countryList.get(findCountry(countryName)).playerList.get(findPlayerInCountry(countryName,playerName)).getReport().size();i++){
   out+=countryList.get(findCountry(countryName)).playerList.get(findPlayerInCountry(countryName,playerName)).getReport().get(i)+"\n";
 
  }
    return out; 
}
 public String displayCountryList(){
     String out="";
     for(Country instance: countryList){
                out+="Country: "+instance.name+"\n";
                out+="Weight Matches: "+instance.ICCTestWeight_Matches+"\n";
                out+="Point: "+instance.ICCTestPoints+"\n";
                out+="Rating: "+instance.ICCTestRating+"\n";
                for(Player instance2:instance.playerList){
                      out+="\t-------------------------\n";
                      out+="\tName: "+instance2.name+"\n";
                      out+="\tAge: "+instance2.age+"\n";
                      out+="\tRating: "+instance2.rating+"\n";
                      out+="\t-------------------------"+"\n";
                }
     }
     return out;
 }
    

    
     
    public boolean add_country(Country in){//Add country to list
        for(int i=0;i<countryList.size();i++){
            if(countryList.get(i).name == null ? in.name == null : countryList.get(i).name.equals(in.name)){//Same country
                System.out.println("Have "+countryList.get(i).name+ " in DataBase.");
                return false;
            }
        }
        countryList.add(in);
        return true;
    }
    public void del_country(String name){//Del country from list
        countryList.remove(findCountry(name));
    }
    public boolean add_player(String countryName,Player player){
        
        if(countryList.get(findCountry(countryName)).player_add(player)==true){
            return true;
        }
        return false;
    }
    public boolean remove_player(String countryName,String name){
    if(countryList.get(findCountry(countryName)).player_remove(name)==true){
       return true;
    }
    
         return false;
    }
    
    
    
    
    public int findCountry(String name){//return as index
        
        for(int i=0;i<countryList.size();i++){
            
            if(countryList.get(i).name == null ? name == null : countryList.get(i).name.equals(name)){
                return i;//return index
            }
            
        }
        return -1;
    }
    public int findPlayerInCountry(String cname,String name){
        
       return countryList.get(findCountry(cname)).findPlayer(name);
    }
    public void sort_alphabetical_CI(){
        Collections.sort(countryList, (Country c1, Country c2) -> {
            String s1=c1.name;
            String s2=c2.name;
            return s1.compareToIgnoreCase(s2);
        });
    }
     public void updateRatingPoint(){
         
           
        for(Country instance:countryList){
           
           
            int sum=0;
            for(Player instance2:instance.playerList){
                sum+=instance2.rating;
            }
            
            if(instance.playerList.size()>0)
             instance.ICCTestRating=(sum/instance.playerList.size())+instance.ICCTestAddRating;
            else instance.ICCTestRating=instance.ICCTestAddRating;
                
            if(instance.ICCTestRating<0){
                instance.ICCTestRating=0;
            }
     
            instance.ICCTestPoints=instance.ICCTestRating*instance.ICCTestWeight_Matches;

            if(instance.ICCTestPoints<0){
                instance.ICCTestPoints=0;
            }
        
        
        }
        
    }
 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
 
    @Override
    public String toString() {
        String out="";
        for(Country instance:countryList){
            out+=instance.name+"\n";
            for(Player instance_2:instance.playerList){
                out+=instance_2+"\n";
            }
        }
        return out;
    }

    @Override
    public void write_file() {
        try {
            this.stream_out=new ObjectOutputStream(new FileOutputStream(file));
            stream_out.reset();
            stream_out.writeInt(countryList.size());
            int nPlayer=0;
            for(Country instance:countryList){
                nPlayer+=instance.playerList.size();
            }
            stream_out.writeInt(nPlayer);
            for(Country instance:countryList){
                stream_out.writeObject(instance);
            }
            stream_out.close();
            
             this.writer=new PrintWriter(new FileWriter(file_dev));
            for(Country instance2:countryList){
                writer.println("Country: "+instance2.name);
                writer.println("Weight Matches: "+instance2.ICCTestWeight_Matches);
                writer.println("Point: "+instance2.ICCTestPoints);
                writer.println("Rating: "+instance2.ICCTestRating);
//                
                for(Player instance3:instance2.playerList){
                    writer.println("\t-------------------------");
                    writer.println("\tName: "+instance3.name);
                    writer.println("\tAge: "+instance3.age);
                    writer.println("\tRating: "+instance3.rating);
                    writer.println("\tPosition: "+instance3.position);
                    writer.println("\t-------------------------");
                }
                writer.println();
            }
            
            writer.close();
            System.out.println("Write: "+"Size: "+countryList.size()+" Player: "+nPlayer+" to "+file);
            System.out.println("Finish Writing");
        } catch (IOException ex) {
            Logger.getLogger(CountryList.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR!");
        }
    }

    @Override
    public  void read_file() {
        try {
      
            //----------------------READ FROM PLAYER STORAGE.DAT----------------//
            this.stream_in=new ObjectInputStream(new FileInputStream(file));
            int Size=stream_in.readInt();
            int nPlayer=stream_in.readInt();
   
           for(int i=0;i<Size;i++){
               add_country((Country) stream_in.readObject());
           }
            stream_in.close();
            //----------------------READ FROM PLAYER STORAGE.DAT----------------//
            //----------------------READ FROM DATA BASE.TXT-------------------------//
            Scanner reader = new Scanner(new File(file_input));
            int readCountry_Scanner=0,readPlayer_Scanner=0;
            while(reader.hasNextLine()){
                reader.nextLine();
                String text_country=reader.nextLine();
                reader.nextLine();
                int text_numPlayer=reader.nextInt();
                 reader.nextLine();
                //System.out.println(text_numPlayer);
                if(add_country(new Country(text_country))==true)readCountry_Scanner++;//Country read
                for(int i=0;i<text_numPlayer;i++){
                    reader.nextLine();
                    String text_name=reader.nextLine();
                    reader.nextLine();
                    int text_age=reader.nextInt();
                    reader.nextLine();
                    int text_rating=reader.nextInt();
                    reader.nextLine();
                    reader.nextLine();
                    String text_pos=reader.nextLine();
                    //reader.nextLine();
                    if(add_player(text_country,new Player(text_rating,text_name,text_age,text_pos))==true)readPlayer_Scanner++;//Player Read
                }
            }
            System.out.println("Read: "+"Size:" +Size+" Player: "+nPlayer+" from "+file);
            System.out.println("Read: "+"Size:" +readCountry_Scanner+" Player: "+readPlayer_Scanner+" from "+file_input);
            reader.close();
            //----------------------READ FROM TEAM STORAGE.TXT-------------------------//
            System.out.println("Finish reading");
        } catch (IOException ex) {
            Logger.getLogger(CountryList.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR2!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CountryList.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        sort_alphabetical_CI();
        
        
        } 

    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }
        
        
                   
    
    
}
