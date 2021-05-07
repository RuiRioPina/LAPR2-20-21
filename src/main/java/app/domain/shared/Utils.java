package app.domain.shared;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static int countDigits;
    private static int countUppercase;
    private static int countLowerCase;


    public static boolean isLeapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    public static boolean dayValidation(int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        switch (monthOfBirth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (dayOfBirth > 31) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dayOfBirth > 30) {
                    return false;
                }
                break;
            case 2:
                if (dayOfBirth > 29) {
                    return false;
                } else if (dayOfBirth == 29 && !isLeapYear(yearOfBirth)) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static List<Character> randomCharacter(int maxNumberOfDigits, int maxNumberOfUppercaseLetters, int maxNumberOfLowercaseLetters) {
        int randomNumber;

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

    public static List<Character> randomCharacter(int numberOfDigits) {
        int randomNumber, count = 0;

        char numberCasted;
        List<Character> chars = new ArrayList<>();
        do {
            randomNumber = (int) (Math.random() * 62);

            if (randomNumber <= 9) {
                int number = randomNumber + 48;
                numberCasted = (char) number;
                chars.add(numberCasted);
                count++;
            } else if (randomNumber <= 35) {
                int uppercase = randomNumber + 55;
                numberCasted = (char) uppercase;
                chars.add(numberCasted);
                count++;
            } else {
                int lowercase = randomNumber + 61;
                numberCasted = (char) lowercase;
                chars.add(numberCasted);
                count++;
            }
        } while (count != numberOfDigits);
        return chars;
    }

    public static boolean isAlpha(String name) {
        return name.matches("^[\\p{L} .'-]+$");
    }
    public static boolean validateSOC(String SOC){return SOC.length()!=4 && !SOC.matches("[0-9]+");}
    public static boolean nameContainsDigits(String name){return  name.matches(".*\\d.*");}
}
