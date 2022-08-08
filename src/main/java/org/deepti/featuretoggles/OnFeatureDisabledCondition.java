package org.deepti.featuretoggles;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnFeatureDisabledCondition extends SpringBootCondition implements org.deepti.featuretoggles.FeatureToggleConditionExtractor {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return featureToggleValue(context, metadata, false, FeatureDisabled.class.getName());
    }
}
