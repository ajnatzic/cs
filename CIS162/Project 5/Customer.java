
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    double arrivalTime = 0.0;
    
    public Customer(double time){
        arrivalTime = time;
    }
    
    public void setArrivalTime(double t){
        arrivalTime = t;
    }
    
    public double getArrivalTime(){
        return arrivalTime;
    }
    
    public static void main (String args[]){
        Customer test = new Customer(700);
        System.out.println("Should print 700.0: " + test.getArrivalTime());
        test.setArrivalTime(800);
        System.out.println("Should print 800.0: " + test.getArrivalTime());
    }
}
