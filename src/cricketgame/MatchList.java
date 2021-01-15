/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MyFamily
 */
public class MatchList extends InOutStream {

    private ArrayList<Matches> newMatchLists = new ArrayList<>();

    private ArrayList<Matches> matchReportLists = new ArrayList<>();

    private CountryList countryListObject;

    public MatchList(CountryList countryListObject, String file, String file_dev) {
        this.file = file;
        this.file_dev = file_dev;
        this.countryListObject = countryListObject;

    }

    public boolean isFullSize(int size, int bound) {
        if (size > bound - 1) {
            return false;
        } else {
            return true;
        }

    }

    public void randomNewMatch() {

        if (isFullSize(newMatchLists.size(), 10)&&countryListObject.getCountryList().size()>1&&canNewMatch()) {

            newMatchLists.add(new Matches());

            int randomTeamA = new Random().nextInt(countryListObject.getCountryList().size());
            int randomTeamB = new Random().nextInt(countryListObject.getCountryList().size());

            while ((randomTeamA == randomTeamB) || doesNotHaveAllPositionInCountry(randomTeamA, randomTeamB)) {

                randomTeamB = new Random().nextInt(countryListObject.getCountryList().size());
                randomTeamA = new Random().nextInt(countryListObject.getCountryList().size());
            }

            newMatchLists.get(newMatchLists.size() - 1).getTeamMatch().add(new Team(countryListObject.getCountryList().get(randomTeamA).name, countryListObject.getCountryList().get(randomTeamA).getPlayerList()));
            newMatchLists.get(newMatchLists.size() - 1).getTeamMatch().add(new Team(countryListObject.getCountryList().get(randomTeamB).name, countryListObject.getCountryList().get(randomTeamB).getPlayerList()));

            newMatchLists.get(newMatchLists.size() - 1).setDetail(countryListObject.getCountryList().get(randomTeamA).name + " vs " + countryListObject.getCountryList().get(randomTeamB).name);

        }

    }

    public boolean doesNotHaveAllPositionInCountry(int randomTeamA, int randomTeamB) {
        String tempPosition[] = {"Wicketkeeper", "Slip", "Gully", "Point", "Cover", "Mid-off", "Bowler", "Mid-on", "Mid-wicket", "Fine leg", "Third man"};

        for (int j = 0; j < 11; j++) {
            int check = 0;
            for (int i = 0; i < countryListObject.getCountryList().get(randomTeamA).getPlayerList().size(); i++) {

                if (tempPosition[j] == null ? countryListObject.getCountryList().get(randomTeamA).getPlayerList().get(i).position == null : tempPosition[j].equals(countryListObject.getCountryList().get(randomTeamA).getPlayerList().get(i).position)) {
                    check++;
                }

            }
            if (check == 0) {
                return true;
            }
             for (int i = 0; i < countryListObject.getCountryList().get(randomTeamB).getPlayerList().size(); i++) {

                if (tempPosition[j] == null ? countryListObject.getCountryList().get(randomTeamB).getPlayerList().get(i).position == null : tempPosition[j].equals(countryListObject.getCountryList().get(randomTeamB).getPlayerList().get(i).position)) {
                    check++;
                }

            }
            if (check == 0) {
                return true;
            }
        }
        

    
           

        return false;
    }

   
    public boolean canNewMatch(){
        int check=0;
    System.out.println(countryListObject.getCountryList().size());
        for(int j=0;j<countryListObject.getCountryList().size();j++){
        for(int i=1;i<countryListObject.getCountryList().size();i++){
          
       if(!(doesNotHaveAllPositionInCountry(i, j))) {
           check++;
       }
            System.out.println(check);
       if(check>=1){
           return true;
       }
    }
        }
                
        
        return false;
    }

