package org.deepti.featuretoggles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FeatureToggleController {

    final org.deepti.featuretoggles.FeatureToggleConfig config;

    public FeatureToggleController(org.deepti.featuretoggles.FeatureToggleConfig config) {
        this.config = config;
    }

    @GetMapping(path = "/v1/features")
    public Map<String, Boolean> getFeatures() {
       return config.getFeatures();
    }
}
