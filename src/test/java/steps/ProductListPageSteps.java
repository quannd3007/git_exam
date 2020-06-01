package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.ProductListPage;
import utility.InitDriver;

public class ProductListPageSteps {

    public ProductListPage productListPage;

    public ProductListPageSteps(){
        productListPage = new ProductListPage(InitDriver.getDriver());
    }

    @Given("go to Product List page")
    public void goToLoginPage() {
        productListPage.open();
    }

    @And("change layout to list")
    public void changeLayoutToList() {
        productListPage.changeToList();
    }

    @And("^set sorting product list by (.+?)$")
    public void setSortingProductListByPrice(String option) {
        productListPage.sortBy(option);
    }

    @Then("the product list is sort correctly")
    public void theProductListIsSortCorrectly() throws Exception {
        Assert.assertTrue("The sort price function work incorrectly", productListPage.verifySortByPriceCorrect());
    }

}
