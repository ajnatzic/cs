import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

/*************************************************************
 * GUI for a Market Simulation
 * 
 * @author Scott Grissom
 * @version October 7, 2017
 ************************************************************/
public class MarketGUI extends JFrame implements ActionListener{

    MarketPlace market;

    JButton simulate;

    JTextField cashiersField, arrivalField, serviceField;

    JCheckBox display;

    /** Results text area */
    JTextArea resultsArea;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;
    JMenuItem clearItem;

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        MarketGUI gui = new MarketGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Market Simulator");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/    
    public MarketGUI(){

        
        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area 
        resultsArea = new JTextArea(20,30);
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

        // create Paramaters label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 0;
        loc.gridwidth = 2;
        add(new JLabel("Paramaters"), loc);     

        // cashiers label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 1;
        add(new JLabel("Cashiers:"), loc);

        // cashiers textfield
        cashiersField = new JTextField(4);
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 1;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        loc.insets.left = 10;
        loc.insets.right = 10;
        add(cashiersField, loc);

        // arrival time label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 2;
        add(new JLabel("Arrival Time:"), loc);

        // arrival time textfield
        arrivalField = new JTextField(4);
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 2;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        loc.insets.left = 10;
        loc.insets.right = 10;
        add(arrivalField, loc);

        // service time label
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 3;
        add(new JLabel("Service Time:"), loc);

        // service time textfield
        serviceField = new JTextField(4);
        loc = new GridBagConstraints();
        loc.gridx = 2;
        loc.gridy = 3;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        loc.insets.left = 10;
        loc.insets.right = 10;
        add(serviceField, loc);

        // checkout display checkbox
        display = new JCheckBox("Display");
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 4;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        loc.insets.left = 15;
        loc.insets.right = 15;
        add(display, loc);
        display.addActionListener(this);
        
        // simulate button
        simulate = new JButton("Simulate");
        loc = new GridBagConstraints();
        loc.gridx = 1;
        loc.gridy = 5;
        loc.insets.bottom = 5;
        loc.insets.top = 5;
        loc.insets.left = 15;
        loc.insets.right = 15;
        add(simulate, loc);
        simulate.addActionListener(this);

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
        
        MarketPlace market = new MarketPlace();
        
        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // Various Button presses  
        if(buttonPressed == quitItem){
            System.exit(1);
        }
        else if(buttonPressed == simulate){
            String cashiers = cashiersField.getText();

            String service = serviceField.getText();
            
            String arrival = arrivalField.getText();
            
            // checks to see if numbers entered are valid
            if(isValidNumber(cashiers) == false){
                JOptionPane.showMessageDialog(null, "Enter valid number of cashiers", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(isValidNumber(arrival) == false){
                JOptionPane.showMessageDialog(null, "Enter valid arrival time", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(isValidNumber(service) == false){
                JOptionPane.showMessageDialog(null, "Enter valid service time", "Error", JOptionPane.ERROR_MESSAGE);
            }
            // if all tests passed then the simulation starts
            else{
                int c = Integer.parseInt(cashiers);
                double s = Double.parseDouble(service);
                double a = Double.parseDouble(arrival);
                
                market.setParameters(c, s, a, display.isSelected());
                market.startSimulation();
                resultsArea.append(market.getReport());
            }
        }
        else if(buttonPressed == clearItem){
            resultsArea.setText("");
        }

    }

    private boolean isValidNumber(String str){
        boolean isValid = true;

        // see if str contains a valid double
        try{
            Double.parseDouble(str);

            // there must have been an error converting to double
        }catch (Exception e){
            isValid = false;
        }
        return isValid;
    }

    /*******************************************************
    Creates the menu items
     *******************************************************/    
    private void setupMenus(){
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        clearItem = new JMenuItem("Clear Results");
        fileMenu.add(clearItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        quitItem.addActionListener(this);
        clearItem.addActionListener(this);
        

    }
}