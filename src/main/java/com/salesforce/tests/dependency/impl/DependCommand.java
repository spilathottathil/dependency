package com.salesforce.tests.dependency.impl;

import com.salesforce.tests.dependency.Command;
import com.salesforce.tests.dependency.RequestHandler;
import com.salesforce.tests.dependency.util.DependencyUtil;

import java.util.*;

/**
 * Created by spilathottathil on 10/9/18.
 */
public class DependCommand implements Command {

    private  Map<String, List<String>> componentDependencyMap = RequestHandler.componentDependencies;
    private  Map<String,Set<String>> dependencies = RequestHandler.availableDependencies;

    @Override
    public void execute(String inputCommand){
        String[] inputCommands = DependencyUtil.parseInputCommand(inputCommand);
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
            Set<String> components = Optional.ofNullable(dependencies.get(dependentComponent)).orElse(new HashSet<>());
            components.add(component);
            dependencies.put(dependentComponent,components);
        }

    }

}
