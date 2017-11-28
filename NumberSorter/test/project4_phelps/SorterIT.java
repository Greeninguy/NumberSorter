/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_phelps;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noxma
 */
public class SorterIT {
    
    public SorterIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testQuickSortStarter() {
        System.out.println("quickSortStarter");
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = (int)(Math.random() * 100) + 1;
        }
        for (int i = 0; i < 100; i++) {
            System.out.print(array[i] + " ");
        }
        Sorter instance = null;
        instance.quickSortStarter(array);
        for (int i = 0; i < 100; i++) {
            System.out.print(array[i] + " ");
        }
    }

//    /**
//     * Test of quickSort method, of class Sorter.
//     */
//    @Test
//    public void testQuickSort() {
//        System.out.println("quickSort");
//        int[] array = null;
//        int first = 0;
//        int last = 0;
//        Sorter instance = null;
//        instance.quickSort(array, first, last);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of quickSortPartitioner method, of class Sorter.
//     */
//    @Test
//    public void testQuickSortPartitioner() {
//        System.out.println("quickSortPartitioner");
//        int[] array = null;
//        int low = 0;
//        int high = 0;
//        Sorter instance = null;
//        int expResult = 0;
//        int result = instance.quickSortPartitioner(array, low, high);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    

}
