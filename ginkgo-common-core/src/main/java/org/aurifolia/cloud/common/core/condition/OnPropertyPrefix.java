package org.aurifolia.cloud.common.core.condition;


import org.aurifolia.cloud.common.core.annotation.ConditionalOnPropertyPrefix;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;

/**
 * 条件注解实现类, 用于判断配置文件里某个key是否存在
 *
 * @author Peng Dan
 * @since 1.0
 */
public class OnPropertyPrefix extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String key = metadata.getAnnotations().get(ConditionalOnPropertyPrefix.class).synthesize().value();
        ConfigurableEnvironment environment = (ConfigurableEnvironment) context.getEnvironment();
        boolean match = environment.getPropertySources().stream()
                .filter(propertySource -> propertySource instanceof EnumerablePropertySource)
                .map(propertySource -> (EnumerablePropertySource<?>) propertySource)
                .anyMatch(propertySource ->
                        Arrays.stream(propertySource.getPropertyNames())
                                .anyMatch(name -> name.startsWith(key)));
        return match ? ConditionOutcome.match() : ConditionOutcome.noMatch(String.format("Property %s exists", key));
    }
}