    public void randomWinnerAndAddReports(CountryList listRef) {
        String tempCountryName;
        String tempPlayerName;

        if (newMatchLists.size() > 0) {

            //Tie
            if (new Random().nextInt(1000) == 900) {

                newMatchLists.get(0).setEnd_matches("Tie");
                newMatchLists.get(0).setMatchDate(new Date());

                String tempTieACountry = newMatchLists.get(0).getTeamMatch().get(0).getTeamName();
                String tempTieBCountry = newMatchLists.get(0).getTeamMatch().get(1).getTeamName();

                if (abs(listRef.getCountryList().get(listRef.findCountry(tempTieACountry)).ICCTestRating - listRef.getCountryList().get(listRef.findCountry(tempTieBCountry)).ICCTestRating) > 40) {

                    if (listRef.getCountryList().get(listRef.findCountry(tempTieACountry)).ICCTestRating > listRef.getCountryList().get(listRef.findCountry(tempTieBCountry)).ICCTestRating) {

                        listRef.getCountryList().get(listRef.findCountry(tempTieBCountry)).ICCTestAddRating += 40;
                        listRef.getCountryList().get(listRef.findCountry(tempTieACountry)).ICCTestAddRating -= 40;
                    } else {

                        listRef.getCountryList().get(listRef.findCountry(tempTieBCountry)).ICCTestAddRating -= 40;
                        listRef.getCountryList().get(listRef.findCountry(tempTieACountry)).ICCTestAddRating += 40;

                    }

                }

            } //not Tie
            else {
                int isWinner = new Random().nextInt(2);
                //AddPlayerRate
                for (int playerIndex = 0; playerIndex < 7 + new Random().nextInt(2); playerIndex++) {
                    int randomPlayer = new Random().nextInt(11);
                    int randomRate = 1 + new Random().nextInt(4);

                    newMatchLists.get(0).getTeamMatch().get(isWinner).getPlayerTeam().get(randomPlayer).rating += randomRate;
                    tempCountryName = newMatchLists.get(0).getTeamMatch().get(isWinner).getTeamName();
                    tempPlayerName = newMatchLists.get(0).getTeamMatch().get(isWinner).getPlayerTeam().get(randomPlayer).name;
                    listRef.getCountryList().get(listRef.findCountry(tempCountryName)).playerList.get(listRef.findPlayerInCountry(tempCountryName, tempPlayerName)).rating += randomRate;
                }

                for (int playerIndex = 0; playerIndex < 1 + new Random().nextInt(3); playerIndex++) {
                    int randomPlayer = new Random().nextInt(11);
                    int randomRate = 1 + new Random().nextInt(2);

                    newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getPlayerTeam().get(randomPlayer).rating += randomRate;
                    tempCountryName = newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getTeamName();
                    tempPlayerName = newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getPlayerTeam().get(randomPlayer).name;
                    listRef.getCountryList().get(listRef.findCountry(tempCountryName)).playerList.get(listRef.findPlayerInCountry(tempCountryName, tempPlayerName)).rating += randomRate;

                }
                //MinusPlayerRate

                for (int playerIndex = 0; playerIndex < 5 + new Random().nextInt(3); playerIndex++) {
                    int randomPlayer = new Random().nextInt(11);
                    int randomRate = 1 + new Random().nextInt(4);

                    newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getPlayerTeam().get(randomPlayer).rating -= randomRate;
                    tempCountryName = newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getTeamName();
                    tempPlayerName = newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getPlayerTeam().get(randomPlayer).name;
                    listRef.getCountryList().get(listRef.findCountry(tempCountryName)).playerList.get(listRef.findPlayerInCountry(tempCountryName, tempPlayerName)).rating -= randomRate;
                }

                tempCountryName = newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getTeamName();
                listRef.getCountryList().get(listRef.findCountry(tempCountryName)).ICCTestWeight_Matches += 1;
                tempCountryName = newMatchLists.get(0).getTeamMatch().get(isWinner).getTeamName();
                listRef.getCountryList().get(listRef.findCountry(tempCountryName)).ICCTestWeight_Matches += 1;

                //setDetailMatch
                newMatchLists.get(0).setEnd_matches(newMatchLists.get(0).getTeamMatch().get(isWinner).getTeamName() + " win");
                newMatchLists.get(0).setMatchDate(new Date());

                //addCountryRate
                String tempWinnerCountry = newMatchLists.get(0).getTeamMatch().get(isWinner).getTeamName();
                String tempLoserCountry = newMatchLists.get(0).getTeamMatch().get(abs(isWinner - 1)).getTeamName();

                if (abs(listRef.getCountryList().get(listRef.findCountry(tempWinnerCountry)).ICCTestRating - listRef.getCountryList().get(listRef.findCountry(tempLoserCountry)).ICCTestRating) < 40) {
                    listRef.getCountryList().get(listRef.findCountry(tempWinnerCountry)).ICCTestAddRating += 50;
                    listRef.getCountryList().get(listRef.findCountry(tempLoserCountry)).ICCTestAddRating -= 50;

                } else {
                    if (listRef.getCountryList().get(listRef.findCountry(tempWinnerCountry)).ICCTestRating > listRef.getCountryList().get(listRef.findCountry(tempLoserCountry)).ICCTestRating) {
                        listRef.getCountryList().get(listRef.findCountry(tempWinnerCountry)).ICCTestAddRating += 10;
                        listRef.getCountryList().get(listRef.findCountry(tempLoserCountry)).ICCTestAddRating -= 10;
                    } else {
                        listRef.getCountryList().get(listRef.findCountry(tempWinnerCountry)).ICCTestAddRating += 90;
                        listRef.getCountryList().get(listRef.findCountry(tempLoserCountry)).ICCTestAddRating -= 90;
                    }

                }

            }

            //updateCountryRating
            listRef.updateRatingPoint();

            //AddmatchReportLists
            matchReportLists.add(newMatchLists.get(0));

            //AddPlayerReports
            addMatchToPlayerReports(listRef);

            if (newMatchLists.size() != 0) {
                newMatchLists.remove(0);
            }

        }

    }

