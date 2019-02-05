
/**
 * Testing class of Project 2
 *
 * @author (AJ Natzic)
 * @version (10-5-2017)
 */

import javax.swing.JOptionPane;
public class myPhoneTest
{
    public static void main(String args[]){
        //First test is initalized
        System.out.println("FIRST TEST WITH AJ NATZIC... BEGIN:");
        System.out.println("--------------------------------------");
        System.out.println();

        myPhone mine = new myPhone("AJ Natzic", "1234567890");
        mine.setName("AJ Natzic");
        mine.setPhoneNumber("1234567890");
        mine.readText();
        mine.chargeBattery(120);
        if(mine.getBatteryLife() != 100){
            System.out.println("   FAILED to get correct battery");
        }
        mine.streamAudio(360);
        if (mine.getBatteryLife() != 50){
            System.out.println("   FAILED to get correct battery");
        }
        mine.chargeBattery(30);
        mine.streamAudio(530);
        mine.streamAudio(200);
        if (mine.getDataUsage() != 1.1541666666666666666){
            System.out.println("   FAILED to get correct data usage");
        }
        mine.sendText("Sent Message: Hey whats up?");
        if (mine.getNumTexts() != 1){
            System.out.println("   FAILED to get correct amount of texts");
        }

        mine.printStatement();

        System.out.println();
        System.out.println("FIRST TEST COMPLETE, REVIEW AND FIX ERRORS.");
        System.out.println();

        //Second test is initalized
        System.out.println("SECOND TEST WITH EVIL MAN... BEGIN:");
        System.out.println("-----------------------------------");
        System.out.println();
        myPhone yours = new myPhone("Evil man", "HAHAHAHAHAHAHAHAHHA");
        yours.setName("Evil man");
        yours.setPhoneNumber("HAHAHAHAHAHAHAHAHHA");
        //Should Print "(999-999-9999)"
        yours.readText();
        yours.chargeBattery(120);
        if(yours.getBatteryLife() != 100){
            System.out.println("   FAILED to get correct battery");
        }
        yours.streamAudio(1000);
        yours.chargeBattery(1200);
        yours.streamAudio(1000);
        yours.chargeBattery(120);
        yours.streamAudio(470);
        yours.getBatteryLife();
        yours.chargeBattery(-9999);
        yours.chargeBattery(120);
        yours.streamAudio(1337);
        yours.sendText("Sent Message: I WILL RULE THE WORLD");
        yours.sendText("Sent Message: I'M NOT LYING ILL DO IT >:(");
        yours.sendText("Sent Message: Oh shoot I'm messaging myself...");
        if (yours.getNumTexts() != 3){
            System.out.println("   FAILED to get correct amount of texts");
        }
        if (yours.getDataUsage() != 4.12425){
            System.out.println("   FAILED to get correct data usage");
        }
        yours.getBatteryLife();
        yours.printStatement();

        System.out.println();
        System.out.println("SECOND TEST COMPLETE, REVIEW AND FIX ERRORS.");
        System.out.println();

    } 
} 

