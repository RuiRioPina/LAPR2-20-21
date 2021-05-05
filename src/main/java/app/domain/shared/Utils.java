package app.domain.shared;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static int countDigits;
    private static int countUppercase;
    private static int countLowerCase;
    public static long digits(long num){
        long digits=0;
        do {
            num=num/10;
            digits++;
        }while (num!=0);
        return digits;
    }
    public static boolean isLeapYear(int year){
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
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
}
