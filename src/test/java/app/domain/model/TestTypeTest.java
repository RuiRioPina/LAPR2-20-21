package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestTypeTest {

    @Test
    public void getDescription() {
    	List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        
        TestType tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        String description = "uma descri��o";
        assertEquals(tt.getDescription(), description);
    }

    @Test
    public void getCollectingMethod() {
    	List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        
        TestType tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        String collectingMethod = "colheita";
        assertEquals(tt.getCollectingMethod(), collectingMethod);
    }
    
    @Test
    public void getCode() {
    	List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        
        TestType tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        String code = "54321";
        assertEquals(tt.getCode(), code);
    }
    
    @Test
    public void getParameterCategories() {
    	List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        
        TestType tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        
        List<ParameterCategory> ttCategories = tt.getParameterCategories();
        
        assertArrayEquals(parameterCategories.toArray(), ttCategories.toArray());
    }

    @Test
    public void testToString() {
    	List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);
        
        TestType tt = new TestType("54321","uma descri��o", "colheita", parameterCategories);
        String tts ="54321 - uma descri��o - colheita - 54321 | HEMOGRAM";
        assertEquals(tt.toString(),tts);
    }
}