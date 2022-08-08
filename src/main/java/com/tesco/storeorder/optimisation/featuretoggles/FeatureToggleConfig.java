package org.deepti.featuretoggles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties
public class FeatureToggleConfig {
    private Map<String, Boolean> featureToggles = new HashMap<>();

    public Map<String, Boolean> getFeatures() {
        return featureToggles;
    }

    public void setFeatureToggles(Map<String, Boolean> featureToggles) {
        this.featureToggles = featureToggles;
    }
}
