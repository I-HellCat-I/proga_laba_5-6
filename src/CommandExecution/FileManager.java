package CommandExecution;

import Classes.Context;
import Classes.Flat;
import Classes.StructureStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.Stack;

public class FileManager {
    private static final XmlMapper mapper = new XmlMapper();
    public static boolean saveCollection(){
        String fn = System.getenv(Context.getPathVar());
        String toWrite;
        StringBuilder builder = new StringBuilder();
        try {
            toWrite = mapper.writeValueAsString(Context.getStructureStorage().getCollection());
        } catch (JsonProcessingException e) {
            return false;
        }
        try (PrintWriter pw = new PrintWriter(Context.getPathVar())){
            pw.write(toWrite);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }
    public static Stack<Flat> loadCollection(){
        Stack<Flat> a = null;
        try {
            a = mapper.readValue(new File(Context.getPathVar()), Stack.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
}