    public void addMatchToPlayerReports(CountryList listRef) {

        while (matchReportLists.size() > 10) {
            matchReportLists.remove(0);
        }
        String tempCountry = "";
        String tempPlayer = "";

        if (matchReportLists.size() > 0) {

            for (int playerIndex = 0; playerIndex < matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(0).getPlayerTeam().size(); playerIndex++) {

                tempCountry = matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(0).getTeamName();
                tempPlayer = matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(0).getPlayerTeam().get(playerIndex).name;

                while (listRef.getCountryList().get(listRef.findCountry(tempCountry)).getPlayerList().get(listRef.getCountryList().get(listRef.findCountry(tempCountry)).findPlayer(tempPlayer)).getReport().size() > 2) {
                    listRef.getCountryList().get(listRef.findCountry(tempCountry)).getPlayerList().get(listRef.getCountryList().get(listRef.findCountry(tempCountry)).findPlayer(tempPlayer)).getReport().remove(0);
                    //matchReportLists.get(matchReportLists.size()-1).getTeamMatch().get(0).getPlayerTeam().get(playerIndex).getReport().remove(0);
                }

                //  System.out.println(matchReportLists.get(matchReportLists.size()-1).toString());
                //matchReportLists.get(matchReportLists.size()-1).getTeamMatch().get(0).getPlayerTeam().get(playerIndex).addReport(matchReportLists.get(matchReportLists.size()-1));
                listRef.getCountryList().get(listRef.findCountry(tempCountry)).playerList.get(listRef.findPlayerInCountry(tempCountry, tempPlayer)).addReport(matchReportLists.get(matchReportLists.size() - 1));

            }

            for (int playerIndex = 0; playerIndex < matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(1).getPlayerTeam().size(); playerIndex++) {
                tempCountry = matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(1).getTeamName();
                tempPlayer = matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(1).getPlayerTeam().get(playerIndex).name;
                while (listRef.getCountryList().get(listRef.findCountry(tempCountry)).getPlayerList().get(listRef.getCountryList().get(listRef.findCountry(tempCountry)).findPlayer(tempPlayer)).getReport().size() > 2) {
                    matchReportLists.get(matchReportLists.size() - 1).getTeamMatch().get(1).getPlayerTeam().get(playerIndex).addReport(matchReportLists.get(matchReportLists.size() - 1));
                    listRef.getCountryList().get(listRef.findCountry(tempCountry)).getPlayerList().get(listRef.getCountryList().get(listRef.findCountry(tempCountry)).findPlayer(tempPlayer)).getReport().remove(0);

                    //matchReportLists.get(matchReportLists.size()-1).getTeamMatch().get(1).getPlayerTeam().get(playerIndex).getReport().remove(0);
                }

                listRef.getCountryList().get(listRef.findCountry(tempCountry)).playerList.get(listRef.findPlayerInCountry(tempCountry, tempPlayer)).addReport(matchReportLists.get(matchReportLists.size() - 1));

//listRef.getCountryList().get(listRef.findCountry(tempCountry)).getPlayerList().get(listRef.getCountryList().get(listRef.findCountry(tempCountry)).findPlayer(tempPlayer)).addReport(matchReportLists.get(matchReportLists.size()-1));
                // matchReportLists.get(matchReportLists.size()-1).getTeamMatch().get(1).getPlayerTeam().get(playerIndex).addReport(matchReportLists.get(matchReportLists.size()-1));
            }

        }

    }

    public String printNewMatchList() {
        String out = newMatchLists.size() + " matches\n";
        int i = 1;
        for (Matches newMatchList : newMatchLists) {
            i++;

            out += i + "\n" + newMatchList;
        }

        return out;
    }

    public String printMatchReportListList() {
        String out = matchReportLists.size() + " matches\n";
        int i = 1;
        for (Matches matchReportList : matchReportLists) {
            out += i + "\n" + matchReportList;
            i++;
        }
        return out;
    }

    @Override
    public String toString() {
        return "This is New Match List.\n" + printNewMatchList() + "This is Match Report List.\n" + printMatchReportListList();
    }

    public ArrayList<Matches> getMatchReportLists() {
        return matchReportLists;
    }

