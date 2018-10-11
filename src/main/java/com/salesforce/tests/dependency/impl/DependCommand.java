package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.util.DependentUtil;

import java.util.*;

/**
 * Created by spilathottathil on 10/9/18.
 */
public class DependCommand implements Command {


    public void execute(String inputCommand){
        String[] inputCommands = DependentUtil.parseInputCommand(inputCommand);
        addDependencies(inputCommands[1],Arrays.copyOfRange(inputCommands,2,inputCommands.length));
    }

    void  addDependencies(String component, String[] dependentComponents) {

        List<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new ArrayList<>());

        for (String dependentComponent : dependentComponents) {
            if(componentDependencyMap.get(dependentComponent) != null &&   componentDependencyMap.get(dependentComponent).contains(component)){
                System.out.println(dependentComponent + " depends on "+component+", ignoring command");
                continue;
            }
            dependenciesForComponent.add(dependentComponent);
            componentDependencyMap.put(component,dependenciesForComponent);
            Set<String> components = Optional.ofNullable(installedDependencies.get(dependentComponent)).orElse(new HashSet<>());
            components.add(component);
            installedDependencies.put(dependentComponent,components);
        }

    }

}
