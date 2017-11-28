/**
 * Project 4 for CS-1181
 * The use of multi-threading is used for different sorting algorithms
 * GUI will allow the user to sort an array generated through different options
 */
package project4_phelps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Jimmie Phelps
 * CS1181
 * Instructor: M. Cheatham
 * Project 4
 */
public class Project4_Phelps extends Application {
   int inputChoice; 
   int sortChoice;
   int sizeChoice;
   int blockChoice;
   long startTime;
   long endTime;
   long timeTook;
   boolean properInput = true;
   boolean properButtons = true;
   boolean hasInput = true;
   boolean properBlock = true;
   static ArrayQueue queue = new ArrayQueue();
   static ArrayList<Thread> threads = new ArrayList<>();
   static ArrayList<Sorter> sorters = new ArrayList<>();
    
   /**
    * Stages the GUI and all of its components which allow the user to modify the sorts.
    * @param primaryStage 
    */
    @Override
    public void start(Stage primaryStage) {
        
        VBox main = new VBox();
        main.setSpacing(5);
        main.setPadding(new Insets(5, 5, 5, 5));
        
        VBox sorts = new VBox();
        sorts.setStyle("-fx-border-color: black");
        sorts.setPadding(new Insets(10, 10, 10, 10));
        
        Label sorterLabel = new Label("Sorting Algorithm");
        sorts.getChildren().add(sorterLabel);
        
        RadioButton sort1 = new RadioButton("Selection");
        RadioButton sort2 = new RadioButton("Bubble");
        RadioButton sort3 = new RadioButton("Insertion");
        RadioButton sort4 = new RadioButton("Quick");
        ToggleGroup sortGroup = new ToggleGroup();
        sort1.setToggleGroup(sortGroup);
        sort2.setToggleGroup(sortGroup);
        sort3.setToggleGroup(sortGroup);
        sort4.setToggleGroup(sortGroup);
        sort1.setOnAction(e ->  {
            sortChoice = 2;
        });
        sort2.setOnAction(e -> {
            sortChoice = 1;
        });
        sort3.setOnAction(e -> {
            sortChoice = 3;
        });
        sort4.setOnAction(e -> {
            sortChoice = 4;
        });
        sorts.getChildren().add(sort1);
        sorts.getChildren().add(sort2);
        sorts.getChildren().add(sort3);
        sorts.getChildren().add(sort4);
        
        main.getChildren().add(sorts);
        
        VBox inputTypes = new VBox();
        inputTypes.setStyle("-fx-border-color: black");
        inputTypes.setPadding(new Insets(10, 10, 10, 10));
        
        Label inputLabel = new Label("Input Type");
        inputTypes.getChildren().add(inputLabel);
        
        RadioButton sorted = new RadioButton("Already sorted");
        RadioButton reverse = new RadioButton("Reverse order");
        RadioButton random = new RadioButton("Random");
        ToggleGroup inputGroup = new ToggleGroup();
        sorted.setToggleGroup(inputGroup);
        reverse.setToggleGroup(inputGroup);
        random.setToggleGroup(inputGroup);
        sorted.setOnAction(e -> {
            inputChoice = 1;
        });
        reverse.setOnAction(e -> {
            inputChoice = 2;
        });
        random.setOnAction(e -> {
            inputChoice = 3;
        });
        inputTypes.getChildren().add(sorted);
        inputTypes.getChildren().add(reverse);
        inputTypes.getChildren().add(random);
        
        main.getChildren().add(inputTypes);
        
        GridPane textFields = new GridPane();
        textFields.setStyle("-fx-border-color: black");
        textFields.setHgap(4);
        textFields.setVgap(3);
        textFields.setPadding(new Insets(10, 10, 10, 10));
        
        Label sizeLabel = new Label("Input Size");
        textFields.add(sizeLabel, 0, 0);
        
        TextField sizeField = new TextField();
        textFields.add(sizeField, 1, 0);
        
        Label blockLabel = new Label("Block Size");
        textFields.add(blockLabel, 0, 1);
        
        TextField blockField = new TextField();
        textFields.add(blockField, 1, 1);
        
        main.getChildren().add(textFields);
        
        Alert inputError = new Alert(AlertType.ERROR);
        inputError.setHeaderText("Error");
        inputError.setContentText("Please enter an input and block size.");
        
        Alert blockError = new Alert(AlertType.ERROR);
        blockError.setTitle("Error");
        blockError.setContentText("The block size cannot be larger than the input size");
        
        Alert negativeError = new Alert(AlertType.ERROR);
        negativeError.setTitle("Error");
        negativeError.setContentText("Please enter a positive input and block size.");
        
        Alert selectionError = new Alert(AlertType.ERROR);
        selectionError.setTitle("Error");
        selectionError.setContentText("Please select a sorting algorithm and input type.");
        
        Button go = new Button("Go");
        go.prefWidthProperty().bind(main.widthProperty());
        go.setOnAction(e -> {
            if ((sort1.isSelected() == false && sort2.isSelected() == false
                    && sort3.isSelected() == false && sort4.isSelected() == false)
                    || (sorted.isSelected() == false && reverse.isSelected() == false
                    && random.isSelected() == false)) {
                properButtons = false;
                selectionError.showAndWait();
            }
            else 
                properButtons = true;
            
            if (sizeField.getText().isEmpty() || blockField.getText().isEmpty()) {
                inputError.showAndWait();
                hasInput = false;
            }
            else
                hasInput = true;
            
            sizeChoice = Integer.parseInt(sizeField.getText());
            blockChoice = Integer.parseInt(blockField.getText());
            
            if (sizeChoice <= 0 || blockChoice <= 0) {
                properInput = false;
                negativeError.showAndWait();
            }
            else
                properInput = true;
            
            if (sizeChoice < blockChoice) {
                properBlock = false;
                blockError.showAndWait();
            }
            else 
                properBlock = true;
            
            if (properInput == true && properBlock == true 
                    && properButtons == true && hasInput == true) {
                int[] array = makeArray(sizeChoice, inputChoice);
                
                startTime = System.currentTimeMillis();
                
                threads = chunkArray(array, blockChoice, sortChoice, queue);
                sorterStarter(threads);
                
                int queueSize = queue.getSize();
                array = merge(queue, queueSize);
                
                endTime = System.currentTimeMillis();
                timeTook = endTime - startTime;
                
                Alert finished = new Alert(AlertType.INFORMATION);
                finished.setTitle("Threaded Sorting");
                finished.setHeaderText("Finished");
                finished.setContentText("Sort completed in " + timeTook + " ms");
                
                System.out.println(Arrays.toString(array));
                finished.showAndWait();
            }
        });
        
        main.getChildren().add(go);
        
        Scene scene = new Scene(main, 250, 340);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    /**
     * This method is used by the Start method to create the appropriate array
     *      based on the options chosen
     * @param size is the number of integers of the array to be made
     * @param inputType this determines if the array will be in ascending or
     *      descending order, or if it will be random numbers
     * @return returns the array that will be sorted by the program
     */
    public static int[] makeArray(int size, int inputType) {
        int[] array = new int[size];
        
        if (inputType == 1) {
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }
        }
        
        else if (inputType == 2) {
            for (int i = 0; i < array.length; i++) {
                array[i] = array.length - i;
            }
        }
        
        else {
            for (int i = 0; i < array.length; i++) {
                array[i] = (int)(Math.random() * 99) + 0;
            }
        }
        
        return array;
    }
    
