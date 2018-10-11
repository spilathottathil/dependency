package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.Component;
import com.salesforce.tests.dependency.util.DependentUtil;

import java.util.*;

/**
 * Created by spilathottathil on 10/10/18.
 */
public class InstallCommand implements Command {
    private  Map<String, List<String>> componentDependencyMap = Component.componentDependencies;
    private  List<String> components = Component.installedComponents;

    @Override
    public void execute(String inputComponent){

        installComponent(DependentUtil.parseInputCommand(inputComponent)[1]);
    }

    void installComponent(String component) {
        //check if its already installed
        if(components.contains(component)){
            System.out.println(component+ " is already installed");
        }else {
            List<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new ArrayList<>());
            for ( String dependency : dependenciesForComponent){
                if(!components.contains(dependency)){
                    System.out.println("Installing "+dependency);
                    components.add(dependency);
                }
            }
            System.out.println("Installing "+component);
            components.add(component);
        }

    }
}
