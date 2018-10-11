package com.salesforce.tests.dependency;

import com.salesforce.tests.dependency.impl.DependCommand;
import com.salesforce.tests.dependency.impl.InstallCommand;
import com.salesforce.tests.dependency.impl.ListCommand;
import com.salesforce.tests.dependency.impl.RemoveCommand;
import com.salesforce.tests.dependency.util.DependentUtil;

import java.util.*;


class Component {

    private static Map<String,Command> commandMap = new HashMap<>();

    public void handleCommands(String inputLine){

        String[] command = DependentUtil.parseInputCommand(inputLine);
        commandMap.get(command[0]).execute(inputLine);
    }

    public  void setCommands(){
        commandMap.put(DependentUtil.DEPEND , new DependCommand());
        commandMap.put(DependentUtil.INSTALL,new InstallCommand());
        commandMap.put(DependentUtil.LIST,new ListCommand());
        commandMap.put(DependentUtil.REMOVE,new RemoveCommand());
    }

}
