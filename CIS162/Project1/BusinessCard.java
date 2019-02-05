
/**
 * An official business card for my business
 *
 * @AJ Natzic
 * @9-21-2017
 */
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
public class BusinessCard extends JPanel{
    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.setContentPane(new BusinessCard());
        f.setSize(700, 700);
        f.setVisible(true);
    }

    public void paintComponent(Graphics g){

        // this statement required
        super.paintComponent(g);

        Color.getHSBColor(0, 0, 0);

        // optional: paint the background color (default is white)
        setBackground(Color.getHSBColor(0.6f, 0.7f, 0.7f));
        int x = 0;
        int y = 0;
        // My name
        Font nameFont = new Font("Bell MT", Font.BOLD, 50);
        g.setFont(nameFont);
        g.setColor(Color.blue);
        g.drawString("AJ Natzic", x + 300, y + 300);
        // Profession
        Font profFont = new Font("Lucida Calligraphy", Font.ITALIC, 20);
        g.setFont(profFont);
        g.setColor(Color.white);
        g.drawString("Senior Graphic Designer", x + 300, y + 335);
        //Phone Number
        Font phoneFont = new Font("AppleGothic", Font.BOLD, 20);
        g.setFont(phoneFont);
        g.setColor(Color.white);
        g.drawString("1-800-275-2273", x + 300, y + 360);
        //Witty Catchphrase
        Font phraseFont = new Font("Lucida Fax", Font.BOLD, 17);
        g.setFont(phraseFont);
        g.setColor(Color.white);
        g.drawString("Learning from our own mistakes, every day.", x + 100, y + 390);
        // The card outline
        g.setColor(Color.white);
        g.drawRect(x + 75, y + 100, 500, 300);

        // Logo

        g.setColor(Color.BLUE);
        g.fillOval(x + 392, y + 180, 15, 15);
        g.fillArc (x + 350, y + 150, 100, 100, 238, 60);
        g.fillArc (x + 360, y + 130, 100, 100, 0, 60);
        g.fillArc (x + 340, y + 130, 100, 100, 120, 60);
        g.setColor(Color.getHSBColor(1f, 1f, 1f));;
        g.drawOval(x + 330, y + 115, 140, 140);

        // Image
        BufferedImage photo = null;
        try {
            File file = new File("Me.jpg");
            photo = ImageIO.read(file);
        } catch (IOException e){
            g.drawString("Problem reading the file", 100, 500);
        }
        g.drawImage(photo, x + 80, y + 150, 200, 200, null);

    }
}
