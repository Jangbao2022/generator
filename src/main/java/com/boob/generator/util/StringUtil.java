package com.boob.generator.util;

/**
 * @Author: yan
 * @Date: 2020/4/29 0029
 * @Version: 1.0
 */

public class StringUtil {

    public static String pathToPackage(String path) {
        return path.replace("//", "/")
                .replace("/", ".");
    }

    public static String packageToPath(String... packageNames) {
        StringBuilder sb = new StringBuilder();
        for (String pn : packageNames) {
            sb.append(pn).append(".");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString().replace("..", ".")
                .replace(".", "/");
    }
}
