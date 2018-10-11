package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;

import java.util.*;

/**
 * Created by spilathottathil on 10/10/18.
 */
public class ListCommand implements Command {

    public void execute(String input){

        installedComponents.forEach(System.out :: println);
    }
}
