package org.example;

import junit.framework.TestCase;

public class AppTest extends TestCase {

    public void testUnboxStringOnlyRepeatString() {

        //Проверка развертывания строки состоящей только из частей кторые надо повторить

        assertEquals("xyzxyzxyzfdsfdsfdsfds", App.unboxString("3[xyz]4[fds]"));
        assertEquals("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyzfdsfdsfdsfds", App.unboxString("10[xyz]4[fds]"));
        assertEquals("xyzfdsfdsfdsfdsfdsfdsfdsfdsfdsfdsfds", App.unboxString("1[xyz]11[fds]"));
    }

    public void testUnboxStringOnlySimpleString() {

        //Проверка развертывания строки не имеющей повторяющихся частей

        assertEquals("abcdef", App.unboxString("abcdef"));

    }

    public void testUnboxStringMixString() {

        //Проверка развертывания строки состоящей из частей кторые надо повторить и не повторяющиеся части

        String actual = App.unboxString("3[xyz]4[fds]rt");
        String expected = "xyzxyzxyzfdsfdsfdsfdsrt";
        assertEquals(expected, actual);

    }

    public void testUnboxStringNestedString() {

        //Проверка развертывания строки состоящей из частей кторые надо повторить и они имеют вложенность для повторения

        String actual = App.unboxString("2[2[a]xyz]d");
        String expected = "aaxyzaaxyzd";
        assertEquals(expected, actual);
    }

    public void testUnboxStringMixNestedString() {
        String actual = App.unboxString("dfa2[2[a]xyz]rt");
        String expected = "dfaaaxyzaaxyzrt";
        assertEquals(expected, actual);
    }

    public void testUnboxStringNotValid() {
        assertEquals("Строка не валидна", App.unboxString(""));

        assertEquals("Строка не валидна", App.unboxString("3[xyz]4]fds]"));
        assertEquals("Строка не валидна", App.unboxString("3[]4[]fds]"));
        assertEquals("Строка не валидна", App.unboxString("3][4][fds]"));
        assertEquals("Строка не валидна", App.unboxString("dfa2[2[a]xyzrt"));
        assertEquals("Строка не валидна", App.unboxString("dfa2[2]a]xyzrt"));
        assertEquals("Строка не валидна", App.unboxString("dfa2]2[a]xyzrt"));
        assertEquals("Строка не валидна", App.unboxString("!dfa2[2[a]xyz]rt"));
    }
}