package project4_phelps;

import java.util.Arrays;
import java.util.Queue;

public class Merger implements Runnable{
    
    private int[] array1;
    private int[] array2;
    private ArrayQueue queue;

    /**
     * Constructor for the merger thread.
     * @param array1 the first sorted array chunk to be merged
     * @param array2 the second sorted array chunk to be merged
     * @param queue the queue which the merger will offer the merged array to
     */
    public Merger(int[] array1, int[] array2, ArrayQueue queue) {
        this.array1 = array1;
        this.array2 = array2;
        this.queue = queue;
    }

    /**
     * Starts the thread, which calls upon the merger method
     */
    @Override
    public void run() {
        merger(array1, array2, queue);
    }
    
    /**
     * merger method starts a counter starting at the begenning of three arrays,
     * which are the two arrays to be merged and the finished merged array.  The
     * counters go through both arrays and determines the smaller integer to be
     * placed into the merged array.
     * @param array1 the first array to be merged
     * @param array2 the second array to be merged
     * @param queue the queue which the merged array is offered to
     */
    public void merger(int[] array1, int[] array2, ArrayQueue queue) {
        int[] merged = new int[array1.length + array2.length];
        int a = 0;
        int b = 0;
        int c = 0;
        
        while (c < merged.length && a < array1.length && b < array2.length) {
            if (array1[a] <= array2[b]) {
                merged[c] = array1[a];
                a++;
                c++;
                if (a == array1.length && b != array2.length) {
                    while (b < array2.length) {
                        merged[c] = array2[b];
                        c++;
                        b++;
                    }
                }
            }
            else {
                merged[c] = array2[b];
                b++;    
                c++;
                if (b == array2.length && a != array1.length) {
                    while (a < array1.length) {
                        merged[c] = array1[a];
                        a++;
                        c++;
                    }
                }
            }
        }
        queue.offerArray(merged);
    }
}
