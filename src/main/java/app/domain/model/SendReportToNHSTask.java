package app.domain.model;

import java.util.Calendar;
import java.util.TimerTask;

import app.controller.App;
import app.domain.model.matcp.LinearRegression;
import app.domain.model.matcp.MultiLinearRegression;
import app.domain.shared.Configuration;
import com.nhs.report.Report2NHS;

public class SendReportToNHSTask extends TimerTask {

    Report2NHS report2NHS = new Report2NHS();

    public SendReportToNHSTask() {

    }

    private String report;

    @Override
    public void run() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(2021, 04, 31);
        Calendar old = Calendar.getInstance();
        old.set(2021, 04, 3);
        Calendar newt = Calendar.getInstance();
        newt.set(2021, 04, 24);

        int historicalPoints = Integer.parseInt(Configuration.getHistoricalPoints());
        double significance = Double.parseDouble(Configuration.getSignificanceValue());


        Company company = App.getInstance().getCompany();

        if (Configuration.getTypeOfLinearRegression().equalsIgnoreCase("Multilinear")) {
            double[] x1 = App.getInstance().getCompany().getTestStore().getMeanAgeOfTestsPerformedPerDay(old, newt);
            double[] x2 = App.getInstance().getCompany().getTestStore().getPositiveCovidTestsPerformedOnDay(old, newt);
            double[] y = App.getInstance().getCompany().getTestStore().getTestsPerformedPerDay(old, newt);
            report = new NHSReport(new MultiLinearRegression(x1, x2, y), x1, x2, y).getReportString(old, newt, significance, 0, significance, 0, significance, significance, Configuration.getTypeOfDate());
        }else {

            String bestModelSLR = company.getTheBestModelSLR(currentDate, historicalPoints, newt, old, significance, 0, significance, 0, significance, significance);

            if (bestModelSLR.equalsIgnoreCase("meanAge")) {
                if (Configuration.getTypeOfDate().equalsIgnoreCase("Weeks")) {
                    report = company.generateSimpleNHSReportMeanAgeWeeks(currentDate, 4, newt, old, significance, 0, significance, 0, significance, significance);
                } else {
                    report = company.generateSimpleNhsReportMeanAge(currentDate, 15, newt, old, significance, 0, significance, 0, significance, significance);
                }
            } else {
                if (Configuration.getTypeOfDate().equalsIgnoreCase("Weeks")) {
                    report = company.generateSimpleNHSReportTestsPerformedWeeks(currentDate, 4, newt, old, significance, 0, significance, 0, significance, significance);
                } else {
                    report = company.generateSimpleNhsReportTestsPerformed(currentDate, 15, newt, old, significance, 0, significance, 0, significance, significance);
                }
            }
        }

        Report2NHS.writeUsingFileWriter(report);
    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

    @Override
    public long scheduledExecutionTime() {
        return super.scheduledExecutionTime();
    }


}
