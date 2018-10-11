package com.salesforce.tests.dependency;

import java.util.*;

/**
 * Created by spilathottathil on 10/9/18.
 */
public interface Command {
     Map<String, List<String>> componentDependencyMap = new HashMap<>();
     List<String> installedComponents = new ArrayList<>();
     Map<String,Set<String>> installedDependencies = new HashMap<>();
    void execute(String input);
}
