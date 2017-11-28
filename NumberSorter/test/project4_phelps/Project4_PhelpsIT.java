/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_phelps;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phelp
 */
public class Project4_PhelpsIT {
    
    public Project4_PhelpsIT() {
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

//    /**
//     * Test of start method, of class Project4_Phelps.
//     */
//    @Test
//    public void testStart() {
//        System.out.println("start");
//        Stage primaryStage = null;
//        Project4_Phelps instance = new Project4_Phelps();
//        instance.start(primaryStage);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of main method, of class Project4_Phelps.
//     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        Project4_Phelps.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of chunkArray method, of class Project4_Phelps.
     */
    @Test
    public void testChunkArray() {
        System.out.println("chunkArray");
        int[] array = new int[50];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 100) + 1;
        }
        System.out.println(Arrays.toString(array));
        int chunks = 4;
        int sortType = 3;
        Project4_Phelps instance = new Project4_Phelps();
        instance.chunkArray(array, chunks, sortType);
        
    }


    /**
     * Test of threadStarter method, of class Project4_Phelps.
     */
    @Test
    public void testThreadStarter() {
        System.out.println("threadStarter");
        
        ArrayList<Thread> list = null;
        Project4_Phelps.threadStarter(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
