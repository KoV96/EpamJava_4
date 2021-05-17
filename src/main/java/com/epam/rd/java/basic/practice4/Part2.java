package com.epam.rd.java.basic.practice4;

import java.io.*;
import java.security.SecureRandom;
import java.util.Random;

public class Part2 {
    private final String encoding;
    private final String fileName;
    private final String sortedFileName;
    private static final int TOTAL_NUM = 10;
    private String randomNum;

    public String getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }

    public Part2(String fileName, String sortedFileName, String encoding) {
        this.encoding = encoding;
        this.fileName = fileName;
        this.sortedFileName = sortedFileName;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getFileName() {
        return fileName;
    }

    public String getSortedFileName() {
        return sortedFileName;
    }

    private void createFirstFile() {
        try (final PrintWriter pr = new PrintWriter(getFileName(), getEncoding())) {
            fillInFirstFile();
            pr.write(randomNum);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + getFileName() + " or " +
                    getSortedFileName() + "can not be written");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
    }

    private void fillInFirstFile() {
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < TOTAL_NUM; i++) {
            if (i == TOTAL_NUM - 1) {
                sb.append(random.nextInt(49) + 1);
                break;
            }
            sb.append(random.nextInt(49) + 1).append(" ");
        }
        setRandomNum(sb.toString());
    }

    private void createSortedFile() {
        try (final PrintWriter pr = new PrintWriter(getSortedFileName(), getEncoding())) {
            pr.write(sortNumbers());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + getSortedFileName());
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage() + getEncoding());
        }
    }

    private String sortNumbers() {
        String[] numbers = getRandomNum().split(" ");
        boolean isSorted = false;
        String buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (Integer.parseInt(numbers[i]) > Integer.parseInt(numbers[i + 1])) {
                    isSorted = false;
                    buf = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = buf;
                }
            }
        }
        return toString(numbers);
    }

    private String toString(String[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            if (i == TOTAL_NUM - 1) {
                sb.append(numbers[i]);
                break;
            }
            sb.append(numbers[i]).append(" ");
        }
        return sb.toString();
    }

    public String getData(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                String data = br.readLine();
                if (data == null) break;
                sb.append(data);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + fileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2("part2.txt", "part2_sorted.txt", "Cp1251");
        part2.createFirstFile();
        part2.createSortedFile();
        System.out.println("input ==> " + part2.getData("part2.txt"));
        System.out.println("output ==> " + part2.getData("part2_sorted.txt"));
    }
}
