package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryTest {

    @Test
    public void getName() {
        ParameterCategory pc1 = new ParameterCategory("54321","HEMOGRAM");
        String name = "HEMOGRAM";
        assertEquals(pc1.getName(),name);
    }

    @Test
    public void getCode() {
        ParameterCategory pc1 = new ParameterCategory("54321","HEMOGRAM");
        String code = "54321";
        assertEquals(pc1.getCode(), code);
    }

    @Test
    public void testToString() {
        ParameterCategory pc1 = new ParameterCategory("54321","HEMOGRAM");
        String tts ="54321 - HEMOGRAM";
        assertEquals(pc1.toString(),tts);
    }
}