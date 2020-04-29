package com.boob.generator.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author: yan
 * @Date: 2020/4/29 0029
 * @Version: 1.0
 */

public class FileUtil {

    private static final Log LOG = LogFactory.getLog(FileUtil.class);

    public static void writeFile(String location, String content) {
        writeFileOverride(location, content, false);
    }

    public static void writeFileOverride(String location, String content, boolean override) {
        try {
            File file = getFile(location, override);
            Writer writer = getWriter(file);
            if (writer == null) {
                return;
            }
            writer.write(content);
            LOG.info("generateList " + location + " success");
            closeWriter(writer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("write file fail, please check location");
        }

    }

    public static void closeWriter(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Writer getWriter(File file) throws Exception {
        if (file == null) {
            return null;
        }
        return new FileWriter(file);
    }

    public static File getFile(String location, boolean override) {
        File file = new File(location);
        if (file.exists()) {
            if (!override) {
                LOG.warn("file " + location + ".java" + "is exists, we can't override it");
                return null;
            }
            LOG.warn("file " + location + ".java" + "is exists, we will override it");
        }
        return file;
    }

    public static File getFile(String location) {
        return new File(location);
    }
}
