package Day3;

import java.util.ArrayList;
import java.util.Arrays;

import Util.*;

public class Day3Part2 {
    
    static ArrayList<Long> joltages;
    
    public static void main(String[] args) {
        String[] input = FileStuff.LoadTextFile("lib/Day 3/input_d3.txt");

        joltages = new ArrayList<Long>();

        for(String line : input){
            FindJoltage(line);
        }

        long sum = 0; // Sum the numerical values of invalid IDs
        for (long i : joltages){
            sum += i;
        }

        System.out.println(String.format("Total Joltage: %d", sum));

    }

    static void FindJoltage(String input){
        int[] inputAsArray = input.chars().map(a->a-'0').toArray(); // Convert input into array of single digits

        ArrayList<Integer> batteryDigits = new ArrayList<Integer>();
        int M = 0;
        for(int N = 11; N >= 0; N--){
            
            int highestDigit = 0;
            int highestIdx = 0;
            for(int i = M; i < inputAsArray.length - N; i++){ // Finds highest digit in first [LENGTH - N] indexes after previous digit (M). This means that there are always enough digits later in the sequence to fill all 12 slots.
                if(inputAsArray[i] > highestDigit){ // If current digit is higher than previous highest, set as new highest
                    highestDigit = inputAsArray[i];
                    highestIdx = i;
                }
            }
            M = highestIdx + 1;
            System.out.println(String.format("Digit: %d\tIndex: %d", highestDigit, highestIdx));
            batteryDigits.add(highestDigit);
        }

        long joltage = NumberStuff.DigitsToLong(batteryDigits);

        joltages.add(joltage); // Add to array of all joltages

        System.out.println(String.format("Bank: %s\tJoltage=%d", input, joltage));
    }
}
