package Classes;

import java.io.*;
import java.util.*;


public class StructureStorage {
    protected static Stack<Flat> collection = new Stack<>();


    public synchronized void sort(){
        collection.sort(null);
    }

    public boolean save(String filename) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(filename)){
            pw.println("<StoreStack>");
            collection.forEach(pw::print);
            pw.print("</StoreStack>");
            return true;
        }
    }

    public void load(String env) { //todo: Getting filepath from Environment variables
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(System.getenv(env)))) { // todo: BufferedInputStream ahahahahha
            collection.addAll((Collection<? extends Flat>) Flat.StXMLParser.parseFlats(bis, new Stack<>()));
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
        }
    }

    public Stack<Flat> getCollection() {
        return collection;
    }
}
