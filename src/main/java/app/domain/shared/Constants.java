package app.domain.shared;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_MEDICAL_LAB_TECHNICIAN = "MEDICAL LAB TECHNICIAN";
    public static final String ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST = "CLINICAL CHEMISTRY TECHNOLOGIST";
    public static final String ROLE_LABORATORY_COORDINATOR = "LABORATORY COORDINATOR";
    public static final String ROLE_SPECIALIST_DOCTOR = "SPECIALIST DOCTOR";

    public static final long CCN_NUMBER_OF_DIGITS = 16;
    public static final long NHS_NUMBER_OF_DIGITS = 10;
    public static final long TIN_NUMBER_OF_DIGITS = 10;
    public static final long PHONE_NUMBER_OF_DIGITS = 11;

    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
}
