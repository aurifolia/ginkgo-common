package org.aurifolia.cloud.common.utils;

import java.util.concurrent.TimeUnit;

/**
 * 线程相关的工具
 *
 * @author Peng Dan
 * @since 1.0
 */
public class ThreadUtil {
    /**
     * 睡眠
     *
     * @param value 睡眠时间
     * @param timeUnit 时间单位
     */
    public static void sleep(long value, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(value);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
