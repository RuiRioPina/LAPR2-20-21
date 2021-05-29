package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReportTest {

    @Test
    public void getReport() {
        Report rep=new Report("Report test");
        String report = "Report test";
        String wrongReport = "Not Report test";

        assertEquals(report,rep.getReport());
        assertNotEquals(wrongReport,rep.getReport());

    }
}