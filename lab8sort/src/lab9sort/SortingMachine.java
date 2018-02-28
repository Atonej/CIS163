package lab9sort;

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

    public static void main(String[] args){
        sortIntegersDemo(100000);
    }
    
    /**********************************************************
    Sorting Strings Demo - Creates an array with 26 Strings
    Displays the array before and after sorting for testing.
     **********************************************************/
    public static void sortStringsDemo(){

        // fill array with 26 Strings of individual letters
        String letters = "abcdefghijklmnopqrstuvwxyz";
        //String letters = "abc";
        String items[] = new String[letters.length()];
        for(int i=0; i<items.length; i++){
            items[i] = Character.toString(letters.charAt(i));
        }

        // Display before and after for Selection Sort
        SortingMachine.shuffle(items);
        System.out.print("Quick Sort (random)\nBefore: ");
        SortingMachine.print(items);
        SortingMachine.quickSort(items);
        System.out.print("After:  ");
        SortingMachine.print(items);
        SortingMachine.printStats();

        System.out.print("\nQuick Sort (sorted)\nBefore: ");
        SortingMachine.print(items);
        SortingMachine.quickSort(items);
        System.out.print("After:  ");
        SortingMachine.print(items);
        SortingMachine.printStats();  

        System.out.print("\nQuick Sort (reversed)\nBefore: ");
        SortingMachine.reverse(items);
        SortingMachine.print(items);
        SortingMachine.quickSort(items);
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

        // Display results for Bubble Sort
        System.out.println("\nBubble Sort: n = " + fmt.format(SIZE));
        SortingMachine.shuffle(items);
        System.out.print("Random order - ");
        SortingMachine.bubbleSort(items);
        SortingMachine.printStats();

        System.out.print("Sorted order - ");
        SortingMachine.bubbleSort(items);
        SortingMachine.printStats();  

        System.out.print("Reversed order - ");
        SortingMachine.reverse(items);
        SortingMachine.bubbleSort(items);
        SortingMachine.printStats(); 

        // Display results for Insertion Sort
        System.out.println("\nInsertion Sort: n = " + fmt.format(SIZE));
        SortingMachine.shuffle(items);
        System.out.print("Random order - ");
        SortingMachine.insertionSort(items);
        SortingMachine.printStats();

        System.out.print("Sorted order - ");
        SortingMachine.insertionSort(items);
        SortingMachine.printStats();  

        System.out.print("Reversed order - ");
        SortingMachine.reverse(items);
        SortingMachine.insertionSort(items);
        SortingMachine.printStats();  

        // Display results for Merge Sort
        System.out.println("\nMerge Sort: n = " + fmt.format(SIZE));
        SortingMachine.shuffle(items);
        System.out.print("Random order - ");
        SortingMachine.mergeSort(items);
        SortingMachine.printStats();

        System.out.print("Sorted order - ");
        SortingMachine.mergeSort(items);
        SortingMachine.printStats();  

        System.out.print("Reversed order - ");
        SortingMachine.reverse(items);
        SortingMachine.mergeSort(items);
        SortingMachine.printStats();  
        
        // Display results for Quick Sort
        System.out.println("\nQuick Sort: n = " + fmt.format(SIZE));
        SortingMachine.shuffle(items);
        System.out.print("Random order - ");
        SortingMachine.quickSort(items);
        SortingMachine.printStats();

        System.out.print("Sorted order - ");
        SortingMachine.quickSort(items);
        SortingMachine.printStats();  

        System.out.print("Reversed order - ");
        SortingMachine.reverse(items);
        SortingMachine.quickSort(items);
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
        for(int index = lo; index <= hi && !found; index++){
            found = data[index].equals(target);
            comparisons++;
        }   
        return found;
    }

    /**********************************************************
    Recursive Binary Search
     **********************************************************/    
    public static <T extends Comparable<T>> boolean binarySearch(T[] data, int lo, int hi, T target){
        comparisons = 0;
        return bSearch(data, lo, hi, target);
    }

    private static <T extends Comparable<T>> 
    boolean bSearch(T[] data, int lo, int hi, T target){
        int mid = (lo + hi) / 2;
        boolean found = false;

        comparisons++;

        // no remaining items to search
        if(lo > hi){
            return false;
        }

        // found the target
        if(data[mid].equals(target)){
            return true;
        }

        // narrow search to front or back half
        if(data[mid].compareTo(target) > 0)
            found = bSearch(data, lo, mid-1, target);
        else
            found = bSearch(data, mid+1, hi, target);

        return found;
    }    

    /**********************************************************
    Bubble Sort - static and generic
    @param data array of <T> to be sorted
     **********************************************************/    
    public static <T extends Comparable<T>> void bubbleSort(T[] data){
        clearStats();
        for(int pass = data.length-1; pass > 0; pass--){
            passes++;
            boolean hasSwap = false;
            for(int i=0; i<pass; i++){
                comparisons++;
                if(data[i].compareTo(data[i+1]) > 0){
                    swap(data,i,i+1);
                    hasSwap = true;
                }
            }
            if (hasSwap == false){
            	return;
            }
        }   
    } 

