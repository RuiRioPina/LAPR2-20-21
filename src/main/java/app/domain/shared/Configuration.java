package app.domain.shared;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Configuration {

    private Configuration() {
        // Do nothing because there is no need to construct the UI layer with any value. This is only used to be able to use the UI when selecting in menus.
    }

    private static Properties props;

    static {
        props = new Properties();
        try {
            InputStream in = new FileInputStream("config.properties");
            props.load(in);
            in.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static String getBarcodeApiJarPath() {
        return props.getProperty("BarcodeApi.jarPath");
    }

    public static String getBarcodeApiCreator() {
        return props.getProperty("BarcodeApi.BarcodeCreator");
    }

    public static String getBarcodeApiObject() {
        return props.getProperty("BarcodeApi.BarcodeObject");
    }

    public static String getBarcodeApiImage() {
        return props.getProperty("BarcodeApi.BarcodeImage");
    }

    public static String getBarcodeApiCreatorMethod() {
        return props.getProperty("BarcodeApi.BarcodeCreatorMethod");
    }

    public static String getBarcodeApiObjectMethod() {
        return props.getProperty("BarcodeApi.BarcodeObjectSize");
    }

    public static String getBarcodeApiImageMethod() {
        return props.getProperty("BarcodeApi.BarcodeImageSaving");
    }

    public static String getAutomaticValidationCovid() {
        return props.getProperty("Automatic.Validation.Covid");
    }

    public static String getAutomaticValidationBlood() {
        return props.getProperty("Automatic.Validation.Blood");
    }
    /**
     * Get the sorting algorithm to sort the clients
     * @return string containg the sorting algorithm
     */
    public static String getSortingAlogrithm() {
        return props.getProperty("Algorithm.Sort");
    }

    /**
     * Getter for the type of date wanted
     * @return the type of date wanted (weeks/days)
     */
    public static String getTypeOfDate() {
        return props.getProperty("Automatic.Report.TypeOfDate");
    }
    /**
     * Get the number of historical points
     * @return string with the number of historical points
     */
    public static String getHistoricalPoints() {
        return props.getProperty("Automatic.Report.HistoricalPoints");
    }

    /**
     * Get the type of linear regression wanted
     * @return string with the type of linear regression wanted
     */

    public static String getTypeOfLinearRegression() {
        return props.getProperty("Automatic.Report.TypeOfLinearRegression");
    }

    /**
     * Getter for the significanceValue
     * @return significance value in properties file
     */
    public static String getSignificanceValue() {
        return props.getProperty("Automatic.Report.ConfidenceValue");
    }

    /**
     * Getter of the date in the configuration file
     * @param date date in the configuration file
     * @return array containg the 2 dates
     */
    private static String[] getDate(String date) {
        String[] arrString = null;

        if (date.contains("-")) {
            arrString = date.split("-");
        } else if (date.contains("/")) {
            arrString = date.split("/");
        }

        return arrString;
    }

    /**
     * Getter for the current date
     * @return an array of strings containg the date
     */
    public static String[] getCurrentDate() {
        String date = props.getProperty("Automatic.Report.CurrentDate");
        return getDate(date);
    }
    /**
     * Getter for the Lower Interval Date
     * @return an array of strings containg the date
     */
    public static String[] getDateIntervalLower() {
        String date = props.getProperty("Automatic.Report.DateInterval");
        String[] intervalDates = date.split(" ");
        return getDate(intervalDates[0]);
    }

    /**
     * Getter for the Upper Interval Date
     * @return an array of strings containg the date
     */
    public static String[] getDateIntervalUpper() {
        String date = props.getProperty("Automatic.Report.DateInterval");
        String[] intervalDates = date.split(" ");
        return getDate(intervalDates[1]);
    }


}