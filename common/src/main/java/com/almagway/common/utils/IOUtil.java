package com.almagway.common.utils;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * author: Beaven
 * date: 2017/10/21 15:38
 */

public class IOUtil {
    private IOUtil() {
    }

    // 缓冲区大小
    private static final int BUFFER_SIZE = 8192;

    public static boolean writeFileFromIs(final String filePath, final InputStream is) {
        return writeFileFromIS(FileUtil.getFile(filePath), is, false);
    }

    public static boolean writeFileFromIs(final String filePath, final InputStream is, final boolean append) {
        return writeFileFromIS(FileUtil.getFile(filePath), is, append);
    }

    /**
     * 关闭IO
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将输入流写入文件
     *
     * @param file   文件
     * @param is     输入流
     * @param append 是否追加在文件末
     * @return {@code true}: 写入成功<br>{@code false}: 写入失败
     */
    private static boolean writeFileFromIS(final File file, final InputStream is, final boolean append) {
        if (!FileUtil.createOrExistsFile(file) || is == null) return false;
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, append));
            byte data[] = new byte[BUFFER_SIZE];
            int len;
            while ((len = is.read(data, 0, BUFFER_SIZE)) != -1) {
                os.write(data, 0, len);
            }
            os.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(is, os);
        }
    }

}
