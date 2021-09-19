package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

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
    SearchPage searchPage;
    LoginPage loginPage;
    MyOrdersPage myOrdersPage;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        BasePage.waitForPageLoadComplete(WAIT_FOR);
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
        BasePage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks image on homepage")
    public void checkImageOnHomepage() {
        assertTrue(homePage.checkPicture());
    }

    @And("Page with title {string} is displayed")
    public void pageWithTitleIsDisplayed(String pageTitle) {
        if (genderCategoriesPage != null || homePage != null) assertEquals(List.of(BasePage.getTitle().split("[' ]")).get(0),pageTitle);
        else assertEquals(BasePage.getTitle(),pageTitle);
    }

    @And("User checks goods features page")
    public void checkGoodsFeaturesPage() {
        assertTrue(genderCategoriesPage.checkGoodsFeatures());
    }

    @And("User clicks feature {string} button")
    public void clickFeatureButton(String feature) {
        genderCategoriesPage.clickProductFeature(feature);
        productCategoryPage = pageFactoryManager.getProductCategoryPage();
        BasePage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks filters")
    public void checkFilters() {
        assertTrue(productCategoryPage.checkFilters());
    }

    @And("User clicks {string} filter")
    public void clickFilter(String filter) {
        productCategoryPage.clickFilter(filter.substring(0,4));
    }

    @And("count of styles is {string}")
    public void checkCountOfStyles(String countOfStyles) {
        try {
            assertEquals(BasePage.getCountOfStyles().length(),countOfStyles.length());
        }catch (Exception e) {
            assertEquals("0",countOfStyles);
        }
    }

    @And("User hover mouse over {string} category")
    public void hoverMouseOverCategory(String category) {
        BasePage.selectShopMainCategory(category);
    }

    @And("User clicks element {string} in header")
    public void clickElementInHeader(String element) {
        BasePage.clickHeaderElement(element);
        productCategoryPage = pageFactoryManager.getProductCategoryPage();
        BasePage.waitForAjaxToComplete(WAIT_FOR);
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
    public void checkProductName(String productName) {
        assertTrue(BasePage.checkProductName(productName));
    }

    @And("User checks search box")
    public void checkSearchBox() {
        assertTrue(BasePage.checkSearchBox());
    }

    @And("User enters request {string}")
    public void sendRequest(String request) {
        BasePage.sendKeysToSearchBox(request);
    }

    @And("User clicks submit search button")
    public void clickSubmitSearchButton() {
        BasePage.clickSubmitSearch();
        searchPage = pageFactoryManager.getSearchPage();
        BasePage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks that search result {string}")
    public void checkSearchResult(String result) {
        assertEquals(result, searchPage.getResultOfSearch());
    }

    @And("User checks that proposed request correction is {string}")
    public void checkRequestCorrection(String suggestCorrection) {
        assertEquals(searchPage.getCorrectedRequest(),suggestCorrection);
    }

    @And("User checks buttons for changing location")
    public void checkButtonsForChangingLocation() {
        assertTrue(BasePage.checkLocationChangers());
    }

    @And("User clicks {int} changing location button")
    public void clickChangingLocationButton(int buttonPosition) {
        BasePage.clickLocationChanger(buttonPosition);
    }

    @And("User selects location {string}")
    public void selectLocation(String location) {
        BasePage.selectLocation(location);
    }

    @And("User selects currency {string}")
    public void selectsCurrency(String currency) {
        BasePage.selectCurrency(currency);
    }

    @And("User clicks save location button")
    public void clickSaveLocationButton() {
        BasePage.clickSaveLocationButton();
    }

    @And("User checks profile button")
    public void checkProfileButton() {
        assertTrue(BasePage.checkProfileButton());
    }

    @And("User opens {string} link in profile dropdown")
    public void openLinkInProfileDropdown(String linkName) {
        BasePage.clickProfileButton(linkName);
        loginPage = pageFactoryManager.getLoginPage();
        BasePage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks email field")
    public void checkEmailField() {
        assertTrue(loginPage.checkEmailField());
    }

    @And("User checks password field")
    public void checkPasswordField() {
        assertTrue(loginPage.checkPasswordField());
    }

    @And("User enters email {string}")
    public void enterEmail(String email) {
        loginPage.inputEmail(email);
    }

    @And("User enters password {string}")
    public void enterPassword(String password) {
        loginPage.inputPassword(password);
    }

    @And("User clicks submit button")
    public void clickSubmitButton() {
        loginPage.clickSubmitButton();
        myOrdersPage = pageFactoryManager.getMyOrdersPage();
    }

    @And("User checks {string} in profile dropdown")
    public void checkUsernameInProfileDropdown(String username) {
        assertEquals(BasePage.getUsername(), username);
    }
}