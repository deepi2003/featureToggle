package org.deepti.featuretoggles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FeatureToggleControllerTest {

    @MockBean
    FeatureToggleConfig config;

    @Test
    void shouldReturnFeatureToggles() throws Exception {
        Map<String, Boolean> featureToggles = new HashMap<>() {
            {
                put("someFeature", true);
                put("someOtherFeature", false);
            }
        };
        when(config.getFeatures()).thenReturn(featureToggles);
        var controller = new FeatureToggleController(config);
        var actual = controller.getFeatures();
        assertEquals(featureToggles, actual);
    }
}