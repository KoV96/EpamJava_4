package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String>{
    private final String fileName;
    private final String encoding;
    private final String data;
    private static final String SENTENCE_REGEX = "[A-Z\\p{javaUpperCase}].*?[!?.]";

    public Part4(String fileName, String encoding) {
        this.fileName = fileName;
        this.encoding = encoding;
        this.data = readFile();
    }

    public String getFileName() {
        return fileName;
    }

    public String getEncoding() {
        return encoding;
    }

    private class SentenceIterator implements Iterator<String>{

        private final Matcher matcher = Pattern.compile(SENTENCE_REGEX).matcher(data);
        @Override
        public boolean hasNext() {
            return matcher.find();
        }

        @Override
        public String next() {
            try {
                return matcher.group();
            } catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public SentenceIterator iterator() {
        return new SentenceIterator();
    }

    private String readFile() {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(getFileName()), getEncoding())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append(line).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4("part4.txt", "Cp1251");
        for (String s : part4) {
            System.out.println(s);
        }
    }
}