    public void setMatchReportLists(ArrayList<Matches> matchReportLists) {
        this.matchReportLists = matchReportLists;
    }

    public ArrayList<Matches> getNewMatchLists() {
        return newMatchLists;
    }

    public void setNewMatchLists(ArrayList<Matches> newMatchLists) {
        this.newMatchLists = newMatchLists;
    }

    @Override
    public void write_file() {
        try {
            //-----------------------WRITE MATCH STORAGE .DAT--------------------//
            this.stream_out = new ObjectOutputStream(new FileOutputStream(file));
            stream_out.reset();
            stream_out.writeInt(newMatchLists.size());
            for (Matches instance : newMatchLists) {
                stream_out.writeObject(instance);
            }

            stream_out.writeInt(matchReportLists.size());
            for (Matches instance : matchReportLists) {
                stream_out.writeObject(instance);
            }
//            stream_out.writeInt(matchObjectList.size());
//             for(Matches instance:matchObjectList){
//                 stream_out.writeObject(instance);
//             }
            stream_out.close();
            //-----------------------WRITE MATCH STORAGE .DAT--------------------//
            //-----------------------WRITE ADMIN MATCHVIEWER.TXT--------------------//
            this.writer = new PrintWriter(new FileWriter(file_dev));
            writer.println("NewMatches: " + newMatchLists.size());
            for (Matches instance : newMatchLists) {
                writer.println("\tMatch: " + instance.getDetail());
                for (Team instance2 : instance.getTeamMatch()) {
                    writer.println("\t\tTeam: " + instance2.getTeamName());
                    writer.println("\t\t\t-----------------------------------------------------");
                    for (Player instance3 : instance2.getPlayerTeam()) {
                        writer.println("\t\t\t" + instance3.name + " Age: " + instance3.age + " Rating: " + instance3.rating + " Position: " + instance3.position);
                    }
                    writer.println("\t\t\t-----------------------------------------------------");
                }
            }
            writer.println();
            writer.println("Report: " + matchReportLists.size());
            for (Matches instance : matchReportLists) {
                writer.println("\tMatchDetail: " + instance.end_matches);
                for (Team instance2 : instance.getTeamMatch()) {
                    writer.println("\t\tTeam: " + instance2.getTeamName());
                    writer.println("\t\t\t-----------------------------------------------------");
                    for (Player instance3 : instance2.getPlayerTeam()) {
                        writer.println("\t\t\t" + instance3.name + " Age: " + instance3.age + " Rating: " + instance3.rating + " Position: " + instance3.position);
                    }
                    writer.println("\t\t\t-----------------------------------------------------");
                }
            }
            writer.println();
//             writer.println("MatchObject: "+matchObjectList.size());
//             for(Matches instance:matchObjectList){
//                 writer.println("\tMatch: "+instance.getDetail());
//                 for(Team instance2:instance.getTeamMatch()){
//                     writer.println("\t\tTeam: "+instance2.getTeamName());
//                      writer.println("\t\t\t-----------------------------------------------------");
//                     for(Player instance3:instance2.getPlayerTeam()){
//                         writer.println("\t\t\t"+instance3.name+" Age: "+instance3.age+" Rating: "+instance3.rating);
//                   }
//                    writer.println("\t\t\t-----------------------------------------------------");
//                 }
//             }
            writer.close();
            System.out.println("Write: " + "NewMatch: " + newMatchLists.size() + " Report: " + matchReportLists.size() +/*" MatchObj: "+matchObjectList.size()+*/ " to " + file);
            System.out.println("Finish writing: MatchList");
            //-----------------------WRITE ADMIN MATCHVIEWER.TXT--------------------//

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatchList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MatchList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void read_file() {
        try {
            //----------------------READ FROM MATCH STORAGE.DAT----------------//
            this.stream_in = new ObjectInputStream(new FileInputStream(file));
            int nNewMatch = stream_in.readInt();
            for (int i = 0; i < nNewMatch; i++) {
                newMatchLists.add((Matches) stream_in.readObject());
            }
            int nReport = stream_in.readInt();
            for (int i = 0; i < nReport; i++) {
                matchReportLists.add((Matches) stream_in.readObject());
            }
//                int nMatchObj=stream_in.readInt();
//                for(int i=0;i<nMatchObj;i++){
//                 matchObjectList.add((Matches) stream_in.readObject());
//                }
            stream_in.close();
            System.out.println("Read: " + "NewMatch: " + nNewMatch + " Report: " + nReport/*+" MatchObj: "+nMatchObj*/ + " from " + file);
            System.out.println("Finish Reading: MatchList");
            //----------------------READ FROM MATCH STORAGE.DAT----------------//
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatchList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MatchList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatchList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
