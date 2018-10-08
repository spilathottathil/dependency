package com.salesforce.tests.dependency;

import java.util.*;

/**
 * The entry point for the Test program
 */
public class Main {

    public void manageCommands(){


    }



    public static void main(String[] args) {

        Component component = new Component();
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

            String[] command = parseInputCommand(line);

            switch (command[0]){

                case "DEPEND":
                    component.addDependencies(command[1], Arrays.copyOfRange(command,2,command.length));
                    break;
                case "INSTALL":
                    component.installComponent(command[1]);
                    break;
                case "REMOVE":
                    component.removeComponent(command[1]);
                    break;
                case "LIST":
                    component.listComponent();
                    break;
            }


        }

    }

    public static String[] parseInputCommand(String input){
        return  Arrays.stream(input.split(" "))
                .map(String::trim)
                .toArray(String[]::new);
    }

}