
/**
 * An example of recursion with factorial (e.g. 4! or 3!)
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Factorial
{
    public static int factorial(int p){
        if (p == 0 || p == 1)  {
            return 1;
        }
        else    {
            return p * factorial(p - 1);
        }
    }

    public static void main (String args[]) {
        int result = factorial(4);

        System.out.println(result);
    }
}
