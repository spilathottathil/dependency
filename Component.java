package com.salesforce.tests.dependency;

import java.util.*;


class Component {

    private Map<String, Set<String>> componentDependencyMap = new HashMap<>();
    private List<String> installedComponents = new ArrayList<>();
    private Map<String,Set<String>> installedDependencies = new HashMap<>();


    void  addDependencies(String component, String[] dependentComponents) {

        Set<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new HashSet<>());

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

    void installComponent(String component) {
        //check if its already installed
        if(installedComponents.contains(component)){
            System.out.println(component+ " is already installed");
        }else {
            Set<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new HashSet<>());
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
            Set<String> dependenciesForComponent = Optional.ofNullable(componentDependencyMap.get(component)).orElse(new HashSet<>());
            for (String dependency : dependenciesForComponent){
                removeIndividualComponent(dependency,component);
            }
        }
    }

    void removeIndividualComponent(String dependency,String component) {
        if(installedDependencies.get(dependency) != null ) installedDependencies.get(dependency).remove(component);
        if(installedDependencies.get(dependency).isEmpty()) {
            installedComponents.remove(dependency);
            System.out.println("Removing "+dependency);
        }
    }

    void listComponent() {
        installedComponents.forEach(System.out :: println);
    }

}
