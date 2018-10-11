package com.salesforce.tests.dependency;

import java.util.*;

/**
 * The entry point for the Test program
 */
public class Main {


    public static void main(String[] args) {

        RequestHandler requestHandler = new RequestHandler();

        //read input from stdin
        Scanner scan = new Scanner(System.in);


        while (true) {
            String line = scan.nextLine();

            //no action for empty input
            if (line == null || line.length() == 0) {
                continue;
            }

            //the END command to stop the program
            if ("END".equals(line)) {
                System.out.println("END");
                break;
            }
           //Please provide your implementation here
            System.out.println(line);
            requestHandler.handleCommands(line);

        }
        requestHandler.destroy();

    }


}