package lab8sort;

import java.util.*;
import java.text.*;
/**********************************************************
 * SortingMachine with static generic methods for linear
 * search, binary search, selection sort, bubble sort and
 * insertion sort.
 * 
 * @author Scott Grissom
 * @version June 20, 2017
 *********************************************************/
public class SortingMachine{

    // basic statitics for sorting algorithms
    static int comparisons, swaps, passes;


/**********************************************************
Sorting Strings Demo - Creates an array with 26 Strings
Displays the array before and after sorting for testing.
**********************************************************/
    public static void sortStringsDemo(){
        
        // fill array with 26 Strings of individual letters
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String items[] = new String[letters.length()];
        for(int i=0; i<items.length; i++){
            items[i] = Character.toString(letters.charAt(i));
        }
        
        // Display before and after for Selection Sort
        SortingMachine.shuffle(items);
        System.out.print("Selection Sort (random)\nBefore: ");
        SortingMachine.print(items);
        SortingMachine.selectionSort(items);
        System.out.print("After:  ");
        SortingMachine.print(items);
        SortingMachine.printStats();
       
        System.out.print("\nSelection Sort (sorted)\nBefore: ");
        SortingMachine.print(items);
        SortingMachine.selectionSort(items);
        System.out.print("After:  ");
        SortingMachine.print(items);
        SortingMachine.printStats();  
        
        System.out.print("\nSelection Sort (reversed)\nBefore: ");
        SortingMachine.reverse(items);
        SortingMachine.print(items);
        SortingMachine.selectionSort(items);
        System.out.print("After:  ");
        SortingMachine.print(items);
        SortingMachine.printStats();          
    }
    
/**********************************************************
Sorting Integers Demo
@param int number of items in the array
**********************************************************/
    public static void sortIntegersDemo(int SIZE){
        
        // fill array with SIZE integers
        Integer items[] = new Integer[SIZE];
        for(int i=0; i<items.length; i++){
            items[i] = i+1;
        }
        DecimalFormat fmt = new DecimalFormat("#,###");
        
        // Display results for Selection Sort
        System.out.println("Selection Sort: n = " + fmt.format(SIZE));
        SortingMachine.shuffle(items);
        System.out.print("Random order - ");
        SortingMachine.selectionSort(items);
        SortingMachine.printStats();
       
        System.out.print("Sorted order - ");
        SortingMachine.selectionSort(items);
        SortingMachine.printStats();  
        
        System.out.print("Reversed order - ");
        SortingMachine.reverse(items);
        SortingMachine.selectionSort(items);
        SortingMachine.printStats();   
        
}
    
    
    
   
    

/**********************************************************
Search Demo for linear and binary searches
@param int number of items in the array
**********************************************************/    
    public static void searchDemo(int SIZE){
        Integer nums[] = new Integer[SIZE];
        Random r = new Random();
        boolean found;
        String status;
        
        // create multiple targets for testing
        int []targets = new int[6];
        targets[0] = 1;
        targets[1] = SIZE;
        targets[2] = SIZE / 2;
        targets[3] = r.nextInt(SIZE) + 1;
        targets[4] = r.nextInt(SIZE) + 1;
        targets[5] = -1;
        
        // fill array 1 .. SIZE
        for(int i=0; i <nums.length; i++){
            nums[i] = i+1;
        }
        
        // Multiple tests for linear search
        System.out.println("Linear Search n = " + SIZE);
        for(int i=0; i<targets.length; i++){
            found = SortingMachine.linearSearch(nums,0,nums.length-1, targets[i]);
            status = found ? "found " : "did not find ";
            System.out.print(status + targets[i] + " using ");
            printStats();
        }
        
        // Multiple tests for binary search
        System.out.println("\nBinary Search n = " + SIZE);
        for(int i=0; i<targets.length; i++){
        	comparisons = 0;
        	passes = 0;
            found = SortingMachine.binarySearch(nums,0,nums.length-1, targets[i]);
            status = found ? "found " : "did not find ";
            System.out.print(status + targets[i] + " using ");
            printStats();
        }
    }

/**********************************************************
Performs linear search of provided array within the
bounds of lo and hi.
@param T[] data array to be searched
**********************************************************/    
    public static <T> boolean linearSearch(T[] data, int lo, int hi, T target){
        boolean found = false;
        comparisons = 0;
        passes = 1;
        for(int index = lo; index <= hi && !found; index++){
            comparisons++;
        	if (data[index].equals(target)){
        		found = data[index].equals(target);
        		return found;
        	}
        }
        return found;
    }
    
/**********************************************************
Recursive Binary Search
**********************************************************/    
public static boolean binarySearch(Integer[]nums, int lo, int high, int target){
		comparisons++;
		passes++;
    	if(lo > high){
    		return false;
    	}
    	
    	int mid = (lo + high) / 2;
    	
    	if(lo == high && nums[mid] != target){
    		return false;
    	}
    	
    	if(nums[mid] == target){
    		return true;
    	}
    	
    	if(high - lo == 1){
    		if (nums[lo] == target || nums[high] == target){
    			return true;
    		}
    		return false;
    	}
    	
    	if(nums[mid] < target){
    		return binarySearch(nums, mid, high, target);
    	}

    	else {
    		return binarySearch(nums, lo, mid, target);
    	}
    }

/**********************************************************
Bubble Sort - static and generic
@param data array of <T> to be sorted
**********************************************************/    
    public static <T extends Comparable<T>> void bubbleSort(T[] data){
        clearStats();
        for(int pass = data.length-1; pass > 0; pass--){
            passes++;
            for(int i=0; i<=pass; i++){
                comparisons++;
                if(data[i].compareTo(data[i+1]) > 0){
                    swap(data,i,i+1);
                }
            }
        }   
    } 

/**********************************************************
Insertion Sort - static and generic
@param data array of <T> to be sorted
**********************************************************/    
    public int[] insertionSort(int[] nums){
    	for(int i=1; i< nums.length; i++){
    		for(int j= (i-1); j>=0; j--){
    			if(nums[j]< nums[j+1]){
    				int x = nums[j];
    				nums[j] = nums[j+1];
    				nums[j+1] = x;
    			}
    				
    		}
    	}
    	return nums;
    }

/**********************************************************
Selection Sort - static and generic
@param data array of <T> to be sorted
**********************************************************/    
    public static <T extends Comparable<T>> void selectionSort(T[] data){
        clearStats();
        for(int pass = 0; pass < data.length-1; pass++){
            int minIndex = pass;
            passes++;
            for(int i=pass+1; i<data.length; i++){
                comparisons++;
                if(data[minIndex].compareTo(data[i]) > 0){
                    minIndex = i;
                }
            }
            comparisons++;
            swap(data, pass, minIndex);
        }   
    }   
    
/**********************************************************
Swap two items within an array given two indices
@param data array of items
@param left index of one item
@param right index of second item
**********************************************************/    
    private static <T> void swap(T[] data, int left, int right){
        T temp = data[left];
        data[left] = data[right];
        data[right] = temp;
        swaps++;
    }
    
/**********************************************************
Prints items in the provided array
@param items array to be displayed
**********************************************************/    
    private static <T> void print(T[] items){
        for(int i=0; i<items.length; i++){
            System.out.print(items[i] +"  ");
        }
        System.out.println();
    }
    
/**********************************************************
Shuffle contents of an array
@param data array to be shuffled
**********************************************************/    
    private static <T> void shuffle(T[] data){
        Random r = new Random();
        for(int i=0; i<data.length*2; i++)
            swap(data, r.nextInt(data.length), r.nextInt(data.length));
    } 

/**********************************************************
Reverse contents of the provided array
**********************************************************/     
    private static <T> void reverse(T[] data){
        for(int i=0; i<data.length/2; i++)
            swap(data, i, data.length - i - 1);
    }  
    
/**********************************************************
Clear statistics: passes, comparisons and swaps
**********************************************************/     
    private static void clearStats(){
        swaps = 0;
        passes = 0;
        comparisons = 0;
    }
    
/**********************************************************
Print current statistics: passes, comparisons and swaps
**********************************************************/    
    private static void printStats(){
        DecimalFormat fmt = new DecimalFormat("#,###");
        System.out.println("Passes: " + fmt.format(passes) + 
                " Comparisons: " + fmt.format(comparisons) + " Swaps: " + fmt.format(swaps)); 
    }
     


    public static void main(String[] args){
    	searchDemo(1000000);
    }
}

