package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
}

