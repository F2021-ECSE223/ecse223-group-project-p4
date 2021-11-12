package ca.mcgill.ecse.climbsafe.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersistenceObjectStream {

  private static String filename = "output.txt";

  /**
   * Serializes an object to memory
   * 
   * @author Matthieu Hakim
   * @param object The object we want to serialize
   */
  public static void serialize(Object object) {
    try (var oos = new ObjectOutputStream(new FileOutputStream(new File(filename)))) {
      oos.writeObject(object);
    } catch (IOException e) {
      throw new RuntimeException("Could not save data to file '" + filename + "'.");
    }
  }

  /**
   * Deserializes an object from memory
   * 
   * @author Karl Rouhana
   * @return The deserialized object
   */
  public static Object deserialize() {
    try (var ois = new ObjectInputStream(new FileInputStream(new File(filename)))) {
      return ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Setter for the filename static variable
   * 
   * @author Adam Kazma
   * @param newFilename The name we want to set
   */
  public static void setFilename(String newFilename) {
    filename = newFilename;
  }

}

