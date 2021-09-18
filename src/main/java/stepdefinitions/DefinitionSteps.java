package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.GenderCategoriesPage;
import pages.HomePage;
import pages.ProductCategoryPage;

import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefinitionSteps {

    private static final long WAIT_FOR = 60;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    GenderCategoriesPage genderCategoriesPage;
    ProductCategoryPage productCategoryPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(WAIT_FOR);
    }

    @And("User checks header buttons")
    public void checkHeaderButtons() {
        assertTrue(homePage.checkHeaderButtons());
    }

    @And("User clicks header {string} button")
    public void clickHeaderButtonButton(String button) {
        BasePage.clickHeaderButtons(button);
    }

    @And("User checks logo")
    public void checkLogo() {
        assertTrue(BasePage.checkLogo());
    }

    @And("User clicks logo")
    public void clickLogo() {
        BasePage.clickLogo();
    }

    @And("User checks central buttons")
    public void checkHomePageCentralButtons() {
        assertTrue(homePage.checkCentralButtons());
    }

    @And("User clicks type of shop {string} button")
    public void clickTypeOfShopCentralButtons(String button) {
        homePage.clickCentralShopButton(button);
        genderCategoriesPage = pageFactoryManager.getGenderCategoriesPage();
        genderCategoriesPage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks image on homepage")
    public void checkImageOnHomepage() {
        assertTrue(homePage.checkPicture());
    }

    @And("Page with title {string} is displayed")
    public void pageWithTitleIsDisplayed(String pageTitle) {
        assertEquals(List.of(homePage.getTitle().split("[' ]")).get(0),pageTitle);
    }

    @And("User checks goods features page")
    public void checkGoodsFeaturesPage() {
        assertTrue(genderCategoriesPage.checkGoodsFeatures());
    }

    @And("User clicks feature {string} button")
    public void clickFeatureButton(String feature) {
        genderCategoriesPage.clickProductFeature(feature);
        productCategoryPage = pageFactoryManager.getProductCategoryPage();
        productCategoryPage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks filters")
    public void checkFilters() {
        assertTrue(productCategoryPage.checkFilters());
    }

    @And("User click {string} filter")
    public void clickFilter(String filter) {
        productCategoryPage.clickFilter(filter.substring(0,4));
    }

    @And("count of styles is {string}")
    public void checkCountOfStyles(String countOfStyles) {
        assertEquals(productCategoryPage.getCountOfStyles().length(),countOfStyles.length());
    }

    @And("User hover mouse over {string} category")
    public void hoverMouseOverCategory(String category) {
        BasePage.selectShopMainCategory(category);
    }

    @And("User clicks element {string} in header")
    public void clickElementInHeader(String element) {
        BasePage.clickHeaderElement(element);
        productCategoryPage = pageFactoryManager.getProductCategoryPage();
        productCategoryPage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks {string} name of page")
    public void checkPageName(String name) {
        assertEquals(productCategoryPage.getPageName()
                        .replace("Men's ","")
                        .replace("Women's ",""), name);
    }

    @And("User checks filter sections")
    public void checkFilterSections() {
        assertTrue(productCategoryPage.checkFilterSections());
    }

    @And("User selects filter section {string}")
    public void selectFilterSection(String section) {
        productCategoryPage.clickFilterSection(section);
    }

    @And("User checks that name of products contains {string}")
    public void checkNameProductContainsFilter(String productName) {
        assertTrue(productCategoryPage.checkProductNameContainsFilter(productName));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}