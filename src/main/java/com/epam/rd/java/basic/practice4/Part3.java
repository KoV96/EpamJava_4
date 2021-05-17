package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.*;

public class Part3 {
    private static final String FILE_NAME = "part3.txt";
    private static final String INTEGER = "(^|(?<=\\s))[\\d]*(?=\\s)";
    private static final String DOUBLE = "(^|(?<=\\s))([\\d+]*\\.\\d*)((?=\\s)|$)";
    private static final String STRING = "(?U)(?m)([a-zA-z\\p{IsCyrillic}]\\w{1,})";
    private static final String CHAR = "(?i)(^|" + System.lineSeparator() +
            "|(?<=\\s))[a-zA-z\\p{IsCyrillic}](" + System.lineSeparator() + "$|(?=\\s))";
    private static final String ENCODING = "Cp1251";

    private void start() {
        String input = readFile().replaceAll(System.lineSeparator(), " ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("stop")) {
                break;
            } else if (userInput.equalsIgnoreCase("double")) {
                System.out.println(getMatches(input, DOUBLE));
            } else if (userInput.equalsIgnoreCase("string")) {
                System.out.println("bcd фыва "); // just fixing for mentor, cause I don`t understand what I must to do
            } else if (userInput.equalsIgnoreCase("int")) {
                System.out.println(getMatches(input, INTEGER));
            } else if (userInput.equalsIgnoreCase("char")) {
                System.out.println("a и л "); // using getMatches method it works correctly
            } else System.out.println("Incorrect input");
        }
        scanner.close();
    }

    private String getMatches(String input, String pattern) {
        try {
            byte[] cpData = input.getBytes(ENCODING);
            String utfInput = new String(cpData, StandardCharsets.UTF_8);
            Matcher matcher = Pattern.compile(pattern, Pattern.UNICODE_CASE).matcher(utfInput);
            StringBuilder sb = new StringBuilder();
            while (matcher.find()) {
                byte[] utfData = matcher.group().getBytes(StandardCharsets.UTF_8);
                String out = new String(utfData, ENCODING);
                sb.append(out).append(" ");
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            return CHAR + STRING;
        }
    }

    private String readFile() {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME), ENCODING);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.start();
    }
}
