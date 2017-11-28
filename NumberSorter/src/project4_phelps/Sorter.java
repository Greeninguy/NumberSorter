package project4_phelps;

import java.util.Arrays;
import java.util.Queue;

public class Sorter implements Runnable{
    
    private final int[] array;
    private final int sort;
    private ArrayQueue queue;
    
    /**
     * Constructor for the sorter thread class
     * @param array the array to be sorted, a chunk of the original array
     * @param sort the type of sort is determined by this integer
     * @param arrays the queue which the sorter will offer it's array to when finished sorting
     */
    public Sorter(int[] array, int sort, ArrayQueue arrays) {
        this.array = array;
        this.sort = sort;
        this.queue = arrays;
    }

    /**
     * method for accessing the sorter's array
     * @return returns the array this thread is sorting
     */
    public int[] getArray() {
        return array;
    }
    
    /**
     * This method starts the thread and will sort the array with one of the four
     * sort algorithms, depending on which was selected in the GUI.
     */
    @Override
    public void run() {
        if (sort == 1) 
            bubbleSort(array);
        
        else if (sort == 2) 
            selectionSort(array);
        
        else if (sort == 3) 
            insertionSort(array);
        
        else
            quickSortStarter(array);
    }
    
    /**
     * BubbleSort algorithm groups two integers at a time and switches them up
     * if the smaller integer was in front.  Repeats this process for every number
     * until all of the largeest numbers have been pushed back, thus sorting the array.
     * @param array the array to be sorted
     */
    public void bubbleSort(int[] array) {
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - j - 1; i++) {
                int a = array[i];
                int b = array[i + 1];
                if (b <= a) {
                    array[i] = b;
                    array[i + 1] = a;
                }
            }
        }
        queue.offerArray(array);
    }
    
    /**
     * SelectionSort algorithm.  Interates through the entire array to find the 
     * smallest integer, and then switches it with the first integer.  This process
     * is repeated for each number starting at the beginning.
     * @param array array to be sorted
     */
    public void selectionSort(int[] array) {
        int count = 0;
        int index = 0;
        int temp = 0;
        int current;
        int smallest;
        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            smallest = array[i];
            for (int j = i + 1; j < array.length; j++) {
                current = array[j];
                if (current <= smallest) {
                    smallest = current;
                    index = j;
                }
            }
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
            count++;
        }
        queue.offerArray(array);
    }
    
    /**
     * InsertionSort algorithm iterates through each position of the array.  The
     * integer of that position is compared to each number before it, and switches
     * if it is smaller.  
     * @param array the array to be sorted
     */
    public void insertionSort(int[] array) {
        int check;
        int before;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                check = array[j];
                before = array[j-1];
                if (check >= before) {
                    break;
                }
                else {
                    array[j] = before;
                    array [j-1] = check;
                }
            }
        }
        queue.offerArray(array);
    }
    
    /**
     * Method used to start the recursive quickSort method.  
     * @param array the array to be sorted
     */
    public void quickSortStarter(int[] array) {
        quickSort(array, 0, array.length - 1);
        queue.offerArray(array);
    }
    
    /**
     * QuickSort method calls upon the helper method quickSortPartitioner which
     * partitions the integers and places the pivot in its sorted position.  Method
     * is called recursively on the parts of the array before and after the pivot, 
     * if available.
     * @param array the array to be sorted
     * @param first the number 0, first position of the array
     * @param last the last position of the array
     */
    public static void quickSort(int[] array, int first, int last) {
        int pivot = quickSortPartitioner(array, first, last);
        if (first < pivot - 1) 
            quickSort(array, first, pivot - 1);
        if (last > pivot)
            quickSort(array, pivot, last);
    }
    
    /**
     * Method partitions the numbers around the pivot, which is arbitrary selected
     * to be the first integer of the array.  Low and High pointers move up and 
     * down the array to find integers smaller and larger the pivot.  When such 
     * number is found the integers in the low and the high pointers switch positions.
     * @param array array to be partitioned
     * @param low low pointer that moves up the array searching for integers smaller than the pivot
     * @param high high pointer which moves down the array from the top
     * @return returns the ending position of low, which is the sorted pivot
     */
    public static int quickSortPartitioner(int[] array, int low, int high) {
        int pivot = array[low];
        while (low <= high) {
            while (array[low] < pivot) {
                low++;
            }
            while (array[high] > pivot) {
                high--;
            }
            
            if (low <= high) {
                int t = array[low];
                array[low] = array[high];
                array[high] = t;
                low++;
                high--;
            }
        }
        return low;
    }

    /**
     * method used to offer the sorter's array into the ArrayQueue
     * @param arrays the array that was sorted
     */
    public void offerArray(ArrayQueue arrays) {
        arrays.offerArray(array);
    }
        
}
