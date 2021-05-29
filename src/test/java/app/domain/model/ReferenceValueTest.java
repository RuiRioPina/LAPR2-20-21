package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReferenceValueTest {

    @Test
    public void getMaxValue() {
        ReferenceValue referenceValue = new ReferenceValue("10e9L", 450, 150);
        double actual = referenceValue.getMaxValue();
        double expected = 450;
        assertEquals(expected,actual,0.01);
    }

    @Test
    public void getMinValue() {
        ReferenceValue referenceValue = new ReferenceValue("10e9L", 450, 150);
        double actual = referenceValue.getMinValue();
        double expected = 150;
        assertEquals(expected,actual,0.01);
    }
}