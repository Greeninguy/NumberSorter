package project4_phelps;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ArrayQueue {
    
    private Queue<int[]> queue = new ConcurrentLinkedQueue<>();

    /**
     * a getter for the queue
     * @return 
     */
    public Queue<int[]> getArrays() {
        return queue;
    }
    
    /**
     * method for polling an array from the queue
     * @return 
     */
    public synchronized int[] pollArray() {
        
        return queue.poll();
    }
    
    /**
     * method for offering an array to the queue
     * @param array 
     */
    public synchronized void offerArray(int[] array) {
        queue.offer(array);
    }
    
    /**
     * a method to see how many arrays are in the queue
     * @return returns the number of arrays
     */
    public int getSize() {
        return queue.size();
    }
    
    /**
     * peek method to see the first array in the queue
     * @return returns the array first in the queue's list
     */
    public int[] peek() {
        return queue.peek();
    }
}



