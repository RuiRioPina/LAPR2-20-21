package app.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    private static int countDigits;
    private static int countUppercase;
    private static int countLowerCase;

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    public static List<Character> randomCharacter() {
        int randomNumber, maxNumberOfDigits = 2, maxNumberOfUppercaseLetters = 3, maxNumberOfLowercaseLetters = 5;
        char numberCasted;
        List<Character> chars = new ArrayList<>();
        do {
            randomNumber = (int) (Math.random() * 62);
            int number = randomNumber + 48;
            numberCasted = (char) number;
            if (randomNumber <= 9) {
                chars.add(numberCasted);
                countDigits++;
            }
        } while (countDigits != maxNumberOfDigits || randomNumber > 9);

        do {
            randomNumber = (int) (Math.random() * 62);
            int uppercase = randomNumber + 55;

            if (randomNumber > 9 && randomNumber <= 35) {
                numberCasted = (char) uppercase;
                chars.add(numberCasted);
                countUppercase++;
            }
        } while (countUppercase != maxNumberOfUppercaseLetters || randomNumber <= 9 || randomNumber > 35);
        do {
            randomNumber = (int) (Math.random() * 62);

            int lowercase = randomNumber + 61;
            if (randomNumber >= 36 && randomNumber <= 61) {
                numberCasted = (char) lowercase;
                chars.add(numberCasted);
                countLowerCase++;
            }
        } while (countLowerCase != maxNumberOfLowercaseLetters || randomNumber < 36 || randomNumber > 61);
        return chars;
    }
}
