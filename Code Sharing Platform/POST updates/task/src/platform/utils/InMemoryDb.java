package platform.utils;

import platform.entity.Code;

public class InMemoryDb {
    private volatile static Code code = new Code();

    public static Code getCode() {
        return code;
    }

    public static void setCode(Code code) {
        InMemoryDb.code = code;
    }
}
