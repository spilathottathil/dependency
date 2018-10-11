package com.salesforce.tests.dependency;

import com.salesforce.tests.dependency.impl.DependCommand;
import com.salesforce.tests.dependency.impl.InstallCommand;
import com.salesforce.tests.dependency.impl.ListCommand;
import com.salesforce.tests.dependency.impl.RemoveCommand;
import com.salesforce.tests.dependency.util.DependencyUtil;

import java.util.*;


public class RequestHandler {

    private  Map<String,Command> commandMap = new HashMap<>();
    public static Map<String, List<String>> componentDependencies = new HashMap<>();
    public static List<String> installedComponents = new ArrayList<>();
    public static Map<String,Set<String>> availableDependencies = new HashMap<>();

    RequestHandler(){
        //set commands
        commandMap.put(DependencyUtil.DEPEND , new DependCommand());
        commandMap.put(DependencyUtil.INSTALL,new InstallCommand());
        commandMap.put(DependencyUtil.LIST,new ListCommand());
        commandMap.put(DependencyUtil.REMOVE,new RemoveCommand());
    }

     void handleCommands(String inputLine){
        String[] command = DependencyUtil.parseInputCommand(inputLine);
        commandMap.get(command[0]).execute(inputLine);
    }


     void destroy(){
        componentDependencies.clear();
        installedComponents.clear();
        availableDependencies.clear();
    }


}
