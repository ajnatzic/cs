import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class myPhoneLabExam here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class myPhoneLabExam
{
    private int numTexts = 0;
    private double dataUsed = 0.0;
    private double battLife = 0.0;
    private String custName = "";
    private String phoneNum = "";
    private final double DATA_PER_MIN = 65.0 / 60.0;
    private final double MAX_MINUTES = 720.0;
    
    public myPhoneLabExam(String name, String num){
        numTexts = 0;
        dataUsed = 0.0;
        battLife = 0.0;
        custName = name;
        phoneNum = num;
    }
    
    public int getNumTexts(){
        return numTexts;
    }
    
    public double getBatteryLife(){
        return battLife;
    }
    
    public double getDataUsage(){
        return dataUsed;
    }
    
    public void setName(String n){
        custName = n;
    }
    
    public void setPhoneNumber(String n){
        phoneNum = n;
    }
    
    public void chargeBattery(int mins){
        if(battLife == 100){
            JOptionPane.showMessageDialog(null, "Battery is charged");
        }
        else if(battLife > 100){
            battLife += mins / 120;
        }
    }
}
