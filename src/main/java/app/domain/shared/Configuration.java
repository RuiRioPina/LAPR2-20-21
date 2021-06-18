package app.domain.shared;

import java.io.FileInputStream;
import java.io.InputStream;
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

    public static String getSortingAlogrithm() {
        return props.getProperty("Algorithm.Sort");
    }

    public static String getCurrentDate() {
        return props.getProperty("Automatic.Report.CurrentDate");
    }

    public static String getTypeOfDate() {
        return props.getProperty("Automatic.Report.TypeOfDate");
    }

    public static String getHistoricalPoints() {
        return props.getProperty("Automatic.Report.HistoricalPoints");
    }

    public static String getDateInterval() {
        return props.getProperty("Automatic.Report.DateInterval");
    }

    public static String getTypeOfLinearRegression() {
        return props.getProperty("Automatic.Report.TypeOfLinearRegression");
    }

    public static String getIndependentVariable() {
        return props.getProperty("Automatic.Report.IndependentVariable");
    }

    public static String getSignificanceValue() {
        return props.getProperty("Automatic.Report.SignificanceValue");
    }
}
