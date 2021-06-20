package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Test;
import org.apache.commons.collections.ArrayStack;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class LabCoordinatorStore implements Serializable {

    static List<Test> tests;


    public Map<String, Integer> getNumberTestsByYear(List<Test> tests, Calendar sDate, Calendar eDate, String testsType) {
        Map<String, Integer> numberReadyTests = new TreeMap<>();
        int numberYears = 0;
        sDate.set(Calendar.HOUR_OF_DAY, 1);
        eDate.set(Calendar.HOUR_OF_DAY, 23);

        Calendar sDateContador = (Calendar) sDate.clone();

        if (sDate != null && eDate != null) {
            while (!(sDateContador.get(Calendar.YEAR) > (eDate.get(Calendar.YEAR)))) {
                sDateContador.add(Calendar.YEAR, 1);
                numberYears++;
            }

            sDateContador = (Calendar) sDate.clone();

            while (!(sDateContador.get(Calendar.YEAR) > (eDate.get(Calendar.YEAR)))) {
                String day = "";
                day = String.valueOf(sDateContador.get(Calendar.YEAR));
                numberReadyTests.put(day, getNumOfTReadyByYear(tests, sDateContador, testsType, sDate, eDate));
                sDateContador.add(Calendar.YEAR, 1);
            }
        }
        return numberReadyTests;

    }

    public Map<String, Integer> getNumberTestsByDay(List<Test> tests, Calendar sDate, Calendar eDate, String testsType) {
        Map<String, Integer> numberReadyTests = new TreeMap<>();
        int numberDays = 0;

        Calendar sDateContador = (Calendar) sDate.clone();
        eDate.set(Calendar.HOUR_OF_DAY, 23);

        if (sDate != null && eDate != null) {
            while (!(sDateContador.getTime().getTime() > (eDate.getTime().getTime()))) {
                sDateContador.add(Calendar.DAY_OF_MONTH, 1);
                numberDays++;
            }

            sDateContador = (Calendar) sDate.clone();

            while (!(sDateContador.getTime().getTime() > (eDate.getTime().getTime()))) {
                String day = "";
                day = String.valueOf(sDateContador.get(Calendar.DAY_OF_MONTH))
                        + "/"
                        + String.valueOf(sDateContador.get(Calendar.MONTH) + 1)
                        + "/"
                        + String.valueOf(sDateContador.get(Calendar.YEAR));
                numberReadyTests.put(day, getNumOfTReadyByDay(tests, sDateContador, testsType));
                sDateContador.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        return numberReadyTests;

    }

    public Map<String, Integer> getNumberTestsByMonth(List<Test> tests, Calendar sDate, Calendar eDate, String testsType) {
        Map<String, Integer> numberReadyTests = new TreeMap<>();
        int numberDays = 0;

        sDate.set(Calendar.HOUR_OF_DAY, 1);
        eDate.set(Calendar.HOUR_OF_DAY, 23);
        Calendar sDateContador = (Calendar) sDate.clone();

        if (sDate != null && eDate != null) {
            while (!(sDateContador.get(Calendar.MONTH) > (eDate.get(Calendar.MONTH)))) {
                sDateContador.add(Calendar.MONTH, 1);
                numberDays++;
            }

            sDateContador = (Calendar) sDate.clone();

            while (!(sDateContador.get(Calendar.MONTH) > (eDate.get(Calendar.MONTH)))) {
                String day = "";
                day = String.valueOf(sDateContador.get(Calendar.MONTH) + 1);
                numberReadyTests.put(day, getNumOfTReadyByMonth(tests, sDateContador, testsType, sDate, eDate));
                sDateContador.add(Calendar.MONTH, 1);
            }
        }
        return numberReadyTests;
    }

    public Map<String, Integer> getNumberTestsByWeek(List<Test> tests, Calendar sDate, Calendar eDate, String testsType) {
        Map<String, Integer> numberReadyTests = new TreeMap<>();
        int numberDays = 0;

        sDate.set(Calendar.HOUR_OF_DAY, 1);
        eDate.set(Calendar.HOUR_OF_DAY, 23);
        Calendar sDateContador = (Calendar) sDate.clone();

        if (sDate != null && eDate != null) {
            while (!(sDateContador.get(Calendar.WEEK_OF_YEAR) > (eDate.get(Calendar.WEEK_OF_YEAR)))) {
                sDateContador.add(Calendar.WEEK_OF_YEAR, 1);
                numberDays++;
            }

            sDateContador = (Calendar) sDate.clone();

            while (!(sDateContador.get(Calendar.WEEK_OF_YEAR) > (eDate.get(Calendar.WEEK_OF_YEAR)))) {
                String day = "";
                day = String.valueOf(sDateContador.get(Calendar.WEEK_OF_YEAR));
                numberReadyTests.put(day, getNumOfTReadyByWeek(tests, sDateContador, testsType, sDate, eDate));
                sDateContador.add(Calendar.WEEK_OF_YEAR, 1);
            }
        }
        return numberReadyTests;
    }

    public int getNumOfTReadyByMonth(List<Test> tests, Calendar date, String type, Calendar beginDate, Calendar endDate) {
        int listTests = 0;
        List<Client> lstClients = new ArrayList<>();
        for (Test t : tests) {

            if (type.equalsIgnoreCase("readyTests")) {
                Calendar test = t.getValidationDate();
                if (test != null && test.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                        && beginDate.getTime().getTime() <= test.getTime().getTime()
                        && test.getTime().getTime() <= endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("diagnosisTests")) {
                Calendar test = t.getDiagnosisDate();
                Calendar registrationDate = t.getRegistrationDate();
                if (test != null && test.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                        registrationDate.getTime().getTime() <= endDate.getTime().getTime() &&
                        test.getTime().getTime() > endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("missingResultsTests")) {
                Calendar test = t.getChemicalAnalysisDate();
                Calendar registrationDate = t.getRegistrationDate();
                if (test != null && test.get(Calendar.MONTH) == date.get(Calendar.MONTH) &&
                        registrationDate.getTime().getTime() <= endDate.getTime().getTime() &&
                        test.getTime().getTime() > endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("clients")) {
                Calendar test = t.getRegistrationDate();
                if (test != null && test.get(Calendar.MONTH) == date.get(Calendar.MONTH)
                        && beginDate.getTime().getTime() <= test.getTime().getTime()
                        && test.getTime().getTime() <= endDate.getTime().getTime()
                        && !containsClient(lstClients, t.getClient().getNhsNumber())) {
                    listTests++;
                    lstClients.add(t.getClient());
                }
            }

        }
        return listTests;
    }

    public int getNumOfTReadyByWeek(List<Test> tests, Calendar date, String type, Calendar beginDate, Calendar endDate) {
        int listTests = 0;
        List<Client> lstClients = new ArrayList<>();
        for (Test t : tests) {
            Calendar registrationDate = t.getRegistrationDate();
            if (type.equalsIgnoreCase("readyTests")) {
                Calendar test = t.getValidationDate();
                if (test != null && test.get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR) &&
                        beginDate.getTime().getTime() <= test.getTime().getTime() &&
                        test.getTime().getTime() <= endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("diagnosisTests")) {
                Calendar test = t.getDiagnosisDate();

                if (test != null &&
                        registrationDate.get(Calendar.WEEK_OF_YEAR) <= date.get(Calendar.WEEK_OF_YEAR) &&
                        test.get(Calendar.WEEK_OF_YEAR) > date.get(Calendar.WEEK_OF_YEAR)) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("missingResultsTests")) {
                Calendar test = t.getChemicalAnalysisDate();
                if (test != null && registrationDate.get(Calendar.WEEK_OF_YEAR) <= date.get(Calendar.WEEK_OF_YEAR) &&
                        test.get(Calendar.WEEK_OF_YEAR) > date.get(Calendar.WEEK_OF_YEAR)) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("clients")) {
                Calendar test = t.getRegistrationDate();
                if (test != null && test.get(Calendar.WEEK_OF_YEAR) == date.get(Calendar.WEEK_OF_YEAR) &&
                        beginDate.getTime().getTime() <= test.getTime().getTime() &&
                        test.getTime().getTime() <= endDate.getTime().getTime() &&
                        !containsClient(lstClients, t.getClient().getNhsNumber())) {
                    listTests++;
                    lstClients.add(t.getClient());
                }
            }

        }
        return listTests;
    }

    public int getNumOfTReadyByDay(List<Test> tests, Calendar currentDate, String type) {
        int listTests = 0;
        List<Client> lstClients = new ArrayList<>();
        for (Test t : tests) {

            if (type.equalsIgnoreCase("readyTests")) {
                Calendar test = t.getValidationDate();
                if (test != null &&
                        test.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH) &&
                        test.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                        test.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("diagnosisTests")) {
                Calendar testCreatedDate = t.getRegistrationDate();
                Calendar testDiagnosisDate = t.getDiagnosisDate();
                currentDate.set(Calendar.HOUR_OF_DAY, 23);
                if (testDiagnosisDate != null &&
                        testCreatedDate != null &&
                        testCreatedDate.getTime().getTime() <= currentDate.getTime().getTime() &&
                        testDiagnosisDate.getTime().getTime() > currentDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("missingResultsTests")) {
                Calendar testCreatedDate = t.getRegistrationDate();
                Calendar testResultsDate = t.getChemicalAnalysisDate();
                if (testResultsDate != null &&
                        testCreatedDate != null &&
                        testCreatedDate.getTime().getTime() <= currentDate.getTime().getTime() &&
                        testResultsDate.getTime().getTime() > currentDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("clients")) {
                Calendar test = t.getRegistrationDate();
                if (test != null &&
                        test.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH) &&
                        test.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                        test.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
                        !containsClient(lstClients, t.getClient().getNhsNumber())) {
                    listTests++;
                    lstClients.add(t.getClient());
                }
            }

        }
        return listTests;
    }

    public boolean containsClient(final List<Client> list, long nhs) {
        return list.stream().anyMatch(o -> o.getNhsNumber() == nhs);
    }

    public int getNumOfTReadyByYear(List<Test> tests, Calendar date, String type, Calendar beginDate, Calendar endDate) {
        int listTests = 0;
        List<Client> lstClients = new ArrayList<>();
        for (Test t : tests) {

            if (type.equalsIgnoreCase("readyTests")) {
                Calendar test = t.getValidationDate();
                if (test != null && test.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                        && beginDate.getTime().getTime() <= test.getTime().getTime()
                        && test.getTime().getTime() <= endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("diagnosisTests")) {
                Calendar test = t.getDiagnosisDate();
                Calendar registrationDate = t.getRegistrationDate();
                if (test != null &&
                        registrationDate.getTime().getTime() <= endDate.getTime().getTime() &&
                        test.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                        test.getTime().getTime() > endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("missingResultsTests")) {
                Calendar test = t.getChemicalAnalysisDate();
                Calendar registrationDate = t.getRegistrationDate();
                if (test != null &&
                        registrationDate.getTime().getTime() <= endDate.getTime().getTime() &&
                        test.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                        test.getTime().getTime() > endDate.getTime().getTime()) {
                    listTests++;
                }
            } else if (type.equalsIgnoreCase("clients")) {
                Calendar test = t.getRegistrationDate();
                if (test != null && test.get(Calendar.YEAR) == date.get(Calendar.YEAR)
                        && beginDate.getTime().getTime() <= test.getTime().getTime()
                        && test.getTime().getTime() <= endDate.getTime().getTime()
                        && !containsClient(lstClients, t.getClient().getNhsNumber())) {
                    listTests++;
                    lstClients.add(t.getClient());
                }
            }

        }
        return listTests;
    }

    public static List<Test> getTestsWithResults() {
        List<Test> complete = new ArrayList<>();
        for (Test t : tests) {
            if (t.getSamplesCollectionDate() != null && t.getChemicalAnalysisDate() != null && t.getDiagnosisDate() != null && t.getValidationDate() != null) {
                complete.add(t);
            }
        }
        return complete;
    }


    public static int[] maxSubArraySum(int[] list) {
        int size = list.length;
        int max_so_far = Integer.MIN_VALUE,
                max_ending_here = 0, start = 0,
                end = 0, s = 0;
        for (int i = 0; i < size; i++) {
            max_ending_here += list[i];

            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
                start = s;
                end = i;
            }

            if (max_ending_here < 0) {
                max_ending_here = 0;
                s = i + 1;
            }
        }

        int[] places = new int[end - start + 1];
        for (int i = 0; i < places.length; i++) {
            places[i] = start;
            start++;
        }

        return places;
    }

    public static int[] listMax(Calendar start, Calendar end, List<Test> tests) {

        String message = "There is no tests registered";

        try {
            int dayDif = 24 * (end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH) + 1);
            int gapInMinutes = 30;
            int[] dif = new int[dayDif];
            int count = 0;
            int loops = ((int) Duration.ofHours(12).toMinutes() / gapInMinutes);
            int day = start.get(Calendar.DAY_OF_MONTH);
            int lDay = end.get(Calendar.DAY_OF_MONTH);
            List<Test> testsReg = tests;

            //List<Test> testsVal = getTestsWithResults();
            for (; day <= lDay; day++) {
                start.set(Calendar.DAY_OF_MONTH, day);
                end.set(Calendar.DAY_OF_MONTH, day);
                if (start.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    start.add(Calendar.DAY_OF_MONTH, 1);
                    end.add(Calendar.DAY_OF_MONTH, 1);
                }
                LocalDateTime intStart = tCalendarToLDT(start);
                LocalDateTime intFinal = tCalendarToLDT(end);

                int i = 1;
                List<LocalTime> times = new ArrayList<>(loops);
                List<LocalTime> timesf = new ArrayList<>(loops);
                LocalTime time = LocalTime.MIN.plusHours(8);
                LocalTime timef = LocalTime.MIN.plusHours(8).plusMinutes(30);
                for (; i <= loops; i++) {

                    times.add(time);
                    timesf.add(timef);
                    LocalTime time1 = time;
                    LocalTime time2 = timef;

                    LocalDateTime local = time1.atDate(LocalDate.from(intStart));
                    LocalDateTime local1 = time2.atDate(LocalDate.from(intFinal));

                    for (Test t : testsReg) {
                        Calendar test = t.getRegistrationDate();

                        if (tCalendarToLDT(test).isAfter(local) && tCalendarToLDT(test).isBefore(local1)) {
                            dif[count]++;
                        }
                    }

/*
                        for (Test t:testsVal) {
                            Calendar test=t.getValidationDate();

                            if (tCalendarToLDT(test).isAfter(local) && tCalendarToLDT(test).isBefore(local1)) {
                                dif[count]--;
                            }
                        }
*/


                    time = time.plusMinutes(gapInMinutes);
                    timef = timef.plusMinutes(gapInMinutes);
                    count++;
                }
            }
            return dif;

        } catch (NullPointerException e) {
            System.out.println(message);
            return null;
        }
    }


    public static LocalDateTime tCalendarToLDT(Calendar date) {
        TimeZone tz = date.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDateTime.ofInstant(date.toInstant(), zid);
    }


    public static List<LocalDateTime> getMax(Calendar start, Calendar end, int[] sum, int dif) {
        String message = "There is no tests registered";

        StringBuilder max = new StringBuilder();
        List<LocalDateTime> limits = new ArrayList<>();
        try {
            int len = sum.length;
            int gapInMinutes = 30;
            int count = 0;
            int countPlace = 0;
            int loops = ((int) Duration.ofHours(12).toMinutes() / gapInMinutes);
            int day = start.get(Calendar.DAY_OF_MONTH) - dif;
            int lDay = end.get(Calendar.DAY_OF_MONTH);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (; day <= lDay; day++) {
                start.set(Calendar.DAY_OF_MONTH, day);
                end.set(Calendar.DAY_OF_MONTH, day);
                if (start.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    start.add(Calendar.DAY_OF_MONTH, 1);
                    end.add(Calendar.DAY_OF_MONTH, 1);
                }
                TimeZone tz = start.getTimeZone();
                ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
                LocalDateTime localDate = LocalDateTime.ofInstant(start.toInstant(), zid);

                TimeZone tz1 = end.getTimeZone();
                ZoneId zid1 = tz1 == null ? ZoneId.systemDefault() : tz1.toZoneId();
                LocalDateTime localDate1 = LocalDateTime.ofInstant(end.toInstant(), zid1);
                int i = 1;
                List<LocalTime> times = new ArrayList<>(loops);
                List<LocalTime> timesf = new ArrayList<>(loops);
                LocalTime time = LocalTime.MIN.plusHours(8);
                LocalTime timef = LocalTime.MIN.plusHours(8).plusMinutes(30);
                for (; i <= loops; i++) {

                    times.add(time);
                    timesf.add(timef);
                    LocalTime time1 = time;
                    LocalTime time2 = timef;

                    LocalDateTime local = time1.atDate(LocalDate.from(localDate));
                    LocalDateTime local1 = time2.atDate(LocalDate.from(localDate1));

                    if (sum[0] == count || sum[sum.length - 1] == count) {
                        if (count == sum[0]) {
                            limits.add(local);
                        } else {
                            limits.add(local1);
                        }
                    }

                    time = time.plusMinutes(gapInMinutes);
                    timef = timef.plusMinutes(gapInMinutes);
                    count++;


                }

            }
            return limits;

        } catch (NullPointerException e) {
            System.out.println(message);
            return null;
        }
    }


    public static int countNumber(int num) {
        int count = 0;

        while (num != 0) {
            num = num / 10;
            count++;
        }
        return count;
    }

    public static int[] maxSubArray(int[] nums) {

      /*  int [] numeros = new int[3];
        numeros[0] = 2;
        numeros[1] = -1;
        numeros[2] = 3;*/


        int n = nums.length;
        int maximumSubArraySum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int count = 0;

        for (int left = 0; left < n; left++) {

            int runningWindowSum = 0;

            for (int right = left; right < n; right++) {
                runningWindowSum += nums[right];

                if (runningWindowSum > maximumSubArraySum) {
                    maximumSubArraySum = runningWindowSum;
                    start = left;
                    end = right;
                }
            }
        }
        int[] limits = new int[2];
        limits[0] = start;
        limits[1] = end;
        int[] result = new int[limits[1] - limits[0] + 1];
        int i = limits[0];
        int j = limits[1];
        while (i <= j) {
            result[count] = nums[i];
            i++;
            count++;
        }
        return result;
    }


}
