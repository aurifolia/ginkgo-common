package org.aurifolia.cloud.common.core.annotation;

import org.aurifolia.cloud.common.core.condition.OnPropertyPrefix;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 条件注解. 用于判断配置文件里某个key的前缀是否存在
 *
 * @author Peng Dan
 * @since 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnPropertyPrefix.class)
public @interface ConditionalOnPropertyPrefix {
    /**
     * 配置项的key
     *
     * @return key
     */
    String value();
}
