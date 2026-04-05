package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.LongStream;

public class Day2Part2 {
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
            while(reader.hasNextLine()){ // Iterates through input file, converting inputs to a char (L or R, representing direction) and an integer representing number of clicks
                String data = reader.nextLine();
                for(String s : data.split(",")){
                    String[] rangeBounds = s.split("-");
                    long[] idsToCheck = LongStream.rangeClosed(Long.parseLong(rangeBounds[0]), Long.parseLong(rangeBounds[1])).toArray();
                    //System.out.println(String.format("Range of %s-%s:", rangeBounds[0], rangeBounds[1]));
                    for (long i : idsToCheck) {
                        CheckID(i);
                    }
                    /*for(int i : currentRangeInvalidIDs) {
                        System.out.println(i);
                    }*/

                    invalidIDs.addAll(currentRangeInvalidIDs);
                    currentRangeInvalidIDs.clear();
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        long sum = 0;
        for (Long i : invalidIDs){
            sum += i;
        }

        System.out.println(String.format("Sum of Invalid IDs: %s", sum));
    }

    static void CheckID(long id){

        int[] idAsArray = Long.toString(id).chars().map(a->a-'0').toArray(); // Turns the ID into an array of single digit integers

        ArrayList<Integer> factors = GetFactors(idAsArray.length); // Returns list of factors of the ID's length in descending order (means fewer comparisons needed)

        for(int f : factors){ // Iterates through each of the factors of the length of the ID to check substrings

            ArrayList<int[]> subArrays = new ArrayList<int[]>();
            
            for(int i = 0; i < idAsArray.length; i+=f){ // Separates the ID into segments of equal length
                subArrays.add(Arrays.copyOfRange(idAsArray, i, i+f));
            }

            boolean isInvalid = true;

            for(int[] subArray : subArrays){ // If any of the segments do not match, then ID is not a repeating pattern of length f
                if(!Arrays.equals(subArray, subArrays.getFirst())){
                    isInvalid = false;
                }
            }

            if(isInvalid == true){ // If at least one repeating pattern is found, add to list of invalid IDs and exit
                currentRangeInvalidIDs.add(id);
                return;
            }
        }
    }

    static ArrayList<Integer> GetFactors(int num){
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(int i = (num / 2); i > 0; i--){
            if(num % i == 0){
                list.add(i);
            }
        }

        return list;
        
    }
}
