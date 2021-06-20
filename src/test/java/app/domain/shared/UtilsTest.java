package app.domain.shared;

import jdk.jshell.execution.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void isLeapYear() {
        assertTrue(Utils.isLeapYear(2000));
        assertFalse(Utils.isLeapYear(2001));
    }

    @Test
    public void dayValidation() {
        assertTrue(Utils.dayValidation(2002,04,25));
        assertTrue(Utils.dayValidation(2002,04,29));
        assertFalse(Utils.dayValidation(2002,04,58));
    }

    @Test
    public void randomCharacter() {
        assertNotEquals(Utils.randomCharacter(2), Utils.randomCharacter(2));
        assertNotEquals(Utils.randomCharacter(15), Utils.randomCharacter(15));
        assertNotEquals(Utils.randomCharacter(31), Utils.randomCharacter(31));
        assertNotEquals(Utils.randomCharacter(44), Utils.randomCharacter(44));
        List<Character> randomCharacter = new ArrayList<>();
        assertNotEquals(randomCharacter,Utils.randomCharacter(5,3,2));
    }


    @Test
    public void isAlpha() {
        assertFalse(Utils.isAlpha("Artt4"));
    }

    @Test
    public void validateSOC() {
        assertTrue(Utils.validateSOC("5A4A"));
    }

    @Test
    public void nameContainsDigits() {
        assertFalse(Utils.nameContainsDigits("ab"));
    }
}