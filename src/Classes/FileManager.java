package Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class FileManager {
    /**
     * Класс, который отвечает за загрузку и сохранение StructureStorage, в остальное время спокойно спит
     */
    private static final XmlMapper mapper = XmlMapper.builder().findAndAddModules().build();

    public static boolean saveCollection() {
        String toWrite;
        try {
            new File(Context.getPath()).createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        try {
            toWrite = mapper.writeValueAsString(Context.getStructureStorage().getCollection());
        } catch (JsonProcessingException e) {
            return false;
        }
        try (PrintWriter pw = new PrintWriter(Context.getPath())) {
            pw.write(toWrite);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (NullPointerException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    public static Stack<Flat> loadCollection() {
        try {
            Stack<Flat> loaded = null;
            try {
                new File(Context.getPath()).createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            mapper.registerModule(new JavaTimeModule());
            try {
                loaded = mapper.readValue(new File(Context.getPath()), new TypeReference<Stack<Flat>>() {
                });
                for (Flat f : loaded) {
                    f.addLoadedId();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            Flat.finishLoadingIds();
            return loaded;
        } catch (NullPointerException e) {

            return new Stack<>();

        }
    }
}
