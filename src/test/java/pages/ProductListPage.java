package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductListPage extends BasePage {

    String XPATH_BUTTON_MODE_LIST = "(//a[@id='mode-list'])[1]";
    String ID_SELECT_SORT_BY = "sorter";
    String XPATH_NEXT_BUTTON = "(//main[@id='maincontent']//a[@class='action  next'])[2]";
    String XPATH_LIST_PRICE_OF_PRODUCT = "//li[@class='item product product-item']//span[@class='price']";
    String XPATH_PRICE_OF_PRODUCT = "(//li[@class='item product product-item']//span[@class='price'])[%s]";

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://demo.smartosc.com/women/tops-women/jackets-women.html");
    }

    public void changeToList() {
        waitUtility.sleep(5);
        actionUtility.click(By.xpath(XPATH_BUTTON_MODE_LIST));
    }

    public void sortBy(String option) {
        waitUtility.waitUntilVisibility(By.id(ID_SELECT_SORT_BY));
        new Select(driver.findElement(By.id(ID_SELECT_SORT_BY))).selectByValue(option.toLowerCase());

    }

    public float getPrice(By xpath) {
        return Float.parseFloat(driver.findElement(xpath).getText().replace("US$", "").replace(",", "."));
    }

    public float getPrice1(By xpath) {
        return Float.parseFloat(driver.findElement(xpath).getText().replace("US$", "").replace(",", "."));
    }
    
    public boolean verifySortByPriceCorrect() throws Exception {
        float maxPricePage = 0;

        List<WebElement> productList = driver.findElements(By.xpath(XPATH_LIST_PRICE_OF_PRODUCT));

        // check product list has data
        if (productList.size() < 1) {
            throw new Exception("No product!");
        }

        return verifyPrice(maxPricePage);
    }

    public boolean verifyPrice(float maxPricePage) throws Exception {
        // wait product list is displayed
        waitUtility.waitUntilVisibility(By.xpath(XPATH_LIST_PRICE_OF_PRODUCT));

        List<WebElement> productList = driver.findElements(By.xpath(XPATH_LIST_PRICE_OF_PRODUCT));

        // compare price of first product on current page to max price (price of last product) of previous page
        if (getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, 1))) < maxPricePage) {
            throw new Exception("Max price of previous page: " + maxPricePage + " but price of first product on current page: " + getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, 1))));
        }

        for (int i = 1; i < productList.size(); i++) {
            float pre = getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, i)));
            float next = getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, i + 1)));
            if (pre > next) {
                throw new Exception("Previous price: " + pre +" but Next price: " + next);
            }
        }

        // assign max price of current page in order to compare with the price of first product on next page
        maxPricePage = getPrice(By.xpath(String.format(XPATH_PRICE_OF_PRODUCT, productList.size() - 1)));

        // click next button if it is still displayed
        if(actionUtility.checkElementDisplay(By.xpath(XPATH_NEXT_BUTTON), 5)){
            actionUtility.click(By.xpath(XPATH_NEXT_BUTTON));

            // recall verify price function for next page
            verifyPrice(maxPricePage);
        }

        return true;
    }

}
