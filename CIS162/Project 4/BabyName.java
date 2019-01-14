import java.util.*;
import java.text.*;
/**
 * Initializes variables and contains toString() class
 *
 * @author (AJ Natzic)
 * @version (10-30-2017)
 */
public class BabyName implements Comparable
{
    String name = "";
    boolean gender = false;
    int numBabys = 0;
    int birthYear = 0;
    
    public BabyName(String n, boolean g, int count, int yr){
        name = n;
        gender = g;
        numBabys = count;
        birthYear = yr;
    }
    
    public int compareTo(Object other){
        BabyName b = (BabyName) other;
        return (b.numBabys - numBabys);
    }
    
    public boolean isFemale(){
        
        if(gender == true){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getName(){
        return name;
    }
    
    public int getCount(){
        return numBabys;
    }
    
    public int getYear(){
        return birthYear;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void setCount(int count){
        numBabys = count;
    }
    
    public void setYear(int yr){
        birthYear = yr;
    }
    
    public String toString(){
        String toString = "";
        String gender = "";
        if(isFemale() == true){
            gender = "girls";
        }
        else{
            gender = "boys";
        }
        toString = NumberFormat.getNumberInstance(Locale.US).format(numBabys) + " " + gender + " named " + name + " in " + birthYear;
        return toString;
    }
    
    public static void main(String [] args){
        BabyName test = new BabyName("AJ", false, 1, 1999);
        System.out.println(test.isFemale());
        System.out.println(test.getName());
        System.out.println(test.getCount());
        System.out.println(test.getYear());
        System.out.println(test.toString());
    }
}
