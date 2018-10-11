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
public void addComponentWhichAlredyExists(){
    String[] input = {"DEPEND BROWSER TCPIP NETCARD HTML\n" +
            "INSTALL BROWSER\n",
            "INSTALL NETCARD\n",
            "END\n"
    };
    String expectedOutput = "DEPEND BROWSER TCPIP NETCARD HTML\n" +
            "INSTALL BROWSER\n" +
            "Installing TCPIP\n" +
            "Installing NETCARD\n" +
            "Installing HTML\n" +
            "Installing BROWSER\n" +
            "INSTALL NETCARD\n" +
            "NETCARD is already installed\n" +
            "END\n";
    runTest(expectedOutput, input);
}
@Test
public void removeNotInstalledComponent(){
    String[] input = {"DEPEND BROWSER TCPIP NETCARD HTML\n" +
            "INSTALL BROWSER\n",
            "REMOVE B\n",
            "END\n"
    };
    String expectedOutput = "DEPEND BROWSER TCPIP NETCARD HTML\n" +
            "INSTALL BROWSER\n" +
            "Installing TCPIP\n" +
            "Installing NETCARD\n" +
            "Installing HTML\n" +
            "Installing BROWSER\n" +
            "REMOVE B\n" +
            "B is not installed\n" +
            "END\n";
    runTest(expectedOutput, input);
}


}
