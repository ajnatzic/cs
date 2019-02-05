
/**
 * Project 2, describes phone usage
 *
 * @author (AJ Natzic)
 * @version (10-4-2017)
 */
import java.util.Random;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.lang.Math;
import java.text.DecimalFormat;
public class myPhone
{
    private int numTexts;
    private double dataUsed;
    private double remBattery;
    private String customerName;
    private String phoneNum;
    private final double DATA_PER_MIN = 65 / 60.0;
    private final double MAX_MINUTES = 720.0;

    public myPhone (String name, String num){
        numTexts = 0;
        dataUsed = 0.0;
        remBattery = 0.0;
        customerName = name;
        phoneNum = num;

    }

    public int getNumTexts(){
        return numTexts;  
    }

    public double getBatteryLife(){
        DecimalFormat fmt3 = new DecimalFormat("#");
        if (remBattery <= 1.0 && remBattery > 0.75){
            remBattery = remBattery * 100;
            JOptionPane.showMessageDialog(null, "Battery is " + fmt3.format(remBattery) + " percent charged!"); 
        }
        else if (remBattery <= 0.25){
            JOptionPane.showMessageDialog(null, "Battery needs to be charged. Less than 25 percent remaining."); 
        }

        else if (remBattery <= 0.75 && remBattery > 0.25){
            remBattery = remBattery * 100;
            JOptionPane.showMessageDialog(null, "Battery is " + fmt3.format(remBattery) + " percent charged."); 
        }

        return remBattery;   
    }

    public double getDataUsage(){
        return dataUsed;
    }

    public void setName(String n){
        customerName = n;
    }

    public void setPhoneNumber(String n){

        if(n.length() == 10){
            phoneNum = phoneNum;
        }

        else{
            phoneNum = "9999999999";
        }

    }

    public void chargeBattery(int mins){
        if (mins < 0){
            remBattery = remBattery;
        }
        else{
            if (remBattery <= 0){
                remBattery = 0;   
            }
            if (mins >= 120){
                remBattery = 1.0;
            }
            else if (mins > 0 && mins < 120){
                remBattery = remBattery + (mins / 120); 
            }
        }
    }

    public void streamAudio(int mins){
        if (mins < 0){
            dataUsed = dataUsed;
            remBattery = remBattery;
        }
        else{
            if (remBattery >= 1.0){
                dataUsed += (DATA_PER_MIN * mins) / 1000;
                remBattery = (MAX_MINUTES - mins) / MAX_MINUTES;
            }
            else if (remBattery < 1.0 && remBattery > 0.0){
                if ((remBattery * MAX_MINUTES) < mins){
                    dataUsed += ((((remBattery * MAX_MINUTES) - (DATA_PER_MIN * mins)) + (DATA_PER_MIN * mins)) / 1000);
                }
                else{
                    dataUsed += (DATA_PER_MIN * mins) / 1000;
                }
                remBattery = remBattery - (mins / MAX_MINUTES); 
            }
        }
    }

    public void sendText(String text){
        JOptionPane.showMessageDialog(null, text);

        numTexts += 1;
    }

    public void readText(){
        Random rand = new Random();
        int choice = rand.nextInt(5) + 1;
        if (choice == 1){
            JOptionPane.showMessageDialog(null, "Hello " + customerName + " :)");
        }
        else if(choice == 2){
            JOptionPane.showMessageDialog(null, "Welcome to the automated phone usage calculator " + customerName + "!");
        }
        else if(choice == 3){
            JOptionPane.showMessageDialog(null, "Lovely weather we're having, don't you think " + customerName + "?");
        }
        else if (choice == 4){
            JOptionPane.showMessageDialog(null, "I love my job!! :) Let's get started " + customerName + "!");
        }
        else if (choice == 5){
            JOptionPane.showMessageDialog(null, "Can not wait to calculate some phone usage! Are you ready " + customerName + "?");
        }
    }

    public void printStatement(){
        System.out.println("myPhone Monthly Statement");
        System.out.println();
        System.out.println("Customer:             " + customerName);
        System.out.println("Number:               " + fmtPhoneNumber());
        System.out.println("Texts:                " + getNumTexts());
        DecimalFormat fmt2 = new DecimalFormat("#.##");
        System.out.println("Data Usage:           " + fmt2.format(dataUsed) + " (GB)");
        System.out.println();

        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        System.out.println("2GB Plan:             " + fmt.format(50.00));
        System.out.println("Additional data fee:  " + fmt.format(calcAdditionalDataFee()));
        System.out.println("Universal Usage (3%): " + fmt.format(calcUsageCharge()));
        System.out.println("Administrative Fee:   " + fmt.format(0.61));
        System.out.println("Total Charges:        " + fmt.format(calcTotalFee()));
        startNewMonth();
    }

    private void startNewMonth(){
        dataUsed = 0.0;
        numTexts = 0;
        remBattery = 0.0;

    }

    private double calcAdditionalDataFee(){
        double extraData;
        if (dataUsed > 2.0){
            extraData = Math.ceil(dataUsed - 2);
            extraData = extraData * 15;
            return extraData;
        }
        return 0.0;
    }

    private double calcUsageCharge(){
        double usageCharge;
        usageCharge = (calcAdditionalDataFee() + 50.0) * 0.03;
        return usageCharge;  
    }

    private double calcTotalFee(){
        double totalFee;
        totalFee = 50.0 + calcAdditionalDataFee() + calcUsageCharge() + 0.61;
        return totalFee;   
    }

    private String fmtPhoneNumber(){
        String str = "(" + phoneNum.substring(0,3) + ")" + phoneNum.substring(3,6) + "-" + phoneNum.substring(6,10);

        return str;
    }

}
