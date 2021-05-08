package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterStoreTest {

    @Test
    public void createParameter() {
        ParameterStore ps = new ParameterStore();

        String code = "12345";
        String shortname = "RBC";
        String description = "RED BLOOD CELLS";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);

        Object obj = new Object();

        Parameter p1 = ps.createParameter(code,shortname,description,parameterCategories);

        Assert.assertNotEquals(ps.createParameter(code,shortname,description,parameterCategories), obj);
        Assert.assertNotEquals(p1, null);
        assertEquals(p1,p1);
    }

    @Test
    public void validateParameter() {
        ParameterStore ps = new ParameterStore();

        String code = "12345";
        String shortname = "RBC";
        String description = "RED BLOOD CELLS";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter(code,shortname,description,parameterCategories);
        Parameter p2 = ps.createParameter("23a51","RBC","RED BLOOD CELLS",parameterCategories);

        ps.validateParameter(p1);
        ps.validateParameter(p2);
        ps.saveParameter(p1);
        ps.saveParameter(p2);

        boolean expected = true;

        boolean actual1 = ps.isParameterInList(p1);
        boolean actual2 = ps.isParameterInList(p2);

        assertEquals(expected,actual1);
        assertEquals(expected, actual2);
    }

    @Test
    public void saveParameter() {
        ParameterStore ps = new ParameterStore();

        String code = "12345";
        String shortname = "RBC";
        String description = "RED BLOOD CELLS";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter(code,shortname,description,parameterCategories);
        Parameter p2 = ps.createParameter("23a51","RBC","RED BLOOD CELLS",parameterCategories);

        ps.saveParameter(p1);
        ps.saveParameter(p2);

        boolean expected = true;
        boolean unexpected = false;

        boolean actual1 = ps.isParameterInList(p1);
        boolean actual2 = ps.isParameterInList(p2);

        assertEquals(expected,actual1);
        assertEquals(expected, actual2);
        assertNotEquals(unexpected,actual1);
    }

    @Test
    public void isParameterInList(){
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p2 = ps.createParameter("23a51","RBC","RED BLOOD CELLS",parameterCategories);
        Parameter p1 = ps.createParameter("54871","RBC","RED BLOOD CELLS",parameterCategories);

        ps.saveParameter(p2);
        ps.isParameterInList(p2);

        boolean actual1 = ps.isParameterInList(p2);
        boolean actual2 = ps.isParameterInList(p1);
        boolean expected = true;

        assertEquals(expected,actual1);
        assertNotEquals(expected,actual2);
        assertNotEquals(null,actual1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkCodeRulesAlpha() {
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter("%%%%%%","RBC","RED BLOOD CELLS",parameterCategories);

        ps.checkCodeRules(p1.getCode());

    }

    @Test (expected = IllegalArgumentException.class)
    public void checkCodeRules() {
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("5421","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter("1234","RBC","RED BLOOD CELLS",parameterCategories);

        ps.checkCodeRules(p1.getCode());

    }

    @Test (expected = IllegalArgumentException.class)
    public void checkShortnameRules() {
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("5421","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter("12345","","RED BLOOD CELLS",parameterCategories);

        ps.checkShortnameRules(p1.getShortname());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkDescriptionRules() {
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("5421","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter("12345","RBC","",parameterCategories);

        ps.checkDescriptionRules(p1.getDescription());
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkParameterCategoryRules() {
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();

        Parameter p1 = ps.createParameter("12345","RBC","RED BLOOD CELLS",parameterCategories);

        ps.checkParameterCategoryRules(p1.getPc());
    }

    @Test
    public void testGetParameterByCode() {
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("5421","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter("12345","RBC","RED BLOOD CELLS",parameterCategories);

        List <Parameter> parameter = new ArrayList<>();

        parameter.add(p1);

        ps.getParameterByCode("12345");

        String s = "12345";
        assertEquals(ps.getParameterByCode("12345"), ps.getParameterByCode(s));
    }
}


