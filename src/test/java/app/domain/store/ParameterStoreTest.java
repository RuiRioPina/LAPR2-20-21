package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertEquals(p1,p1);
    }

    @Test
    public void validateParameter() {
    }

    @Test
    public void saveParameter() {
    }
}

