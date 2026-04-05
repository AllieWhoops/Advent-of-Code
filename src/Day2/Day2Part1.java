package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.LongStream;

public class Day2Part1 {
    static ArrayList<Long> invalidIDs;
    static ArrayList<Long> currentRangeInvalidIDs;

    public static void main(String[] args){
        invalidIDs = new ArrayList<Long>();
        currentRangeInvalidIDs = new ArrayList<Long>();
        Solve();

    }

    static void Solve(){
        File input = new File("lib/Day 2/input_d2.txt"); // Opens input file
        Scanner reader;
        try {
            reader = new Scanner(input);
            while(reader.hasNextLine()){ // Iterates through input file
                String data = reader.nextLine();
                for(String s : data.split(",")){
                    String[] rangeBounds = s.split("-");
                    long[] idsToCheck = LongStream.rangeClosed(Long.parseLong(rangeBounds[0]), Long.parseLong(rangeBounds[1])).toArray(); // Generates an array with all values in the range

                    for (long i : idsToCheck) {
                        CheckID(i);
                    }

                    invalidIDs.addAll(currentRangeInvalidIDs);
                    currentRangeInvalidIDs.clear();
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        long sum = 0; // Sum the numerical values of invalid IDs
        for (Long i : invalidIDs){
            sum += i;
        }

        System.out.println(String.format("Sum of Invalid IDs: %s", sum));
    }

    static void CheckID(long id){
        int[] idAsArray = Long.toString(id).chars().map(a->a-'0').toArray(); // Turns the ID into an array of single digit integers
        int[] idArrayLeft = Arrays.copyOfRange(idAsArray, 0, idAsArray.length / 2); // Splits the array into two segments
        int[] idArrayRight = Arrays.copyOfRange(idAsArray, idAsArray.length / 2, idAsArray.length);
        if(Arrays.equals(idArrayLeft, idArrayRight) == true){ // If the segments are identical, then the ID is a pattern of digits repeated twice .'. add to invalid ID list
            currentRangeInvalidIDs.add(id);
        }
        
    }
}
