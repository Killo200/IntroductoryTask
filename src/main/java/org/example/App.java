package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Application for unboxing string
 */
public class App {
    public static void main(String[] args) {

    }

    public static String unboxString(String str) {

        Pattern patternValid = Pattern.compile("\\d|[a-z]|\\[|]");
        Matcher matcherValid = patternValid.matcher(str);
        String[] arrayOfSymbols = str.split("");
        int leftBracket = 0;
        int rightBracket = 0;
        for (String s :  arrayOfSymbols) {

            if (!matcherValid.find()) {
                return "Строка не валидна";
            }
            if (matcherValid.group().equals("[")) {
                leftBracket++;
            }
            if (matcherValid.group().equals("]")) {
                rightBracket++;
            }
        }
        if (leftBracket != rightBracket) {
            return "Строка не валидна";
        }

        StringBuilder result = new StringBuilder();
        result.append(str);

        while (true) {

            Pattern patternContainDigit = Pattern.compile("\\d+");
            Matcher matcherContainDigit = patternContainDigit.matcher(result);

            if (!matcherContainDigit.find()) {

                return result.toString();
            }

            Pattern patternRightBracket = Pattern.compile("]");
            Matcher matcherRightBracket = patternRightBracket.matcher(result);

            int valueDigit = 0;
            int indexDigit = 0;
            int indexRightBracket = 0;
            StringBuilder innerString = new StringBuilder();

            try {

                indexDigit = matcherContainDigit.start();
                valueDigit = Integer.parseInt(matcherContainDigit.group());
                if (matcherRightBracket.find()) {
                    indexRightBracket = matcherRightBracket.start();

                    innerString.append(result, indexDigit + matcherContainDigit.group().length() + 1, indexRightBracket);

                    Matcher matcherContainInnerDigit = patternContainDigit.matcher(innerString);

                    if (!matcherContainInnerDigit.find()) {

                        for (int i = 1; i < valueDigit; i++) {
                            innerString.append(result, indexDigit + matcherContainDigit.group().length() + 1, indexRightBracket);
                        }

                        result.replace(indexDigit, indexRightBracket + 1, innerString.toString());
                    } else {

                        innerString.append("]");
                        matcherContainDigit.find();
                        int indexInnerValue = matcherContainDigit.start(0);

                        result.replace(indexInnerValue, indexRightBracket + 1, unboxString(innerString.toString())).toString();
                    }
                } else {

                    throw new Exception();
                }
            } catch (Exception ex) {

                return "Строка не валидна";
            }
        }
    }
}
