package com.salesforce.tests.dependency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Place holder for your unit tests
 */

public class YourUnitTest extends BaseTest {

    Component component;

@Before
public void setUp(){
   component= new Component();

}

@Test
public  void addAlreadyInstalledComponent(){

    String componentInput ="TCPIP";
    component.installComponent(componentInput);
    //Adding TCPIP again
    component.installComponent(componentInput);
    String expectedOuput = "Installing TCPIP\n" + "TCPIP is already installed\n";
    Assert.assertEquals(expectedOuput,systemOutRule.getLogWithNormalizedLineSeparator());

}

@Test
public void listComponent(){
    String componentInput ="TCPIP";
    component.installComponent(componentInput);
    //Adding TCPIP again
    component.installComponent(componentInput);
    component.installComponent("BROWSER");
    component.listComponent();
    String expectedOutput = "Installing TCPIP\n" +
            "TCPIP is already installed\n" +
            "Installing BROWSER\n" +
            "TCPIP\n" +
            "BROWSER\n";
    Assert.assertEquals(expectedOutput,systemOutRule.getLogWithNormalizedLineSeparator());
}

@Test
public void removeRequiredComponent(){
    String[] input = {"DEPEND BROWSER TCPIP NETCARD HTML\n" +
            "INSTALL BROWSER\n",
            "REMOVE TCPIP\n",
            "END\n"
    };

    String expectedOutput = "DEPEND BROWSER TCPIP NETCARD HTML\n" +
            "INSTALL BROWSER\n" +
            "Installing TCPIP\n" +
            "Installing NETCARD\n" +
            "Installing HTML\n" +
            "Installing BROWSER\n" +
            "REMOVE TCPIP\n" +
            "TCPIP is still needed\n" +
            "END\n";
    runTest(expectedOutput, input);
}

@Test
public void addDependenciesWhichAlredyExists(){

}



}
