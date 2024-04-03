package Classes;

import java.time.ZonedDateTime;

public final class Context {
    private static final StructureStorage structureStorage = new StructureStorage();
    private static final String pathVar = "FlatsFilePath";
    private static int maxRecursionDepth = 100;

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

    public static String getPathVar() {
        return pathVar;
    }

    public static int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
    public static void setMaxRecursionDepth(int recursionDepth) {
        maxRecursionDepth = recursionDepth;
    }
}
