package Classes;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Stack;
import java.util.function.Consumer;
import Classes.Flat;


public class StoreStack<Flat> extends Stack<Flat> {
    public synchronized void sort(){
        super.sort(null);
    }

    public boolean save(String filename) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(filename)){
            pw.println("<StoreStack>");
            this.forEach(pw::print);
            pw.print("</StoreStack>");
            return true;
        }
    }

    public void load(String env) { //todo: Getting filepath from Environment variables
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(System.getenv(env)))) { // todo: BufferedInputStream ahahahahha
            this.addAll((Collection<? extends Flat>) StXMLParser.parseFlats(bis));
        } catch (Exception e) {
            System.out.println(e.getMessage() + " " + Arrays.toString(e.getStackTrace()));
        }
    }
}
