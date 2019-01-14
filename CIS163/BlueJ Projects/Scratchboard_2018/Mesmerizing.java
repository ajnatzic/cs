
/**
 * Write a description of class Mesmerizing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mesmerizing
{
    // instance variables - replace the example below with your own
    private String stars;

    /**
     * Constructor for objects of class Mesmerizing
     */
    public Mesmerizing()
    {
        // initialise instance variables
        stars = "*";
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void dotsMethod()
    {
        int i = 0;
        
        while (true){
           
          for(i = 0; i < 10; i++){
                stars = "*" + stars + "*";
                System.out.println(stars);
            }
    }
}

    public static void main(String args[]){
        Mesmerizing test = new Mesmerizing();
        
        test.dotsMethod();
    }
}
