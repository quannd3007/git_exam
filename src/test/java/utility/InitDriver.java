package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InitDriver {
    public static WebDriver driver;

    public static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--no-sandbox");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver(){
        return driver;
    }

}
