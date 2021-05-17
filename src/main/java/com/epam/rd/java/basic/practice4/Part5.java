package com.epam.rd.java.basic.practice4;

import java.util.*;

public class Part5 {
    private static final String ENCODING = "Cp1251";
    private static final String FILE_NAME = "resources";

    private void start(){
        Scanner scanner = new Scanner(System.in, ENCODING);
        while (scanner.hasNext()){
            String[] input = scanner.nextLine().toLowerCase(Locale.ENGLISH).split(" ");
            if (input[0].equals("stop")){
                break;
            }else if (input.length < 2){
                System.err.println("Incorrect input");
            } else {
                try {
                    Locale locale = new Locale(input[1]);
                    ResourceBundle rb = ResourceBundle.getBundle(FILE_NAME, locale);
                    System.out.println(rb.getString(input[0]));
                } catch (MissingResourceException e) {
                    System.err.println(e.getMessage() + " Incorrect input");
                }
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        Part5 part5 = new Part5();
        part5.start();
    }

}
