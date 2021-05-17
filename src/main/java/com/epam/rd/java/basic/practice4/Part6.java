package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

public class Part6 {
    private static final String LATN = "latn";
    private static final String CYRL = "cyrl";
    private static final String LATIN = "\\w+";
    private static final String CYRILLIC = "\\p{IsCyrillic}+";
    private static final String ENCODING = "Cp1251";

    private void start() {
        Scanner scanner = new Scanner(System.in, ENCODING);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("stop")) {
                break;
            }
            printsOutSpecificWords(userInput);
        }
    }

    private void printsOutSpecificWords(String userInput) {
        String fileData = readFile("part6.txt");
        Matcher matcher;
        StringBuilder sb = new StringBuilder();
        if (userInput.equalsIgnoreCase(LATN)) {
            matcher = Pattern.compile(LATIN).matcher(fileData);
            while (matcher.find()) {
                sb.append(matcher.group()).append(" ");
            }
            System.out.println("Latn: " + sb.toString());
        } else if (userInput.equalsIgnoreCase(CYRL)) {
            matcher = Pattern.compile(CYRILLIC).matcher(fileData);
            while (matcher.find()) {
                sb.append(matcher.group()).append(" ");
            }
            System.out.println("Cyrl: " + sb.toString());
        } else {
            System.out.println(userInput + ": " + "Incorrect input");
        }
    }

    private String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(new FileInputStream(fileName), ENCODING)) {
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                if (data == null) break;
                sb.append(data).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + fileName);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Part6 part6 = new Part6();
        part6.start();
    }
}
