package cn.llsmpdroid.belief.log;

import android.annotation.TargetApi;
import android.os.Build;

/**
 * <p>Title:${type_inName}<p/>
 * <p>Description:<p/>
 * <p>Company: </p>
 *
 * @author litao
 * @mail llsmpsvn@gmail.com
 * @date on 2016/12/5
 */

public class XPrinter {
    static final int MAX_LENGTH_OF_SINGLE_MESSAGE = 3500;

    public static String lineSeparator = lineSeparator();

    public static void println(int logLevel, String tag, String msg) {
        if (msg.length() <= MAX_LENGTH_OF_SINGLE_MESSAGE) {
            printChunk(logLevel, tag, msg);
            return;
        }

        int msgLength = msg.length();
        int start = 0;
        int end = start + MAX_LENGTH_OF_SINGLE_MESSAGE;
        while (start < msgLength) {
            printChunk(logLevel, tag, msg.substring(start, end));

            start = end;
            end = Math.min(start + MAX_LENGTH_OF_SINGLE_MESSAGE, msgLength);
        }
    }

    private static void printChunk(int logLevel, String tag, String msg) {
        android.util.Log.println(logLevel, tag, msg);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String lineSeparator() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return System.lineSeparator();
            }
        } catch (Exception ignored) {
        }
        return "\n";
    }

}