
/**
 * My SUV
 *
 * @AJ Natzic
 * @version 0.0.0.1
 */
import javax.swing.*;
import java.awt.*;
public class Drawing extends JPanel{
 public static void main(String[] a) {
 JFrame f = new JFrame();
 f.setContentPane(new Drawing());
 f.setSize(600, 400);
 f.setVisible(true);
 }
 public void paintComponent(Graphics g){
 // this statement required
 super.paintComponent(g);

 // optional: paint the background color (default is white)
 setBackground(Color.CYAN);

 // display words
 g.setColor(Color.black);
 g.drawString("AJ's SUV", 50, 20);
 // The Ground
 g.setColor(Color.GRAY);
 g.fillRect(0, 400, 6000, 4000);
 //The Sun
 g.setColor(Color.YELLOW);
 g.fillOval(750, 100, 100, 100);
 
 int X = 0;
 //The Car****
 g.setColor(Color.RED);
 g.fillRect(X + 300, 300, 400, 150);
 g.fillRect(X + 400, 200, 200, 100); 
 //Windows
 g.setColor(Color.WHITE);
 g.fillRect(X + 410, 210, 75, 75);
 g.fillRect(X + 515, 210, 75, 75);
 //Wheels
 g.setColor(Color.BLACK);
 g.fillOval(X + 550, 400, 100, 100);
 g.fillOval(X + 350, 400, 100, 100);
 

 
}
}
