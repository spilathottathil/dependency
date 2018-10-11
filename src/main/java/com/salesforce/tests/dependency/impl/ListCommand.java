package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.RequestHandler;

import java.util.*;

/**
 * Created by spilathottathil on 10/10/18.
 */
public class ListCommand implements Command {
    private  List<String> components = RequestHandler.installedComponents;

    @Override
    public void execute(String input){
        components.forEach(System.out :: println);
    }
}
