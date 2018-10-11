package com.salesforce.tests.dependency;

import com.salesforce.tests.dependency.impl.DependCommand;
import com.salesforce.tests.dependency.impl.InstallCommand;
import com.salesforce.tests.dependency.impl.ListCommand;
import com.salesforce.tests.dependency.impl.RemoveCommand;
import com.salesforce.tests.dependency.util.DependentUtil;

import java.util.*;


public class Component {

    private static Map<String,Command> commandMap = new HashMap<>();
    public static Map<String, List<String>> componentDependencies;
    public static List<String> installedComponents ;
    public static Map<String,Set<String>> availableDependencies;

    Component(){
        componentDependencies = new HashMap<>();
        installedComponents = new ArrayList<>();
        availableDependencies = new HashMap<>();
        //set commands
        commandMap.put(DependentUtil.DEPEND , new DependCommand());
        commandMap.put(DependentUtil.INSTALL,new InstallCommand());
        commandMap.put(DependentUtil.LIST,new ListCommand());
        commandMap.put(DependentUtil.REMOVE,new RemoveCommand());
    }

    public void handleCommands(String inputLine){

        String[] command = DependentUtil.parseInputCommand(inputLine);
        commandMap.get(command[0]).execute(inputLine);
    }


    public void destroy(){
        componentDependencies.clear();
        installedComponents.clear();
        availableDependencies.clear();
    }


}
