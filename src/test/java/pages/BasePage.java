package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.ActionUtility;
import utility.WaitUtility;

public class BasePage {
	
	String XPATH_LOADING = "//div[@class='loading-mask'][@style='display: none;']";
	
	public WebDriver driver;
	public ActionUtility actionUtility;
	public WaitUtility waitUtility;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		actionUtility = new ActionUtility(driver);
		waitUtility = new WaitUtility(driver);
	}
	
	public void waitLoadingInvisibility() {
		waitUtility.waitUntilExist(By.xpath(XPATH_LOADING), 60);
	}
}
