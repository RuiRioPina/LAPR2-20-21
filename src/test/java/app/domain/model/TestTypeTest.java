package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestTypeTest {
    List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
    ParameterCategory pc;

    TestType tt;
    String tts;

    @Before
    public void setUp() throws Exception {
        pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        tts ="54321 - uma descri��o - colheita - 54321 | HEMOGRAM";
    }

    @Test
    public void getDescription() {

        String description = "uma descri��o";
        assertEquals(tt.getDescription(), description);
    }

    @Test
    public void getCollectingMethod() {

        String collectingMethod = "colheita";
        assertEquals(tt.getCollectingMethod(), collectingMethod);
    }
    
    @Test
    public void getCode() {

        String code = "54321";
        assertEquals(tt.getCode(), code);
    }
    
    @Test
    public void getParameterCategories() {
        
        List<ParameterCategory> ttCategories = tt.getParameterCategories();
        
        assertArrayEquals(parameterCategories.toArray(), ttCategories.toArray());
    }

    @Test
    public void testToString() {
    	List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        
        TestType tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        String tts ="54321 - uma descri��o - colheita - 54321 | HEMOGRAM";
        assertEquals(tt.toString(),tts);
    }
}