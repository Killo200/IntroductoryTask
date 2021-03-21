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

                        Matcher matcherContainInnerDigit2 = patternContainDigit.matcher(innerString);
                        matcherContainInnerDigit2.find();

                        if (matcherContainInnerDigit2.start() == 0) {

                            result.replace(indexInnerValue, indexRightBracket + 1, unboxString(innerString.toString())).toString();
                        } else {

                            innerString.delete(0, matcherContainInnerDigit2.start());
                            result.replace(indexInnerValue, indexRightBracket + 1, unboxString(innerString.toString())).toString();
                        }
                    }
                } else {

                    throw new Exception();
                }

            }
        } catch (Exception ex) {

            return "Строка не валидна";
        }
    }
}
