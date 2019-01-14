
public class Example
{
    public static void sampleMethod(CoffeeService c) {
        c.serve();
        System.out.println("");
        c.serveEspresso();
        System.out.println("");
        System.out.println("");
    }

    public static void main(String args[]) {
        Keurig k = new Keurig();
        sampleMethod(k);
        Nespresso n = new Nespresso();
        // sampleMethod(n);
        SmallKeurig sk = new SmallKeurig();
        sampleMethod(sk);

        System.out.println("");

    }
}
