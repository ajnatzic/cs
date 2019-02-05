
import java.util.*;
import java.io.*;
/**
 * Searches Baby Name text file to do user tasks
 *
 * @author (AJ Natzic)
 * @version (10-31-17)
 */
public class BabyNamesDatabase
{
    private ArrayList<BabyName> allNames;
    public BabyNamesDatabase(){
        allNames = new ArrayList<BabyName>();
    }

    public int countAllNames(){
        return allNames.size();
    }

    public int countAllGirls(){
        int i = 0;
        int totalGirls = 0; 
        for(i = 0; i < countAllNames(); ++i){
            if(allNames.get(i).isFemale() == true){
                ++totalGirls;
            }
        }
        return totalGirls;
    }

    public int countAllBoys(){
        int i = 0;
        int totalBoys = 0; 
        for(i = 0; i < countAllNames(); ++i){
            if(allNames.get(i).isFemale() == false){
                ++totalBoys;
            }
        }
        return totalBoys;
    }

    public BabyName mostPopularGirl(int year){
        int i = 0;
        int popGirlName = 0;
        int numBirths = 0;
        for(i = 0; i < countAllNames(); ++i){
            if(allNames.get(i).getYear() == year && allNames.get(i).isFemale() == true){
                if(allNames.get(i).getCount() > numBirths){
                    numBirths = allNames.get(i).getCount();
                    popGirlName = i;
                }
            }
        }
        return allNames.get(popGirlName);
    }

    public BabyName mostPopularBoy(int year){
        int i = 0;
        int numBirths = 0;
        int popBoyName = 0;
        for(i = 0; i < countAllNames(); ++i){
            if(allNames.get(i).getYear() == year && allNames.get(i).isFemale() == false){
                if(allNames.get(i).getCount() > numBirths){
                    numBirths = allNames.get(i).getCount();
                    popBoyName = i;
                }
            }
        }
        return allNames.get(popBoyName);
    }

    public ArrayList<BabyName> searchForName(String name){
        ArrayList<BabyName> searchForName = new ArrayList<BabyName>();
        int i = 0;
        for(i = 0; i < countAllNames(); ++i){
            if(allNames.get(i).getName().equalsIgnoreCase(name)){
                searchForName.add(allNames.get(i));
            }
        }
        Collections.sort(searchForName);
        return searchForName;
    }

    public ArrayList<BabyName> searchForYear(int year){
        ArrayList<BabyName> searchForYear = new ArrayList<BabyName>();
        int i = 0;
        for(i = 0; i < countAllNames(); ++i){
            if(allNames.get(i).getYear() == year){
                searchForYear.add(allNames.get(i));
            }
        }
        Collections.sort(searchForYear);
        return searchForYear;
    }

    public ArrayList<BabyName> topTenNames(int year){
        ArrayList<BabyName> topTenNames = new ArrayList<BabyName>();
        topTenNames = searchForYear(year);
        Collections.sort(topTenNames);
        int i;
        for(i = 10; i < topTenNames.size();){
            topTenNames.remove(i);
        }
        return topTenNames; 
    }

    public void readBabyNameData(String filename){
        // Read the full set of data from a text file
        try{
            // open the text file and use a Scanner to read the text
            FileInputStream fileByteStream = new FileInputStream(filename);
            Scanner scnr = new Scanner(fileByteStream);
            scnr.useDelimiter("[,\r\n]+");

            // keep reading as long as there is more data
            while(scnr.hasNext()) {
                String name = scnr.next();
                String gender = scnr.next();
                int count = scnr.nextInt();
                int year = scnr.nextInt();
                boolean isFemale;
                if(gender.equalsIgnoreCase("F")){
                    isFemale = true;
                }
                else{
                    isFemale = false;
                }
                BabyName entry = new BabyName(name, isFemale, count, year);
                allNames.add(entry);
            }
            fileByteStream.close();
        }
        catch(IOException e) {
            System.out.println("Failed to read the data file: " + filename);
        }

    }

}
