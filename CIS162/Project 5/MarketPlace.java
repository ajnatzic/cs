import java.util.*;
import java.math.*;
import java.io.*;

/**
 * Simulates the marketplace using other classes
 *
 * @author AJ Natzic
 * @version 11-30-2017
 */
public class MarketPlace
{
    private final int OPEN_TIME = 600;
    private final int CLOSE_TIME = 1080;
    private double avgArrivalTime = 0.0;
    private double avgServiceTime = 0.0;
    private int numCashiers = 0;
    private boolean checkoutArea = false;
    private double currTime = 0.0;
    private int numCustomersServed = 0;
    private int longestLine = 0;
    private double longestLineTime = 0.0;
    private double avgWaitTime = 0.0;
    private double totalWaitTime = 0.0;

    private ArrayList<Customer> theLine;
    private PriorityQueue<GVevent> futureEvents;
    private Customer[] cashiers;
    private GVrandom randomTime;
    private String results = "";
    //NOTE: additional variables may be needed
    public MarketPlace(){
        avgArrivalTime = 2.5;
        avgServiceTime = 6.6;
        numCashiers = 3;
        checkoutArea = false;
        results = "";
        PriorityQueue futureEvents = new PriorityQueue<GVevent>();
        GVrandom randomTime = new GVrandom();
        Customer ArrayList = new Customer(numCustomersServed);
        Customer[] cashiers = new Customer[numCashiers];
        currTime = 0.0;
        numCustomersServed = 0;
        longestLine = 0;
        avgWaitTime = 0.0;
        totalWaitTime = 0.0;
    }

    public int getNumCashiers(){
        return numCashiers;
    }

    public double getArrivalTime(){
        return avgArrivalTime;
    }

    public double getServiceTime(){
        return avgServiceTime;
    }

    public int getNumCustomersServed(){
        return numCustomersServed;
    }

    public String getReport(){
        return results;
    }

    public int getLongestLineLength(){
        return longestLine;
    }

    public double getAverageWaitTime(){
        return avgWaitTime;
        //FIXME
    }

    public void setParameters(int num, double s, double a, boolean ck){
        numCashiers = num;
        avgServiceTime = s;
        avgArrivalTime = a;
        checkoutArea = ck;
    }
    //**************PART 2******************
    private void reset(){
        results = "";
        GVevent PriorityQueue;
        GVrandom randomTime;
        Customer ArrayList;
        Customer cashiers;
        currTime = 0.0;
        numCustomersServed = 0;
        longestLine = 0;
        avgWaitTime = 0.0;
        totalWaitTime = 0.0;
        //May need fixing
    }

    private int cashierAvailable(){
        Customer[] cashiers = new Customer[numCashiers];
        int i = 0;
        for(i = 0; i < cashiers.length; ++i){
            if(cashiers[i] == null){
                return i;
            }
        }
        return -1;
    }

    private double randomFutureTime(double avg){
        GVrandom rand = new GVrandom();
        double future = 0.0;
        double now = currTime;
        future = now + rand.nextPoisson(avg);

        return future;
    }

    private void customerToCashier(int num){
        ArrayList<Customer> theLine = new ArrayList<Customer>();
        PriorityQueue futureEvents = new PriorityQueue<GVevent>();
        Customer c = theLine.remove(0);
        cashiers[num] = c;

        ++numCustomersServed;
        totalWaitTime += currTime - c.getArrivalTime();

        double next = randomFutureTime(avgServiceTime);
        GVdeparture d = new GVdeparture(this, next, num);

        futureEvents.add(d);
    }

    public void customerGetsInLine(){
        PriorityQueue futureEvents = new PriorityQueue<GVevent>();
        theLine = new ArrayList<Customer>();
        Customer c = new Customer(currTime);
        GVarrival a = new GVarrival(this, currTime);
        if(theLine.size() > longestLine){
            longestLine = theLine.size();
            longestLineTime = currTime;
        }

        if(cashierAvailable() != -1){
            int i = 0;
            for(i = 0; i < theLine.size(); ++i){
                customerToCashier(i);
            }
        }

        if(currTime < CLOSE_TIME){
            futureEvents.add(a);
        }
    }

    public void customerPays(int num){
        int i = 0;
        for(i = 0; i < theLine.size(); ++i){
            customerToCashier(num);
        }
        //May need fix
    }

    public void startSimulation(){
        //FIXME
        reset();
        GVarrival a = new GVarrival(this, OPEN_TIME);
        futureEvents = new PriorityQueue();
        futureEvents.add(a);
        while(!futureEvents.isEmpty()){
            GVevent e = futureEvents.poll();
            currTime = e.getTime();
            e.process();
            if(checkoutArea == true){
                showCheckoutArea();
            }
        }
        createReport();
    }

    private void showCheckoutArea(){
        Customer[] cashiers = new Customer[numCashiers];
        String custStr = "";
        String cashStr = "";

        for(int i = 0; i < theLine.size(); ++i){
            custStr += "*";
        }

        for(int i = 0; i < cashiers.length; ++i){
            if(cashiers[i] == null){
                cashStr += "_";
            }
            else{
                cashStr += "C";
            }
        }

        results += "\n" + formatTime(currTime) + " " + cashStr + " " + custStr;
    }

    private void createReport(){
        results += "\n\n" + "SIMULATION PARAMETERS"; 
        results += "\n" + "Number of cashiers: " + numCashiers;
        results += "\n" + "Average arrival: " + avgArrivalTime;
        results += "\n" + "Average service: " + avgServiceTime;

        results += "\n\n" + "RESULTS";
        results += "\n" + "Average wait time: " + (int)avgWaitTime + " mins";
        results += "\n" + "Max line length: " + longestLine + " at " + formatTime(longestLineTime);
        results += "\n" + "Customers served: " + numCustomersServed;
        results += "\n" + "Last departure: "; //FIXME: NEEDS TIME
    }

    public String formatTime(double mins){
        String formatTime = "";
        int hoursFmt = 0;
        int minutesFmt = 0;
        String afterTime = "";

        //First if statement for morning, second is for afternoon
        if(mins < 720 && mins >= 0){
            mins = mins / 60.0;
            hoursFmt = (int)Math.floor(mins);

            mins = (double)mins - hoursFmt;
            minutesFmt = (int)Math.round((double)mins * (int)60);

            afterTime = "am";
        }
        else if(mins < 1440 && mins >= 720){
            mins = (mins - 720) / 60.0; 
            hoursFmt = (int)Math.floor(mins);

            mins = (double)mins - hoursFmt;
            minutesFmt = (int)Math.round((double)mins * (int)60);

            afterTime = "pm";
        } 

        //if 0 hours then change to 12 for accuracy
        if(hoursFmt == 0){
            hoursFmt = 12;
        }

        //Formats time. If minutes are <0 another 0 will be added
        if(minutesFmt < 10){
            formatTime = hoursFmt + ":0" + minutesFmt + afterTime;
        }
        else{
            formatTime = hoursFmt + ":" + minutesFmt + afterTime;
        }
        return formatTime;
    }
}
