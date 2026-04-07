package Day3;

import java.util.ArrayList;
import Util.*;

public class Day3Part1 {
    
    static ArrayList<Integer> joltages;
    
    public static void main(String[] args) {
        String[] input = FileStuff.LoadTextFile("lib/Day 3/input_d3.txt");

        joltages = new ArrayList<Integer>();

        for(String line : input){
            FindJoltage(line);
        }

        long sum = 0; // Sum the numerical values of invalid IDs
        for (int i : joltages){
            sum += i;
        }

        System.out.println(String.format("Total Joltage: %d", sum));
    }

    static void FindJoltage(String input){
        int[] inputAsArray = input.chars().map(a->a-'0').toArray(); // Convert input into array of single digits
        int highestDigit = 0;
        int highestIdx = 0;
        for(int i = 0; i < inputAsArray.length - 1; i++){ // Iterates through list until second-to-last item (needs to be a 2-digit result so never check final item for first digit)
            if(inputAsArray[i] > highestDigit){ // If current digit is higher than previous highest, set as new highest
                highestDigit = inputAsArray[i];
                highestIdx = i;
            }
        }

        int secondDigit = 0;
        int secondIdx = highestIdx + 1;
        
        for(int i = secondIdx; i < inputAsArray.length; i++){ // Check all values after highest to find second highest digit to get highest overall number possible
            if(inputAsArray[i] > secondDigit){
                secondDigit = inputAsArray[i];
            }
        }
        int joltage = Integer.parseInt(String.format("%d%d", highestDigit, secondDigit)); // Combine digits into single integer

        joltages.add(joltage); // Add to array of all joltages

        System.out.println(String.format("Bank: %s\tJoltage=%d", input, joltage));
    }
}
