package com.salesforce.tests.dependency;

import org.junit.Before;
import org.junit.Test;



/**
 * Place holder for your unit tests
 */

public class YourUnitTest extends BaseTest {

    RequestHandler requestHandler;

@Before
public void setUp(){
   requestHandler = new RequestHandler();

}

@Test
//Removing component should remove its dependencies as well.
public void removeComponentWithDependencies(){
    String[] input = {"DEPEND TELNET TCPIP NETCARD\n" +
            "INSTALL TELNET\n",
            "REMOVE TELNET\n",
            "LIST\n",
            "END\n"
    };

    String expectedOutput = "DEPEND TELNET TCPIP NETCARD\n" +
            "INSTALL TELNET\n" +
            "Installing TCPIP\n" +
            "Installing NETCARD\n" +
            "Installing TELNET\n" +
            "REMOVE TELNET\n" +
            "Removing TELNET\n" +
            "Removing TCPIP\n"+
            "Removing NETCARD\n"+
            "LIST\n"+
            "END\n";
    runTest(expectedOutput, input);
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
