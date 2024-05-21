package co.edu.uptc.persistence;

import java.io.*;
import java.util.List;

public class FileManager {
    public static <T> List<T> saveObjects(List<T> objects, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(objects);
            return objects;


        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> loadObjects(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis);

            return (List<T>) in.readObject();

        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
