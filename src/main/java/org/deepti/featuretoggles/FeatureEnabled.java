package org.deepti.featuretoggles;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Conditional(value = org.deepti.featuretoggles.OnFeatureEnabledCondition.class)
public @interface FeatureEnabled {
    Class<? extends org.deepti.featuretoggles.Feature> value();
}
