package org.deepti.featuretoggles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = {org.deepti.featuretoggles.FeatureToggleConfig.class, FeatureToggleTest.SampleConfiguration.class})
@TestPropertySource(properties = {"featureToggles.someFeature=true", "featureToggles.someOtherFeature=false"})
public class FeatureToggleTest {

    @Autowired
    org.deepti.featuretoggles.FeatureToggleConfig config;

    @Autowired
    @Qualifier("nameBean")
    String name;

    @Autowired
    @Qualifier("ageBean")
    int age;

    @Autowired
    @Qualifier("colorBean")
    COLOR color;

    @Autowired
    @Qualifier("cityBean")
    String city;

    @Autowired
    @Qualifier("countryBean")
    String country;

    @Autowired
    @Qualifier("currencyBean")
    String currency;

    @Test
    void shouldBeAbleToGetAllFeatureToggles() {
        assertEquals(2, config.getFeatures().size());
    }

    @Test
    void shouldBeAbleToUseFeatureTogglesInIfElse() {
        var flag = config.getFeatures().get("someFeature");
        assertTrue(flag);
    }

    @Test
    void shouldBeAbleToInitializeBeanBasedOnFeatureToggle() {
        assertEquals("primaryName", name);
        assertEquals(2, age);
    }

    @Test
    void shouldBeAbleToDefaultToFalseForANonExistentFeatureToggle() {
        assertEquals(COLOR.RED, color);
    }

    @Test
    void shouldBeAbleToDefaultToFalseForADisabledNonExistentFeatureToggle() {
        assertEquals("Tokyo", city);
    }

    @Test
    void shouldBeAbleToSkipADisabledFeatureToggle() {
        assertEquals("Japan", country);
    }

    @Test
    void shouldBeAbleToUseAnEnabledFeatureToggle() {
        assertEquals("USD", currency);
    }

    class SomeFeature implements Feature {

    }

    class SomeOtherFeature implements Feature {

    }

    class SomeThirdFeature implements Feature {

    }

    public enum COLOR {
        RED, BLUE
    }

    @Configuration
    public static class SampleConfiguration {

        @Bean
        @FeatureEnabled(SomeFeature.class)
        @Qualifier("nameBean")
        @Primary
        public String primaryName() {
            return "primaryName";
        }

        @Bean
        @Qualifier("nameBean")
        public String secondaryName() {
            return "secondaryName";
        }

        @Bean
        @FeatureEnabled(SomeOtherFeature.class)
        @Qualifier("ageBean")
        @Primary
        public int primaryAge() {
            return 1;
        }

        @Bean
        @Qualifier("ageBean")
        public int secondaryAge() {
            return 2;
        }

        @Bean
        @FeatureEnabled(SomeThirdFeature.class)
        @Qualifier("colorBean")
        @Primary
        public COLOR primaryColor() {
            return COLOR.BLUE;
        }

        @Bean
        @Qualifier("colorBean")
        public COLOR secondaryColor() {
            return COLOR.RED;
        }

        @Bean
        @Qualifier("cityBean")
        public String primaryCity() {
            return "NYC";
        }

        @Bean
        @FeatureDisabled(SomeThirdFeature.class)
        @Primary
        @Qualifier("cityBean")
        public String secondaryCity() {
            return "Tokyo";
        }

        @Bean
        @Qualifier("countryBean")
        public String primaryCountry() {
            return "USA";
        }

        @Bean
        @Primary
        @FeatureDisabled(SomeOtherFeature.class)
        @Qualifier("countryBean")
        public String secondaryCountry() {
            return "Japan";
        }

        @Bean
        @Qualifier("currencyBean")
        public String primaryCurrency() {
            return "USD";
        }

        @Bean
        @Primary
        @FeatureDisabled(SomeFeature.class)
        @Qualifier("currencyBean")
        public String secondaryCurrency() {
            return "JPY";
        }
    }
}
