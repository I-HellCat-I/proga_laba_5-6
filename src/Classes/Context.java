package Classes;

import java.lang.ref.Cleaner;
import java.time.ZonedDateTime;

/**
 * Общий контекст, содержит ссылки на переменные, используемые в разных частях программы, ибо мне лень их передавать
 **/
public final class Context {

    private static final StructureStorage structureStorage = new StructureStorage();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(structureStorage.getOnCleanRunnable()));
        Cleaner cl = Cleaner.create();
        cl.register(structureStorage, structureStorage.getOnCleanRunnable());
    }

    private static final String pathVar = "HOME";
    private static int maxRecursionDepth = 100;
    private static boolean exitCommandUsed = false;

    static {
        structureStorage.load();
    }

    private static final ZonedDateTime initDate = ZonedDateTime.now();

    public static StructureStorage getStructureStorage() {
        return structureStorage;
    }

    public static ZonedDateTime getInitDate() {
        return initDate;
    }

    public static String getPath() {
        return System.getenv(pathVar) + "/saved.xml";
    }

    public static int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public static void setMaxRecursionDepth(int recursionDepth) {
        maxRecursionDepth = recursionDepth;
    }

    public static void setExitCommandUsed() {
        exitCommandUsed = true;
    }

    public static boolean getExitCommandUsed() {
        return exitCommandUsed;
    }
}
