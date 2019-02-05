import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/*************************************************************
 * GUI for a Baby Name Database
 * 
 * @author Scott Grissom
 * @version October 7, 2017
 ************************************************************/
public class BabyNameGUI extends JFrame implements ActionListener{

    // FIX ME: Define a BabyNameDatabase variable

    // FIX ME: Define buttons

    // FIX ME: Define text fields

    /** Results text area */
    JTextArea resultsArea;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;
    JMenuItem countItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        BabyNameGUI gui = new BabyNameGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Baby Names");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/    
    public BabyNameGUI(){

        // FIX ME: instantiate an object of type BabyNameDatbase  

        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 10 rows
        resultsArea = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 10;  
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 20;
        add(scrollPane, loc);  

        // create Results label
        loc = new GridBagConstraints();
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        loc.insets.top = 20;
        add(new JLabel("Results"), loc);

        // create Searches label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 0;
        loc.gridwidth = 2;
        add(new JLabel("Searches"), loc);     

        // FIX ME: create labels, textfields and buttons 

        // FIX ME: register listeners for the buttons

        // hide details of creating menus
        setupMenus();
    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // Allow user to load baby names from a file    
        if (buttonPressed == openItem){
            openFile();
        }  

    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            
            // FIX ME: change the following line as needed
            // to use your instance variable name
            //database.readBabyNameData(filename);          
        }
    }

    /*******************************************************
    Creates the menu items
     *******************************************************/    
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        countItem = new JMenuItem("Counts");
        openItem = new JMenuItem("Open...");
        fileMenu.add(countItem);
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // FIX ME: register the menu items with the action listener

    }
}