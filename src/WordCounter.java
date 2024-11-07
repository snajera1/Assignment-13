import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WordCounter {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> wordCount = new TreeMap<String, Integer>(); //Establishes a treemap to hold the final counts
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter a file name, including the extension: ");
        String fileName =  scnr.nextLine(); //Gets the desired filename from the user
        int count = 0; //int variable to decide how large the array to hold the file contents should be

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line; //String variable that stores the current line
            while((line = reader.readLine()) != null){ //Counts how many lines in the text file have a word in them
                count++;
            }
        }

        String[] words = new String[count]; //Creates an array to hold the contents read from the file based on how many lines were counted

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line; //String variable to hold the contents of a line from a text file
            int i = 0; //Integer variable to keep count of the index for the array
            while((line = reader.readLine()) != null){ //A while loop that loops through all the populated lines of a text file
             words[i] = line.toLowerCase(); //Adds a lowercase version of each word to an array so they can be counted case insensitively
             i++;   
            }
        }

        for(int i = 0; i < words.length; i++){ //Loops through the array of words from the file
            if(wordCount.containsKey(words[i])){ //If the word is already in the array, the key's associated value is increased by 1 
                wordCount.put(words[i], wordCount.get(words[i]) + 1);
            }else{ //If the word is not yet in the treemap, it is added as a key with a value of 1
                wordCount.put(words[i], 1);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for(String word : wordCount.keySet()){ //Writes each key and value to the file output.txt
                writer.write(word + ": " + wordCount.get(word) + "\n");
            }
        }

        System.out.println("The word count has been written to file output.txt: "); //Gives the user confirmation that the file has been successfully written to

    }
}
