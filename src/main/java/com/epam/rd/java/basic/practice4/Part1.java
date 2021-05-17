package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class Part1 {
    private static final String ENCODING = "Cp1251";
    private static final String FILE_NAME = "part1.txt";
    private static final String EOL = System.lineSeparator();

    public Part1() {
        System.out.println(convert());
    }

    private String convert() {
        Pattern pattern = Pattern.compile("\\b[a-zA-Z\\p{IsCyrillic}]+\\b|[\n ]");
        Matcher matcher = pattern.matcher(readFile());
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            if (Character.isLetter(matcher.group().charAt(0)) && matcher.group().length() > 3) {
                String convertWord = matcher.group().substring(2);
                sb.append(convertWord);
            } else if (matcher.group().equals("\n")) {
                sb.append(matcher.group());
            } else {
                sb.append(matcher.group());
            }
        }
        return sb.toString();
    }

    private String readFile() {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(FILE_NAME), ENCODING)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                if (scanner.hasNextLine()) sb.append(EOL);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new Part1();
    }
}
