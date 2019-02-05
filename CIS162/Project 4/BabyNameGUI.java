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

    BabyNamesDatabase data;

    JButton yearButton, mostPopButton, topTenButton, nameButton;

    JTextField yearField, nameField;

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

        data = new BabyNamesDatabase();

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

        // year label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 1;
        add(new JLabel("Year"), loc);

        // year textfield
        yearField = new JTextField(4);
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 1;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        add(yearField, loc);

        // year button
        yearButton = new JButton("By Year");
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 2;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        add(yearButton, loc);
        yearButton.addActionListener(this);

        // most popular button
        mostPopButton = new JButton("Most Popular");
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 3;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        add(mostPopButton, loc);
        mostPopButton.addActionListener(this);

        // top ten button
        topTenButton = new JButton("Top Ten");
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 4;
        loc.insets.bottom = 50;
        loc.insets.top = 5;
        add(topTenButton, loc);
        topTenButton.addActionListener(this);

        // name label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 6;
        add(new JLabel("Name"), loc);

        // name field
        nameField = new JTextField(10);
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 6;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        add(nameField, loc);

        //name button
        nameButton = new JButton("By Name");
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 7;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        add(nameButton, loc);
        nameButton.addActionListener(this);

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
        else if(buttonPressed == quitItem){
            System.exit(1);
        }
        else if(data.countAllNames() == 0){
            if(buttonPressed != openItem && buttonPressed != quitItem){
                JOptionPane.showMessageDialog(this, "No file found");
            }
        }
        else if(buttonPressed == mostPopButton){
            if(yearField.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Provide a Year");
            }else{
                displayMostPopular();
            }
        }
        else if(buttonPressed == yearButton){
            if(yearField.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Provide a Year");
            }else{
                displayByYear();
            }
        }
        else if(buttonPressed == topTenButton){
            if(yearField.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Provide a Year");
            }else{
                displayTopTen();
            }
        }

        else if(buttonPressed == nameButton){
            if(yearField.getText().length() == 0){
                JOptionPane.showMessageDialog(this, "Provide a Name");
            }else{
                displayByName();
            }
        }

        else if(buttonPressed == countItem){
            displayCounts();
        }

    }

    private void displayNames(ArrayList<BabyName> list){
        int i = 0;
        for(BabyName b: list){
            resultsArea.append("\n" + b.toString());
            ++i;
        }
        resultsArea.append("\n");
        resultsArea.append("\nTotal: " + NumberFormat.getNumberInstance(Locale.US).format(i));
    }

    private void displayMostPopular(){
        int year = 0;
        year = Integer.parseInt(yearField.getText());
        resultsArea.setText("Most Popular Names in " + year);
        resultsArea.append("\n");
        resultsArea.append("\n" + data.mostPopularBoy(year));
        resultsArea.append("\n" + data.mostPopularGirl(year));
    }

    private void displayByYear(){
        resultsArea.setText("");
        int year = 0;
        year = Integer.parseInt(yearField.getText());
        displayNames(data.searchForYear(year));
        resultsArea.append("\nAll Names in " + year);
    }

    private void displayTopTen(){
        int year = 0;
        year = Integer.parseInt(yearField.getText());
        resultsArea.setText("Top Ten Baby Names in " + year);
        resultsArea.append("\n");
        displayNames(data.topTenNames(year));
    }

    private void displayByName(){
        resultsArea.setText("");
        String name = "";
        name = nameField.getText();
        displayNames(data.searchForName(name));
        resultsArea.append("\nAll years with " + name);
    }

    private void displayCounts(){
        resultsArea.setText("Total Counts");
        resultsArea.append("\n");
        data.countAllNames();
        resultsArea.append("\nTotal Girls: " + NumberFormat.getNumberInstance(Locale.US).format(data.countAllGirls()));
        resultsArea.append("\nTotal Boys: " + NumberFormat.getNumberInstance(Locale.US).format(data.countAllBoys()));
        resultsArea.append("\nTotal Names: " + NumberFormat.getNumberInstance(Locale.US).format(data.countAllNames()));
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
            data.readBabyNameData(filename);          
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

        quitItem.addActionListener(this);
        countItem.addActionListener(this);
        openItem.addActionListener(this);

    }
}