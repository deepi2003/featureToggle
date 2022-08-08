package org.deepti.featuretoggles;

import net.logstash.logback.encoder.org.apache.commons.lang3.BooleanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

import static org.deepti.featuretoggles.Strings.decapitalize;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.springframework.boot.autoconfigure.condition.ConditionOutcome.match;
import static org.springframework.boot.autoconfigure.condition.ConditionOutcome.noMatch;

public interface FeatureToggleConditionExtractor {
    @SuppressWarnings("unchecked")
    default ConditionOutcome featureToggleValue(ConditionContext context, AnnotatedTypeMetadata metadata, boolean forEnabled, String annotationClassName) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(annotationClassName);
        String name = ((Class<? extends Feature>) requireNonNull(attributes).get("value")).getSimpleName();
        name = decapitalize(name);
        boolean value = BooleanUtils.toBoolean(context.getEnvironment().getProperty(format("featureToggles.%s", name)));
        return (forEnabled == value) ? match() : noMatch(format("%s is a no match forEnabled=%s", name, forEnabled));
    }
}
