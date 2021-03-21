package org.example;

import junit.framework.TestCase;

public class AppTest extends TestCase {

    public void testUnboxStringOnlyRepeatString() {

        //Проверка развертывания строки состоящей только из частей кторые надо повторить

        assertEquals("xyzxyzxyzfdsfdsfdsfds", App.unboxString("3[xyz]4[fds]"));
        assertEquals("xyzxyzxyzxyzxyzxyzxyzxyzxyzxyzfdsfdsfdsfds", App.unboxString("10[xyz]4[fds]"));
        assertEquals("xyzfdsfdsfdsfdsfdsfdsfdsfdsfdsfdsfds", App.unboxString("1[xyz]11[fds]"));
        assertEquals("hyy", App.unboxString("hyy3[]"));
        assertEquals("", App.unboxString("100[]"));
    }

    public void testUnboxStringOnlySimpleString() {

        //Проверка развертывания строки не имеющей повторяющихся частей

        assertEquals("abcdef", App.unboxString("abcdef"));

    }

    public void testUnboxStringMixString() {

        //Проверка развертывания строки состоящей из частей кторые надо повторить и не повторяющиеся части

        assertEquals("xyzxyzxyzfdsfdsfdsfdsrt", App.unboxString("3[xyz]4[fds]rt"));

    }

    public void testUnboxStringNestedString() {

        //Проверка развертывания строки состоящей из частей кторые надо повторить и они имеют вложенность для повторения

        assertEquals("aaxyzaaxyzd", App.unboxString("2[2[a]xyz]d"));
    }

    public void testUnboxStringMixNestedString() {

        //Проверка развертывания строки состоящей из частей кторые надо повторить и они имеют вложенность для повторения
        // и так же не повторяющиеся части

        assertEquals("dfaaaxyzaaxyzrt", App.unboxString("dfa2[2[a]xyz]rt"));
        assertEquals("dfaaaxyyzaaxyyzrt", App.unboxString("dfa2[2[a]x2[y]z]rt"));


    }

    public void testUnboxStringNotValid() {

        // Проверка сторки на валидность

        assertEquals("Строка не валидна", App.unboxString(""));
        assertEquals("Строка не валидна", App.unboxString(" "));
        assertEquals("Строка не валидна", App.unboxString(":hyy"));
        assertEquals("Строка не валидна", App.unboxString(":hyy3[]"));
        assertEquals("Строка не валидна", App.unboxString("3[xyz]4]fds]"));
        assertEquals("Строка не валидна", App.unboxString("3[]4[]fds]"));
        assertEquals("Строка не валидна", App.unboxString("3][4][fds]"));
        assertEquals("Строка не валидна", App.unboxString("dfa2[2[a]xyzrt"));
        assertEquals("Строка не валидна", App.unboxString("dfa2[2]a]xyzrt"));
        assertEquals("Строка не валидна", App.unboxString("dfa2]2[a]xyzrt"));
        assertEquals("Строка не валидна", App.unboxString("!dfa2[2[a]xyz]rt"));
        assertEquals("Строка не валидна", App.unboxString("dfa2s[2[a]xyz]rt"));
    }
}