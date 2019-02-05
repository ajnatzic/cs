import java.util.*;
/**
 * Write a description of class GVdate here.
 *
 * @author (AJ NATZIC)
 * @version (11-29-2017)
 */
public class GVdate
{
    private int month = 0;
    private int day = 0;
    private int year = 0;
    private final int birthMonth = 0;
    private final int birthDay = 0;

    public GVdate(int m, int d, int y){
        month = m;
        day = d;
        year = y;
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public GVdate(GVdate d){
        //FIXME
    }

    public void setDate(int m, int d, int y){
        month = m;
        day = d;
        year = y;
    }

    public boolean isMyBirthday(){
        if(month == 2 && day == 14){
            return true;
        }
        else{
            return false;
        }
    }

    public void nextDay(){
        //if month is 31 days
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            ++day;
            if(day > 31){
                day = day - 31;
                ++month;
            }
        }
        //if month is 30 days
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            ++day;
            if(day > 30){
                day = day - 30;
                ++month;
            }
        }
        //if month is 28 or 29 (Feburary)
        else if(month == 2){
            ++day;
            if(isLeapYear() == false){
                if(day > 28){
                    day = day - 28;
                    ++month;
                }
            }
            else if(isLeapYear() == true){
                if(day > 29){
                    day = day - 29;
                    ++month;
                }
            }
        }

        else if(month > 12){
            month = month - 12;
            ++year;
        }
    }

    public void skipAhead(int days){
        int i = 0;
        for(i = 0; i < days; ++i){
            nextDay();
        }
    }

    public String toString(){
        String textMonth = "";
        //sets the int month to a text format
        switch(month){
            case 1:
            textMonth = "Janurary";
            break;

            case 2: 
            textMonth = "Feburary";
            break;

            case 3:
            textMonth = "March";
            break;

            case 4:
            textMonth = "April";
            break;

            case 5:
            textMonth = "May";
            break;

            case 6:
            textMonth = "June";
            break;

            case 7:
            textMonth = "July";
            break;

            case 8:
            textMonth = "August";
            break;

            case 9:
            textMonth = "September";
            break;

            case 10:
            textMonth = "October";
            break;

            case 11:
            textMonth = "November";
            break;

            case 12:
            textMonth = "December";
            break;

            default:
            textMonth = "Unknown";
            break;
        }

        String toString = "";
        toString = textMonth + " " + day + ", " + year;

        return toString;
    }

    public boolean isLeapYear(){
        int i = 0;
        int j = 0;
        double yearDouble = 0.0;
        //test for leap year
        for(i = 0; i < year; i += 4){
            if(i == year){
                yearDouble = (double)year / 400.0;
                //tests if its multiple of 400 (TRUE)
                for(j = 0; j < yearDouble; ++j){
                    if(j == yearDouble){
                        return true;
                    }
                }
                yearDouble = (double)year / 100.0;
                //tests if its multiple of 100 (FALSE)
                for(j = 0; j < yearDouble; ++j){
                    if(j == yearDouble){
                        return false;
                    }
                }
            }
            else if(i == year){
                return true;
            }
        }
        return false;
    }

    public static void main(String [] args){
        int month = 0;
        int day = 0;
        int year = 0;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Lab Exam for AJ Natzic");
        System.out.println("Enter Date: mm dd yyyy: ");
        month = scnr.nextInt();
        day = scnr.nextInt();
        year = scnr.nextInt();
        GVdate test = new GVdate(month, day, year);
        System.out.println("");
        System.out.println("Today: " + test.toString());
        if(test.isMyBirthday() == true){
            System.out.println(test.toString() + " is my Birthday!");
        }
        else{
            System.out.println(test.toString() + " is not my Birthday :-(");
        }
        test.nextDay();
        System.out.println("Tomorrow: " + test.toString());
        test.nextDay();
        System.out.println("The Next Day: " + test.toString());
        int i = 0;
        for(i = 0; i < 100; ++i){
            test.nextDay();
        }
        System.out.println("100 Days Later: " + test.toString());
        System.out.println("");
        System.out.println("And the next ten days are");
        for(i = 0; i < 10; ++i){
            test.nextDay();
            System.out.println(test.toString());
        }
        System.out.println("");
        if(test.isLeapYear() == false){
            System.out.println(year + " is not a leap year");
        }
        else if(test.isLeapYear() == true){
            System.out.println(year + " is a leap year");
        }
    }
}