    /**
     * This method splits the array into the requested number of chunks, and then
     *      hands each chunk into a sorter thread which sorts the array and places
     *      it into an list.
     * @param array the array passed into the method will be sorted
     * @param chunks this is the number of chunks the array is split into
     * @param sortType this determines which sorting algorithm is used to sort
     * @param queue this is the queue that the sorted arrays are placed into
     * @return a list is made which all of the sorter threads reside in
     */
    public static ArrayList<Thread> chunkArray(int[] array, int chunks, int sortType, ArrayQueue queue) {
        ArrayList<Thread> list = new ArrayList<>();
        int extras = array.length % chunks;
        for (int i = 0; i < chunks * (array.length / chunks); i += chunks) {
            int[] array1 = Arrays.copyOfRange(array, i, i + chunks);
            Sorter sorter1 = new Sorter(array1, sortType, queue);
            sorters.add(sorter1);
            Thread thread = new Thread(sorter1);
            list.add(thread);
        }
        if (extras != 0) {
            int[] array2 = Arrays.copyOfRange(array, array.length - extras, array.length);
            Sorter sorter2 = new Sorter(array2, sortType, queue);
            sorters.add(sorter2);
            Thread thread = new Thread(sorter2);
            list.add(thread);
            return list;
            }
        return list;
    }
    
    /**
     * This method starts all of the threads passed to it
     * @param list the threads are passed to the method in a list
     */
    public static void sorterStarter(ArrayList<Thread> list) {
        for (Thread sorter : list) {
            sorter.start();
        }
        try {
            for (Thread sorter : list) {
                sorter.join();
            }
        }
        catch (InterruptedException e) {
            e.getMessage();
        }
    }
    
    /**
     * The merge method takes the queue of sorted arrays assigns two arrays to
     *      be merged by a merger thread.  
     * @param queue arrays are polled and offered to this queue by the merger threads
     * @param chunks the number arrays that the original unsorted array was split into
     *              used to determine how many merger threads to create
     * @return returns the merged and sorted array
     */
    public static int[] merge(ArrayQueue queue, int chunks) {
        ArrayList<Thread> list = new ArrayList<>();
        int counter = 1;
        while (counter != chunks) {
            if (queue.getSize() >= 2) {
                int[] array1 = queue.pollArray();
                int[] array2 = queue.pollArray();
                Thread thread = new Thread(new Merger(array1, array2, queue));
                list.add(thread);
                thread.start();
                counter++;
            }
        }
        for (Thread t : list) {
            try {
                t.join();
            }
            catch(InterruptedException e) {
                e.getMessage();
            }
        }
        return queue.pollArray();
    }
    
}
