package top.desq.javaapp.util;

import top.desq.javaapp.model.AbstractEntity;

public class SecurityUtil {

    private static int id = AbstractEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}