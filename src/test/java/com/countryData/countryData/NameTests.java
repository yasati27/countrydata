package com.countryData.countryData;

import com.countryData.countryData.entity.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameTests {

    // Test Constructor method
    @Test
    public void test_Constructor() {
        // Test constructor and getters
        String commonName = "India";
        String officialName = "India";
        Name name = new Name(commonName, officialName);
        assertEquals(commonName, name.getCommon());
        assertEquals(officialName, name.getOfficial());
    }

    // Test toString method
    @Test
    public void testToString() {
        String commonName = "India";
        String officialName = "India";
        Name name = new Name(commonName, officialName);

    String expectedString = "Name(common=" + commonName + ", official=" + officialName + ")";
    assertEquals(expectedString, name.toString());
}
    // Test equals and hashCode methods
    @Test
    public void test_EqualsAndHashCode() {

        String commonName1 = "India";
        String officialName1 = "India";
        String commonName2 = "Netherlands";
        String officialName2 = "Netherlands";

        Name name1 = new Name(commonName1, officialName1);
        Name name2 = new Name(commonName1, officialName1);

        // assert equals method
        assertEquals(name1, name2);
        // assert hashCode method
        assertEquals(name1.hashCode(), name2.hashCode());

    }
}