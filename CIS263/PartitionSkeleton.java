import java.util.*;
import java.util.stream.*;

/*
 * CIS263 - Winter 2019
 * This is a skeleton for the solution of homework 4
 * You will write a program that will evaluate if
 * there a particular instance of the partition problem (NP-hard)
 * has a solution or not.
 */

public class PartitionSkeleton {
    // This array will contain the
    // elements in the collection to be partitioned
    private static int arrayOfWeights[];

    // This booleand method will return true
    // if the sum of the elements in partition 0 (part0) is the same
    // as the sum of the elements in partition 1 (part1)
    
    private static boolean evaluate(Set <Integer> part1,
				    Set <Integer> part0) {
	
	// I learned how to sum all Integers in a set here: https://www.javaquery.com/2016/10/how-to-sum-values-from-list-set-and-map.html
    	Integer integerSum1 = part1.stream().mapToInt(Integer::intValue).sum();
	Integer integerSum0 = part0.stream().mapToInt(Integer::intValue).sum();
	
	// If the sums are the same return true
	if(integerSum1.equals(integerSum0)){
		return true;
	}
	// Otherwise, return false
	else{
		return false;
	}
    }


    public static void main(String args[]) {
	// Read from the standard input
	// This program can be used with input
	// redirection
	Scanner scanner = new Scanner(System.in);
	// Scanner the size of the collection 
	int n = scanner.nextInt();
	// Read the individual elements in the collection
	// They are stored in the static array arrayOfWeights
	arrayOfWeights = new int [n];
	for(int i = 0;i < n;i++) {
	    arrayOfWeights[i] = scanner.nextInt();
	}
	// allElements contains all the possible indices
	// in the collection
	TreeSet < Integer > allElements = new TreeSet < Integer > ();
	for(int i = 0;i < n;i++) {
	    allElements.add(i);
	}
	// The PowerSet class will be used to generate
	// all the possible subsets of the set allElements
	PowerSet ps = new PowerSet(n);
	boolean solutionFound = false;
	TreeSet < Integer > solution1s = new TreeSet< Integer > ();
	TreeSet < Integer > solution0s = new TreeSet< Integer > ();
	// Go throught all the elements in the PowerSet
	// Evaluate each of them
	// If one happens to be a solution (the solution might not be unique)
	// then that solutions is reported immediately
	while(ps.hasNext()) {
	    TreeSet < Integer > elements1 = ps.next();
	    TreeSet < Integer > elements0 =
		new TreeSet < Integer > ( allElements );
	    elements0.removeAll(elements1);
	    if (evaluate(elements1,elements0)) {
		solutionFound = true;
		solution1s.addAll(elements1);
		solution0s.addAll(elements0);
		break;
	    }
	}
	if (solutionFound) {
	    System.out.println("A solution was found: "+
			       solution1s.toString()+" "+
			       solution0s.toString());
	}
	else {
	    System.out.println("No solution was found.");
	}
	    
    }
}
