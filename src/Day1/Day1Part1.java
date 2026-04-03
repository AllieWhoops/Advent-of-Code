package Day1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Part1 {

    static int currentDialPosition;
    static int zeroCount;

    public static void main(String[] args){
        zeroCount = 0;
        currentDialPosition = 50;
        System.out.println(Solve());
    }

    static int Solve(){

        File input = new File("lib/Day 1/input_d1_p1.txt"); // Opens input file
        Scanner reader;
        try {
            reader = new Scanner(input);
            while(reader.hasNextLine()){ // Iterates through input file, converting inputs to a char (L or R, representing direction) and an integer representing number of clicks
                String data = reader.nextLine();
                char dir = data.charAt(0);
                int rotateValue = Integer.valueOf(data.substring(1));
                UpdateDial(rotateValue, dir); // Updates the current dial position based on the instruction
            }
            reader.close();
            return zeroCount;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }

    static void UpdateDial(int rotateValue, char dir){
        while(rotateValue > 99){
            // Essentially this is input sanitation, as any rotation of 100 or greater can be instead represented as a rotation between 0-99.
            // This makes the overflows left and right much easier to deal with.
            rotateValue -= 100;
        }

        if(dir == 'L'){
            if(currentDialPosition - rotateValue < 0){ // Handles underflow
                currentDialPosition += 100 - rotateValue;
            }
            else{
                currentDialPosition -= rotateValue;
            }
        }
        else{
            if(currentDialPosition + rotateValue > 99){ // Handles overflow
                currentDialPosition += rotateValue - 100;
            }
            else{
                currentDialPosition += rotateValue;
            }
        }
        if(currentDialPosition == 0){
            zeroCount++;
        }
        //System.out.println(currentDialPosition);
    }
}
