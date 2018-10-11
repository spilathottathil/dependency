package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.util.DependentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by spilathottathil on 10/10/18.
 */
public class RemoveCommand  implements Command {

    public void execute(String removeComponent){

        removeComponent(DependentUtil.parseInputCommand(removeComponent)[1]);

    }
    void removeComponent(String component) {
        if(!installedComponents.contains(component)){
            System.out.println(component+" is not installed");
            return;
        }
        if(installedDependencies.get(component) != null ){
            System.out.println(component+" is still needed");
        }else{
            installedComponents.remove(component);
            System.out.println("Removing "+component);
            List<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new ArrayList<>());
            for (String dependency : dependenciesForComponent){
                removeDependency(dependency,component);
            }
        }
    }

    void removeDependency(String dependency,String component) {
        if(installedDependencies.get(dependency) != null ) installedDependencies.get(dependency).remove(component);
        if(installedDependencies.get(dependency).isEmpty()) {
            installedComponents.remove(dependency);
            System.out.println("Removing "+dependency);
        }
    }
}
