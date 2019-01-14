import java.util.*;
import java.io.*;
/**
 * Tests all methods in BabyName
 *
 * @author (AJ Natzic)
 * @version (11-9-2017)
 */
public class BabyTest
{
    public static void main(String args[]){
        BabyNamesDatabase db = new BabyNamesDatabase();
        // read small data file created just for testing
        db.readBabyNameData("MySampleData.txt");
        // check number of records
        if(db.countAllNames() != 11){
            System.out.println("Error: Number of names should be 11");
        }
        // counts all girls
        if(db.countAllGirls() != 3){
            System.out.println("Error: Number of girls should be 3");
            System.out.println(db.countAllGirls());
        }
        // counts all boys
        if(db.countAllBoys() != 8){
            System.out.println("Error: Number of boys shoudld be 8");
            System.out.println(db.countAllBoys());
        }
        // check most popular girl
        BabyName popularGirl = db.mostPopularGirl(1999);
        String girlName = popularGirl.getName();
        if(girlName.equals("Ashley") == false){
            System.out.println("Error: Popular girl in 1999 should be Ashley");
        }
        // check most popular boy
        BabyName popularBoy = db.mostPopularBoy(1999);
        String boyName = popularBoy.getName();
        if(boyName.equals("AJ") == false){
            System.out.println("Error: Popular boy in 1999 should be AJ");
        }
        // check number of records for one name
        ArrayList<BabyName> nameList = db.searchForName("Dodge");
        if(nameList.size() != 1){
            System.out.println("Error: Name search should only return Dodge");
        }
        // check number of records for one year
        ArrayList<BabyName> tempList = db.searchForYear(2003);
        if(tempList.size() != 3){
            System.out.println("Error: Should be 3 records in 2003");
        }
        // gets top ten baby names for given year
        ArrayList<BabyName> topTen = db.topTenNames(2003);
        if(topTen.get(0) != db.mostPopularBoy(2003)){
            System.out.println("Error: Most popular boy name incorrect");
        }
        System.out.println("Scanning complete.");
    }

}
