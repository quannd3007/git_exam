package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

    WebDriver driver;
    int WAITING_TIME = 60;

    public WaitUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilExist(By locator, long waitingTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitingTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitUntilVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilVisibility(By locator, int waitingTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitingTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForPageLoad() {
        try {
            waitForJQueryLoad();
            waitUntilJSReady();
        } catch (Throwable error) {
        }
    }

    //Wait Until JS Ready
    public void waitUntilJSReady() {
        int counting = 1;
        String pageLoadStatus;
        do {
            sleep(1000);
            pageLoadStatus = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
            counting++;
        } while (!pageLoadStatus.contains("complete") && (counting < WAITING_TIME));
    }

    //Wait for JQuery Load
    public void waitForJQueryLoad() {

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        int counting = 1;
        final String jQueryActiveScript = "return window.jQuery != undefined && jQuery.active==0";

        //Get JQuery is Ready
        boolean jqueryReady;
        //Wait JQuery until it is Ready!
        do {
            sleep(1000);
            jqueryReady = (Boolean) jsExec.executeScript(jQueryActiveScript);
            counting++;
        } while (!jqueryReady && (counting < WAITING_TIME));

    }

}
