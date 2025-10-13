package org.aurifolia.cloud.common.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 重试
 *
 * @author Peng Dan
 * @since 1.0
 */
public class RetryFunction {
    /**
     * 最大抖动时间
     */
    private static final long MAX_JITTER_MILLIS = 100;

    /**
     * 重试
     *
     * @param retryTimes 重试次数
     * @param supplier 重试方法
     * @param <T> 返回值类型
     * @return 返回值
     */
    public static <T> T retry(int retryTimes, Supplier<T> supplier) {
        return retry(retryTimes, 0L, false, supplier);
    }

    /**
     * 重试
     *
     * @param retryTimes 重试次数
     * @param sleepMillis 休眠时间
     * @param jitter 是否抖动休眠时间
     * @param supplier 重试方法
     * @param <T> 返回值类型
     * @return 返回值
     */
    public static <T> T retry(int retryTimes, long sleepMillis, boolean jitter, Supplier<T> supplier) {
        Exception exception = null;
        for (int i = 0; i < retryTimes; i++) {
            try {
                return supplier.get();
            } catch (Exception e) {
                exception = e;
                long sleepTime = sleepMillis;
                if (jitter) {
                    sleepMillis += ThreadLocalRandom.current().nextLong(MAX_JITTER_MILLIS);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        throw new RuntimeException(exception);
    }
}
