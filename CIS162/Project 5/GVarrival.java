
/**
 * Write a description of class GVarrival here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GVarrival extends GVevent
{
    public GVarrival(MarketPlace store, double time){
        super(store, time);
    }
    
    public void process(){
        store.customerGetsInLine();
    }
}
