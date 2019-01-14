
import java.io.*;
// import java.util.*;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Random;

public class Mix {

    private List < Character > message;
    private String undoCommands;

    private String userMessage;
    private Scanner scan;
    
    private Hashtable clipboard;

    public Mix() {

        scan = new Scanner(System.in);
        message = new List < Character > ();
        clipboard = new Hashtable<Integer, String>();

        undoCommands = "";
    }

    public static void main(String[] args) {
        Mix mix = new Mix();

        mix.userMessage = args[0];
        mix.createMessage(mix.userMessage, mix.message);
        mix.mixture();
    }

    public void createMessage(String userMessage, List < Character > message) {
        for (char c : userMessage.toCharArray())
            message.append(c);
    }

    private void mixture() {                
        do {
            DisplayMessage();
            System.out.print("Command: ");

            // save state
            List < Character > currMessage =  new List < Character > ();
            createMessage(message.toString(), currMessage);
            String currUndoCommands = undoCommands;

            try {
                String command = scan.next("[Qbrpcxds_h]");                /** APPEND COMMANDS TO END OF STRING */

                switch (command) {
                    case "Q":
                    save(scan.next());
                    System.out.println ("Final mixed up message: \'" + message+"\'");
                    System.exit(0);
                    case "b":
                    insertbefore(scan.next(), scan.nextInt());
                    break;
                    case "r":
                    remove(scan.nextInt(), scan.nextInt());
                    break;
                    case "c":
                    copy(scan.nextInt(), scan.nextInt(), scan.nextInt());
                    break;
                    case "x":
                    cut(scan.nextInt(), scan.nextInt(), scan.nextInt());
                    break;
                    case "p":
                    paste(scan.nextInt(), scan.nextInt());
                    break;
                    /*********************************************************
                     ***************ADD 3 MORE COMMANDS BELOW********************/
                    case "d":
                    duplicate();
                    break;
                    case "s":
                    slice(scan.next());
                    break;
                    case "_":
                    removeSpaces();
                    break;
                    case "h":
                    helpPage();
                    break;
                }
                scan.nextLine();   // should flush the buffer
                System.out.println("For demostration purposes only:\n" + undoCommands);
            }
            catch (Exception e ) {
                System.out.println ("Error on input, previous state restored.");
                scan = new Scanner(System.in);  // should completely flush the buffer

                // restore state;
                undoCommands = currUndoCommands;
                message = currMessage ;
            }

        } while (true);
    }

    private void remove(int start, int stop) {
        for(int i = stop; i >= start; i--) {
            char rChar = message.remove(i);
            if (rChar == ' ')
                rChar = '~'; // to handle spaces in output.

            undoCommands = "b " + rChar + " " + i + "\n" + undoCommands; // does the opposite
        }
    }

    private void cut(int clipNum, int start, int stop) {
        String clip = "";
            
        for(int startIndex = start; startIndex <= stop; startIndex++)    {
            clip = clip + message.get(startIndex);
        }
        remove(start, stop);
        
        List<Character> newList = new List<Character>();
        createMessage(clip, newList);
        
        clipboard.put(clipNum, clip);
        System.out.println("Clipboard " + clipNum + " =>" + clipboard.get(clipNum));
    }

    private void copy(int clipNum, int start, int stop) {
        String clip = "";
        
        for(int startIndex = start; startIndex <= stop; startIndex++)    {
            clip = clip + message.get(startIndex);
        }
        
        List<Character> newList = new List<Character>();
        createMessage(clip, newList);
        
        clipboard.put(clipNum, clip);
        System.out.println("Clipboard " + clipNum + " =>" + clipboard.get(clipNum));
    }

    private void paste(int clipNum, int index) {
        insertbefore(clipboard.get(clipNum).toString(), index);
    }

    /**
     * This method duplicates the message by appending the whole message to the end (with a space)
     */
    private void duplicate(){
        String messageString = " ";
        for(int start = 0; start < message.size(); start++){
            messageString = messageString + message.get(start);
        }

        insertbefore(messageString, message.size());
        System.out.println("Message duplicated!");
    }

    /**
     * Slices either the left or right half of the string as long as message is longer than 2 characters
     */
    private void slice(String decision){ 
        int halfIndex = 0;
        halfIndex = ((message.size() - 1) / 2);

        if (message.size() >= 2)    {   // only messages longer than two characters can be sliced
            if (message.size() % 2 == 0)   { // if # of chars in message is even 
                if(decision.equalsIgnoreCase("l") || decision.equalsIgnoreCase("left"))   {
                    System.out.println("Slicing left...");
                    remove(0, halfIndex);
                }
                if(decision.equalsIgnoreCase("r") || decision.equalsIgnoreCase("right")) {
                    System.out.println("Slicing right...");
                    remove(halfIndex + 1, (message.size() - 1));
                }
            }
            else if (message.size() % 2 != 0)  { // if # of chars in message is odd
                if(decision.equalsIgnoreCase("l") || decision.equalsIgnoreCase("left"))   {
                    System.out.println("Slicing left...");
                    remove(0, halfIndex - 1);
                }
                if(decision.equalsIgnoreCase("r") || decision.equalsIgnoreCase("right")) {
                    System.out.println("Slicing right...");
                    remove(halfIndex + 1, (message.size() - 1));
                }
            }
        }   
        else    // print an error if message is less than two characters
            System.out.println("ERROR: Can't slice a message with less than 2 characters!");
    }

    /**
     * Removes all spaces in the message, pretty self explanatory 
     */
    private void removeSpaces(){
        while(message.indexOf(' ') != -1){  // while there is still spaces in the message
            for (int i = 0; i < message.size(); i++){    // search through message for space and remove it
                if(message.get(i) == ' ')
                    remove(i, i);
            }
        }
    }

    private void insertbefore(String clipBD, int index) {
        if (message.size() == 0)  // special    case
            index = -1;

        for (int i = clipBD.length() - 1; i >= 0; i--) {
            char rChar = clipBD.charAt(i);  
            if (rChar == '~')
                rChar = ' ';  // to handle spaces in output.  
            message.add (index, rChar);
            undoCommands = "r " + index + " ~ " + index  + "\n" + 
            undoCommands;   // does the opposite
        }
    }

    private void DisplayMessage() {
        System.out.print ("Message:\n");
        userMessage = message.toString();

        for (int i = 0; i < userMessage.length(); i++) 
            System.out.format ("%3d", i);
        System.out.format ("\n");
        for (char c : userMessage.toCharArray()) 
            System.out.format("%3c",c);
        System.out.format ("\n");
    }

    public void save(String filename) {

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println(undoCommands);
        out.close();
    }

    private void helpPage() {
        System.out.println("Commands:");
        System.out.println("\tQ filename    means, quit! " + " save to filename" );         
        System.out.println("\t  ~ is used for a space character" );     
        System.out.println("\t .... etc" );     
        System.out.println("\th\tmeans to show this help page");
    }

}
