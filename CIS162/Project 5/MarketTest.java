
/**
 * A class that Tests MarketPlace
 *
 * @author AJ Natzic
 * @version 12-1-2017
 */
public class MarketTest
{
    public static void main(String args[]){
        System.out.println("Start testing...");

        // does store start with 3 cashiers?
        MarketPlace myStore = new MarketPlace();
        assert(myStore.getNumCashiers() == 3) : "Start with 3 cashiers";
        // how many customers served with default arrival time
        myStore.startSimulation();
        int before = myStore.getNumCustomersServed();

        // are parameters updated correctly?
        myStore.setParameters(2, 4, 2, false);
        assert(myStore.getNumCashiers() == 2) : "Change to 2 cashiers";
        assert(myStore.getServiceTime() == 4) : "Should be 4 avg Service Time";
        assert(myStore.getArrivalTime() == 2) : "Should be 2 avg Arrival Time";
        // how many customers served with quicker arrival times?
        myStore.startSimulation();
        int after = myStore.getNumCustomersServed();
        System.out.println(myStore.getReport());
        assert(before < after) : "Should be more customers";

        System.out.println("Testing complete.");
    }
}
