package com.salesforce.tests.dependency.util;

import java.util.Arrays;

/**
 * Created by spilathottathil on 10/10/18.
 */
public   class DependentUtil {
    public static String DEPEND = "DEPEND";
    public static String INSTALL = "INSTALL";
    public static String REMOVE = "REMOVE";
    public static String LIST = "LIST";

    public static String[] parseInputCommand(String input){
        return  Arrays.stream(input.split(" "))
                .map(String::trim)
                .toArray(String[]::new);
    }


}
