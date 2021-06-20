package app.domain.model;

import java.util.Calendar;
import java.util.TimerTask;

import app.controller.App;
import app.domain.model.matcp.MultiLinearRegression;
import app.domain.shared.Configuration;
import com.nhs.report.Report2NHS;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;

/**
 * This class instanciates an object from the NHSReport class passing through all the parameters defined by configuration file and which is to be used to schedule the sending of the Report to the NHS
 */
public class SendReportToNHSTask extends TimerTask {


    /**
     * Default constructor of the SendReportToNHSTask
     */
    public SendReportToNHSTask() {
        //there is no need to initialize anything in the default constructor
    }

    /**
     * The string containing the report
     */
    private String report;

    /**
     * run method which is used to schedule the sending of the report to the NHS. This gets the properties defined in the configuration file and allocates to construct the respective report.
     */

    @Override
    public void run() {
        Calendar currentDate = Calendar.getInstance();
        int dayCurrentDate = Integer.parseInt(Configuration.getCurrentDate()[0]);
        int monthCurrentDate = Integer.parseInt(Configuration.getCurrentDate()[1]) - 1;
        int yearCurrentDate = Integer.parseInt(Configuration.getCurrentDate()[2]);
        int dayLowerInterval = Integer.parseInt(Configuration.getDateIntervalLower()[0]);
        int monthLowerInterval = Integer.parseInt(Configuration.getDateIntervalLower()[1]) - 1;
        int yearLowerInterval = Integer.parseInt(Configuration.getDateIntervalLower()[2]);
        int dayUpperInterval = Integer.parseInt(Configuration.getDateIntervalUpper()[0]);
        int monthUpperInterval = Integer.parseInt(Configuration.getDateIntervalUpper()[1]) - 1;
        int yearUpperInterval = Integer.parseInt(Configuration.getDateIntervalUpper()[2]);
        if (monthCurrentDate == -1) {
            monthCurrentDate = 0;
        }
        currentDate.set(yearCurrentDate, monthCurrentDate, dayCurrentDate);
        Calendar old = Calendar.getInstance();
        old.set(yearLowerInterval, monthLowerInterval, dayLowerInterval);
        Calendar newt = Calendar.getInstance();
        newt.set(yearUpperInterval, monthUpperInterval, dayUpperInterval);

        int historicalPoints = Integer.parseInt(Configuration.getHistoricalPoints());
        double significance = Double.parseDouble(Configuration.getSignificanceValue());


        Company company = App.getInstance().getCompany();
        try {
            if (Configuration.getTypeOfLinearRegression().equalsIgnoreCase("Multilinear")) {
                double[] x1 = App.getInstance().getCompany().getTestStore().getMeanAgeOfTestsPerformedPerDay(old, newt);
                double[] x2 = App.getInstance().getCompany().getTestStore().getPositiveCovidTestsPerformedOnDay(old, newt);
                double[] y = App.getInstance().getCompany().getTestStore().getTestsPerformedPerDay(old, newt);
                report = new NHSReport(new MultiLinearRegression(x1, x2, y), x1, x2, y).getReportString(old, newt, significance, 0, significance, 0, significance, significance, Configuration.getTypeOfDate());
            } else {

                String bestModelSLR = company.getTheBestModelSLR(currentDate, historicalPoints, newt, old, significance, 0, significance, 0, significance, significance);

                if (bestModelSLR.equalsIgnoreCase("meanAge")) {
                    if (Configuration.getTypeOfDate().equalsIgnoreCase("Weeks")) {
                        report = company.generateSimpleNHSReportMeanAgeWeeks(currentDate, historicalPoints, newt, old, significance, 0, significance, 0, significance, significance);
                    } else {
                        report = company.generateSimpleNhsReportMeanAge(currentDate, historicalPoints, newt, old, significance, 0, significance, 0, significance, significance);
                    }
                } else {
                    if (Configuration.getTypeOfDate().equalsIgnoreCase("Weeks")) {
                        report = company.generateSimpleNHSReportTestsPerformedWeeks(currentDate, historicalPoints, newt, old, significance, 0, significance, 0, significance, significance);
                    } else {
                        report = company.generateSimpleNhsReportTestsPerformed(currentDate, historicalPoints, newt, old, significance, 0, significance, 0, significance, significance);
                    }
                }
            }
            Report2NHS.writeUsingFileWriter(report);
        } catch (NotStrictlyPositiveException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("You must insert an interval of dates superior than 2 days/weeks.");
        }


    }


}
