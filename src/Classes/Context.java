package Classes;

import java.time.ZonedDateTime;

public final class Context {
    private static final StructureStorage structureStorage = new StructureStorage();
    static {
        structureStorage.load(getPathVar());
    }
    private static final ZonedDateTime initDate = ZonedDateTime.now();
    public static StructureStorage getStructureStorage() {
        return structureStorage;
    }
    public static ZonedDateTime getInitDate(){
        return initDate;
    }

    public static String getPathVar() {
        return "FlatsFilePath";
    }
}
