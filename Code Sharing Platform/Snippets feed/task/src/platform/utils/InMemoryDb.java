package platform.utils;

import platform.entity.Code;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDb {
    private static long id;
    private static Map<Long, Code> codes = new ConcurrentHashMap<>();
    private static Map<Long, Code> latest = Collections.synchronizedMap(new TreeMap<>(Collections.reverseOrder()));
    private static final int LATEST_COUNT = 10;

    public static Map<Long, Code> getLatestCodes() {
        return latest;
    }

    public static synchronized long setNewCode(Code code) {
        codes.put(++id, code);
        recalculateLatest();
        return id;
    }

    private static void recalculateLatest() {
        // adding the latest code
        latest.put(id, codes.get(id));
        // removing the oldest code
        latest.remove(id - LATEST_COUNT);
    }

    public static Code getCodeById(long id) {
        return codes.get(id);
    }
}
