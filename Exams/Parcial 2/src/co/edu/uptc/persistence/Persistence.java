package co.edu.uptc.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Persistence {
    public String fileToString(String filepath){
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                return myReader.nextLine();
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}