package org.deepti.featuretoggles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

    @Test
    void shouldDecapitaliseString() {
        assertEquals("someRandomText", Strings.decapitalize("SomeRandomText"));
    }

    @Test
    void shouldBeAbleToCreateStrings() {
        assertNotNull(new Strings()); //jacoco needs this
    }
}