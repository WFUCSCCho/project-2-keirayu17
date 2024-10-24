/***
 * @file: Proj2.java
 * @description: Main class for the project. It creates an arraylist to store all data entries
 *               from the movie dataset. Contains sorted and shuffled versions of the arraylist
 *               for further running time analysis.
 *               Data source: https://www.kaggle.com/datasets/mohammadrizwansajjad/top-200-movies-of-2023
 *               Data has been modified by eliminating unnecessary attributes and trimming spaces to
 *               avoid confusion
 * @author: Keira Yu
 * @date: October 24, 2024
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // Ignore first line
        inputFileNameScanner.nextLine();

        // Store data into original ArrayList
        ArrayList<Movie> movieList = new ArrayList<>();

        while (inputFileNameScanner.hasNextLine() && movieList.size() < numLines) {
            String line = inputFileNameScanner.nextLine();
            String[] data = line.split(",");
            String title = data[0];
            int totalGross = Integer.parseInt(data[1]);
            String releaseDate = data[2];
            String distributor = data[3];

            Movie movie = new Movie(title, totalGross, releaseDate, distributor);
            movieList.add(movie);
        }
        inputFileNameScanner.close();

        // Create sorted list
        ArrayList<Movie> sortedList = new ArrayList<>(movieList);
        Collections.sort(sortedList);

        // Create shuffled list
        ArrayList<Movie> shuffledList = new ArrayList<>(movieList);
        Collections.shuffle(shuffledList);

        BST<Movie> sortedBST = new BST<>();
        BST<Movie> shuffledBST = new BST<>();
        AvlTree<Movie> sortedAVL = new AvlTree<>();
        AvlTree<Movie> shuffledAVL = new AvlTree<>();

        FileWriter fileWriter = new FileWriter("output.txt", true);
        long startTime, endTime, duration;


        // Insert SORTED into BST
        startTime = System.nanoTime();
        for (Movie movie : sortedList) {
            sortedBST.insert(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("BST Sorted Insertion Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",BST,Sorted,Insertion," + duration + "\n");

        // Insert SHUFFLED into BST
        startTime = System.nanoTime();
        for (Movie movie : shuffledList) {
            shuffledBST.insert(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("BST Shuffled Insertion Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",BST,Shuffled,Insertion," + duration + "\n");

        // Insert SORTED into AVL
        startTime = System.nanoTime();
        for (Movie movie : sortedList) {
            sortedAVL.insert(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("AVL Sorted Insertion Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",AVL,Sorted,Insertion," + duration + "\n");

        // Insert SHUFFLED into AVL
        startTime = System.nanoTime();
        for (Movie movie : shuffledList) {
            shuffledAVL.insert(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("AVL Shuffled Insertion Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",AVL,Shuffled,Insertion," + duration + "\n");

        // Search in SORTED BST
        startTime = System.nanoTime();
        for (Movie movie: movieList){
            Movie searchInSortedBST = sortedBST.search(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("BST Sorted Search Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",BST,Sorted,Search," + duration + "\n");

        // Search in SORTED AVL
        startTime = System.nanoTime();
        for (Movie movie : movieList) {
            boolean searchInSortedAVL = sortedAVL.contains(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("AVL Sorted Search Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",AVL,Sorted,Search," + duration + "\n");

        // Search in SHUFFLED BST
        startTime = System.nanoTime();
        for (Movie movie: movieList){
            Movie searchInShuffledBST = shuffledBST.search(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("BST Shuffled Search Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",BST,Shuffled,Search," + duration + "\n");

        // Search in SHUFFLED AVL
        startTime = System.nanoTime();
        for (Movie movie : movieList) {
            boolean searchInShuffledAVL = shuffledAVL.contains(movie);
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("AVL Shuffled Search Time: " + duration + " nanoseconds");
        fileWriter.append(numLines + ",AVL,Shuffled,Search," + duration + "\n");

        fileWriter.close();
    }
}
