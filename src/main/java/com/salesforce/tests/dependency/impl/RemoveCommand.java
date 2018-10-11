package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.RequestHandler;
import com.salesforce.tests.dependency.util.DependencyUtil;

import java.util.*;

/**
 * Created by spilathottathil on 10/10/18.
 */
public class RemoveCommand  implements Command {
    private  List components = RequestHandler.installedComponents;
    private  Map<String, List<String>> componentDependencyMap = RequestHandler.componentDependencies;
    private  Map<String,Set<String>> dependencies = RequestHandler.availableDependencies;

    @Override
    public void execute(String removeComponent){

        removeComponent(DependencyUtil.parseInputCommand(removeComponent)[1]);

    }
    void removeComponent(String component) {
        if(!components.contains(component)){
            System.out.println(component+" is not installed");
            return;
        }
        if(dependencies.get(component) != null ){
            System.out.println(component+" is still needed");
        }else{
            components.remove(component);
            System.out.println("Removing "+component);
            List<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new ArrayList<>());
            for (String dependency : dependenciesForComponent){
                removeDependency(dependency,component);
            }
        }
    }

    void removeDependency(String dependency,String component) {
        if(dependencies.get(dependency) != null ) dependencies.get(dependency).remove(component);
        if(dependencies.get(dependency).isEmpty()) {
            components.remove(dependency);
            System.out.println("Removing "+dependency);
        }
    }
}
