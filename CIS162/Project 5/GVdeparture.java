
/**
 * Write a description of class GVdeparture here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GVdeparture extends GVevent
{
    public GVdeparture(MarketPlace store, double time, int id){
        super(store, time, id);
    }
    
    public void process(){
        store.customerPays(cashier);
    }
}
