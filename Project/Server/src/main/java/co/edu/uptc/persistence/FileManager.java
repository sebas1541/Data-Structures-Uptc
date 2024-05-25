package co.edu.uptc.persistence;

import java.io.*;

public class FileManager {
    public static <T> T saveObject(T object, String path) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            out.writeObject(object);
            return object;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> T loadObject(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream in = new ObjectInputStream(fis)) {

            return (T) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
