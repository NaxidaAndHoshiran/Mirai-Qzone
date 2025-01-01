package cn.travellerr.qqzone.utils;

import cn.travellerr.qqzone.entity.Cookie;

import java.util.HashMap;
import java.util.Map;

public class CookieParser {
    public static Cookie parseToCookie(String cookie) {
        Map<String, String> cookieMap = new HashMap<>();
        String[] pairs = cookie.split("; ");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                cookieMap.put(keyValue[0], keyValue[1]);
            }
        }

        return Cookie.Companion.parseFromMap(cookieMap);
    }
}