/**********************************************************
Quick Sort - static, generic and recursive
@param data array of <T> to be sorted
**********************************************************/    
    public static <T extends Comparable<T>> void quickSort(T[] data){
        clearStats();
        quickSort(data, 0, data.length-1);
        
        // cheat by calculating the number of expected passes
        int n = data.length;
        int logN = 1;
        while(n > 1){
            n = n/2;
            logN++;
        }
        passes = logN+1;
    }

    private static <T extends Comparable<T>> void quickSort(T[] data, int left, int right){        
        if(left < right){
            int pivotIndex = partition(data, left, right);
            quickSort(data, left, pivotIndex - 1);
            quickSort(data, pivotIndex + 1, right);
        }
    } 

    private static <T extends Comparable<T>> int partition(T[] data, int left, int right){
    	int midIndex = (left + right) / 2;
        int pivotIndex =  midIndex;
        T pivot = data[midIndex];

        while(left < right){
            
            // move right until finding element greater than pivot
            while(left<right && data[left].compareTo(pivot) <= 0){
                left++;
                comparisons++;
            }
            comparisons++;
            // move left until finding element less than pivot
            while(data[right].compareTo(pivot) > 0){
                right--;
                comparisons++;
            }
            comparisons++;
            
            // swap left and right
            if(left < right){
                swap(data,left,right);
            }
        }

        // final swap to place pivot element
        swap(data, pivotIndex, right);
        return right;
    }  

    /**********************************************************
    Merge Sort - static and generic
    @param data array of <T> to be sorted
     **********************************************************/    
    public static <T extends Comparable<T>> void mergeSort(T[] data){
        clearStats();
        mergeSort(data, 0, data.length-1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] data, int left, int right){
        if(left == 0)
            passes++;  
            
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid+1, right);
            merge(data, left, mid+1, right);
        }
    } 

    private static <T extends Comparable<T>> void merge(T[] data, int left, int mid, int right){
        T[] temp = (T[]) new Comparable [data.length];
        int stop = mid;
        int start = left;
        
        // merge left and right halves to new array
        for(int i = left; i<= right; i++){
            comparisons++;
            swaps++;
            if(left == stop){
                temp[i] = data[mid];
                mid++; 
                
            // copy next element from left half    
            }else if(mid > right || data[left].compareTo(data[mid]) < 0){
                temp[i] = data[left];
                left++;
                
            // copy next element from right half    
            }else{
                temp[i] = data[mid];
                mid++;
            }
        }

        // copy back to original array
        for(int i = start; i<= right; i++){
            data[i] = temp[i];
        }
    }     

    /**********************************************************
    Insertion Sort - static and generic
    @param data array of <T> to be sorted
     **********************************************************/    
    public static <T extends Comparable<T>> void insertionSort(T[] data){
        clearStats();
        for(int pass = 0; pass < data.length-1; pass++){
            passes++;
            int last = pass+1;
            for(int i=last-1; i>=0; i--){
                comparisons++;
                if(data[last].compareTo(data[i]) < 0){
                    swap(data,last,i);
                    last=i;
                }else{
                    //i=0;
                    break;
                }
            }
        }   
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
            if (minIndex != pass) {
            	swap(data, pass, minIndex);
            }
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

}
