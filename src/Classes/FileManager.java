package Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.Stack;

public class FileManager {
    /**
     * Класс, который отвечает за загрузку и сохранение StructureStorage, в остальное время спокойно спит
     */
    private static final XmlMapper mapper = XmlMapper.builder().findAndAddModules().build();

    public static boolean saveCollection() {
        String toWrite;
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
        }
    }

    public static Stack<Flat> loadCollection() {
        Stack<Flat> loaded = null;
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
    }
}
