package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Application for unboxing string
 */
public class App {
    public static void main(String[] args) {

    }

    public static String unboxString(String str) {

        try {

            String[] arrayOfSymbols = str.split("");

            int leftBracket = 0;
            int rightBracket = 0;

            for (int i = 0; i < arrayOfSymbols.length; i++) {

                if (!Pattern.matches("\\d|[a-z]|\\[|]", arrayOfSymbols[i])) {
                    throw new Exception();
                }
                if (arrayOfSymbols[i].equals("[")) {
                    if (!arrayOfSymbols[i - 1].matches("\\d")) {
                        throw new Exception();
                    }
                    leftBracket++;
                }
                if (arrayOfSymbols[i].equals("]")) {
                    rightBracket++;
                }

            }

            if (leftBracket != rightBracket) {
                throw new Exception();
            }

            StringBuilder result = new StringBuilder();
            result.append(str);

            while (true) {

                Pattern patternContainRepeatString = Pattern.compile("\\d+\\[[a-z]+]|\\d+\\[]");
                Pattern patternContaindDigits = Pattern.compile("\\d+");
                Matcher matcherContainRepeatString = patternContainRepeatString.matcher(result);

                if (!matcherContainRepeatString.find()) {

                    return result.toString();
                }

                int indexDigit = matcherContainRepeatString.start();

                String innerString = matcherContainRepeatString.group();


                Matcher matcherContainDigits = patternContaindDigits.matcher(innerString);
                matcherContainDigits.find();
                int valueDigit = Integer.parseInt(matcherContainDigits.group());

                StringBuilder repeat = new StringBuilder();

                for (int i = 0; i < valueDigit; i++) {
                    repeat.append(innerString, matcherContainDigits.group().length() + 1, matcherContainRepeatString.group().length() - 1);
                }

                result.replace(indexDigit, indexDigit + matcherContainRepeatString.group().length(), repeat.toString());
            }
        } catch (Exception ex) {

            return "Строка не валидна";
        }
    }
}
