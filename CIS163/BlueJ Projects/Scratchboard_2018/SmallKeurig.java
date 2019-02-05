
/**
 * Write a description of class SmallKeurig here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SmallKeurig implements CoffeeService
{
    public void serve(){
        System.out.println("Small Keurig Machine serving coffee.");
    }
    
    public void serveEspresso(){
        System.out.println("Keurig Machine serving espresso");
    }
}
