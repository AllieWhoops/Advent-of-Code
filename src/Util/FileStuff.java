package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStuff {

    /**
     * Returns lines of text file at *filepath* as a String[]
     * @param filepath The location of the file to be read
     * @return The lines of the text file as a String[]
     */
    public static String[] LoadTextFile(String filepath){
        File input = new File(filepath); // Opens input file
        ArrayList<String> tempList = new ArrayList<String>();

        Scanner reader;
        try {
            reader = new Scanner(input);
            while(reader.hasNextLine()){
                tempList.add(reader.nextLine());
            }
            reader.close();
            
            return tempList.toArray(new String[0]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
