/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketgame;



import java.util.Collections;


/**
 *
 * @author Phol
 */
public class ICCTestRankings implements Rankings {

    private CountryList data;

    public ICCTestRankings(CountryList countryList) {
        this.data = countryList;
    }
    
    
    @Override
    public void sort() {
      Collections.sort(data.getCountryList(), (Country c1, Country c2) -> {
           
        return Integer.compare(c2.ICCTestRating, c1.ICCTestRating);
        });
    }
    
    
      
    public CountryList getData() {
        return data;
    }

    public void setData(CountryList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        sort();
        int index=1;
        String out="ICC Ranking: \n";
        for(Country instance:data.getCountryList()){
            out+="\t "+index+" "+instance.name+" Rating: "+instance.ICCTestRating+"\n";
            index++;
        }
        return out;
    }
    
}
