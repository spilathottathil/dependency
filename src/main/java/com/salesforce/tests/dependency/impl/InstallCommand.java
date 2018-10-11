package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.util.DependentUtil;

import java.util.*;

/**
 * Created by spilathottathil on 10/10/18.
 */
public class InstallCommand implements Command {


    public void execute(String inputComponent){
        installComponent(DependentUtil.parseInputCommand(inputComponent)[1]);
    }

    void installComponent(String component) {
        //check if its already installed
        if(installedComponents.contains(component)){
            System.out.println(component+ " is already installed");
        }else {
            List<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new ArrayList<>());
            for ( String dependency : dependenciesForComponent){
                if(!installedComponents.contains(dependency)){
                    System.out.println("Installing "+dependency);
                    installedComponents.add(dependency);
                }
            }
            System.out.println("Installing "+component);
            installedComponents.add(component);
        }

    }
}
