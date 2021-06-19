package app.controller;

import app.domain.model.Parameter;

import app.domain.model.ParameterCategory;
import app.domain.store.ParameterStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterControllerTest {

    @Test
    public void createParameter() {
        ParameterController parameterController = new ParameterController();

        List<String> parameterCategories = new ArrayList<>();
        String pc = "54321 - HEMOGRAM";
        parameterCategories.add(pc);

        Parameter p1 = parameterController.createParameter("54345", "RBC", "RED BLOOD CELLS", parameterCategories);
        Parameter p2 = parameterController.createParameter("abcde", "RBC", "RED BLOOD CELLS", parameterCategories);
        assertNotEquals(p1, p2);
    }

    @Test
    public void saveParameter() {
        ParameterController parameterController = new ParameterController();
        ParameterStore ps = new ParameterStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("5421","HEMOGRAM");
        parameterCategories.add(pc);

        Parameter p1 = ps.createParameter("12344","RBC","RED BLOOD CELLS",parameterCategories);

        parameterController.saveParameter(p1);
        ps.saveParameter(p1);

        boolean actual1 = ps.isParameterInList(p1);
        boolean expected = true;
        boolean unexpected = false;

        assertEquals(expected, actual1);
        assertNotEquals(unexpected, actual1);
    }

    @Test
    public void getParameterCategories() {
        List<ParameterCategory> pc = App.getInstance().getCompany().getParameterCategoryStore().getParameterCategories();
        ParameterController parameterController = new ParameterController();

        List<ParameterCategory> pcList = parameterController.getParameterCategories();

        assertArrayEquals(pc.toArray(), pcList.toArray());
    }
}