import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnagramFinder {
	
	
	/**************************************************************/
	/* Method: Main */
	/* Purpose: Take a file path as input and run the FindAnagrams method. */
	/* Parameters: */
	/* Scanner sc: Scanner that takes user input from the terminal and saves it
	/* to filepath */
	/* String filepath: path of the words.txt file */
	/* Returns: void */
	/**************************************************************/
    public static void main(String[] args) {
    	/* Creates a scanner object and uses it as filepath for the word file */
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Input a filepath eg C:\\Downloads\\words.txt");
    	String filepath = sc.nextLine();
    	findAnagrams(filepath);
    	sc.close();
    }

    /**************************************************************/
    /* Method: Find Anagrams */
    /* Purpose: Find all the anagrams in a file */
    /* Parameters: */
    /* String filepath: filepath of the list of words to find anagrams in */
    /* Map anagramMap: Creates a hashmap to process the file. */
    /* The key-value pair is the original word and the word */
    /* with the letters sorted alphabetically */
    /* scanner sc: scanner object for the filepath of the input file */
    /* eg dog:dgo */
    /* string word: Converts all words to lowercase */
    /* char[] chars: Passes the lowercase words into a char array to be */
    /* presorted by quicksort*/
    /* String sortedWord: The word sorted in alphabetical order */
    /* eg dog --> dgo */
    /* List<String> anagramList: ArrayList of all the words that, */
    /* when sorted alphabetically, match the sortedWord */
    /* which is the definition of an anagram */
    /* Returns: Prints all the sets of anagrams to terminal */
    /**************************************************************/
public static void findAnagrams(String filename)
{
	final String filepath = filename;
    /* Creates a hashmap where the key will be the sorted String from the input 
    /* and the val is the list of all strings that  match that sorted string */
	Map<String, List<String>> anagramMap = new HashMap<>();
    
	/* This subroutine is how the anagrams are found in a step process.
	 * Step 1: Take in each word, ignoring capitilization and pass it to a 
	 * character array 
	 * Step 2: Presort each word in the char array, so that it is rearranged 
	 * in alphabetical order
	 * Step 3: Save the sorted word as the key of the hashmap 
	 * Step 4: Process every word that matches the sorted word and save the set
	 * of words as the value of the hashmap as a List<String>*/
	
	/* First gets the filepath of the file and processes it */
	try (Scanner scanner = new Scanner(new File(filepath))) {
        while (scanner.hasNextLine()) {
        	/* Passes the current word into the charArray, and sorts it */
            String word = scanner.nextLine().toLowerCase();
            char[] chars = word.toCharArray();
            quicksort(chars, 0, chars.length-1);
            
            /*First checks if the word has been added already, then adds it 
             * as the key of the map */
            String sortedWord = new String(chars);
            if (!anagramMap.containsKey(sortedWord)) {
                anagramMap.put(sortedWord, new ArrayList<>());
            }
            /* Adds the word to the array list of words 
             * that match this sortedWord */
            anagramMap.get(sortedWord).add(word);
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filepath);
        return;
    }

	
	/* If there is more than one word
	 * i.e it isn't a single word set of anagrams, then print the set of vals */
    for (List<String> anagramList : anagramMap.values()) {
        if (anagramList.size() > 1) {
            System.out.println(anagramList);
        }
    }
 }


/**************************************************************/
/* Method: quicksort */
/* Purpose: As input takes a word from the file list*/
/* and then sorts a char array of all the chars in the file */
/* Uses recursion to repeatedly change the left and right indexes*/
/* This algorithm repeatedly creates a pivot index and sorts the algorithm */
/* All values to the left of the pivot must be less than it, */
/*  and the values to the right greater */
/* Parameters: */
/* char arr: The array to be sorted */
/* int left: The left value to be compared */
/* int right: The rightmost value to be compared
/* Returns: The sorted char array*/
/**************************************************************/
public static void quicksort(char[] arr, int left, int right) {
    if (left >= right) {
        return;
    }
    int pivotIndex = partition(arr, left, right);
    quicksort(arr, left, pivotIndex - 1);
    quicksort(arr, pivotIndex + 1, right);
}
/**************************************************************/
/* Method: partition */
/* Purpose: Creates partitions for quicksort around the pivot element */
/* This method is called recursively by quicksort
/*  in order to continuously find pivot */
/* elements of subarrays until they are fully sorted */
/* Parameters: */
/* char[] arr: The array to be partitioned */
/* int left: the leftmost value of the subarray */
/* int right: the rightmost value of the subarray */
/* Returns: int: The pivot index */
/**************************************************************/
private static int partition(char[] arr, int left, int right) {
    char pivotValue = arr[right];
    int i = left - 1;
    for (int j = left; j < right; j++) {
        if (arr[j] < pivotValue) {
            i++;
            swap(arr, i, j);

        }
    }
    swap(arr, i + 1, right);
    return i + 1;
}
/**************************************************************/
/* Method: swap */
/* Purpose: swap elements of an array. This is used by quicksort to swap the */
/* pivot until */
/* All elements to the left are smaller */
/*  and all elements to the right are greater */
/* Parameters: */
/* char[] arr: The array where the elements are located */
/* int i: The first/left element */
/* int j: The second/right element */
/* char temp: stores the value of the first element */
/*  so it can be swapped after arr[i] is overwritten */
/* Returns: The array remains the same besides for the two swapped elements */
/**************************************************************/
private static void swap(char[] arr, int i, int j) {
    char temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}



}
