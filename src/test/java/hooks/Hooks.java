package hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utility.InitDriver;

public class Hooks {

    @Before
    public void openBrowser() {
        System.out.println("before");
        InitDriver.setDriver();
    }

    @After
    public void tearDown() {
        InitDriver.getDriver().quit();
    }

}
