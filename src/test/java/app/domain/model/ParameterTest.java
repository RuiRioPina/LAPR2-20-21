package app.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterTest {

    @Test
    public void getCode() {
        String code = "12345";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS",parameterCategories);

        assertEquals(code,p.getCode());
    }

    @Test
    public void getShortname() {
        String shortname = "RBC";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS", parameterCategories);

        assertEquals(shortname,p.getShortname());
    }

    @Test
    public void getDescription() {
        String description = "RED BLOOD CELLS";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS", parameterCategories);

        assertEquals(description,p.getDescription());
    }

    @Test
    public void getPc() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p2 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p2);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS", parameterCategories);

        assertEquals(parameterCategories,p.getPc());
    }
}