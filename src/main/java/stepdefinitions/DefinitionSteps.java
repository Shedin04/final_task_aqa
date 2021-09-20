package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.Locale;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefinitionSteps {

    private static final long WAIT_FOR = 60;
    WebDriver driver;
    PageFactoryManager pageFactoryManager;
    HomePage homePage;
    GenderCategoriesPage genderCategoriesPage;
    ProductsCategoryPage productsCategoryPage;
    SearchPage searchPage;
    LoginPage loginPage;
    ProductPage productPage;
    WishlistPage wishlistPage;
    BagPage bagPage;

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
        assertEquals(BasePage.getTitle().split("[' ]")[0],pageTitle);
    }

    @And("User checks goods features page")
    public void checkGoodsFeaturesPage() {
        assertTrue(genderCategoriesPage.checkGoodsFeatures());
    }

    @And("User clicks feature {string} button")
    public void clickFeatureButton(String feature) {
        genderCategoriesPage.clickProductFeature(feature);
        productsCategoryPage = pageFactoryManager.getProductCategoryPage();
        BasePage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks filters")
    public void checkFilters() {
        assertTrue(productsCategoryPage.checkFilters());
    }

    @And("User clicks {string} filter")
    public void clickFilter(String filter) {
        productsCategoryPage.clickFilter(filter.substring(0,4));
    }

    @And("User checks that count of styles is displayed")
    public void checkCountOfStyles() {
        assertTrue(BasePage.checkCountOfStyles());
    }

    @And("Count of styles is {string}")
    public void checkCountOfStyles(String countOfStyles) {
        BasePage.waitPageLoadingSpinner();
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
        productsCategoryPage = pageFactoryManager.getProductCategoryPage();
        BasePage.waitForAjaxToComplete(WAIT_FOR);
    }

    @And("User checks {string} name of page")
    public void checkPageName(String name) {
        assertEquals(productsCategoryPage.getPageName()
                        .replace("Men's ","")
                        .replace("Women's ",""), name);
    }

    @And("User checks filter sections")
    public void checkFilterSections() {
        assertTrue(productsCategoryPage.checkFilterSections());
    }

    @And("User selects filter section {string}")
    public void selectFilterSection(String section) {
        productsCategoryPage.clickFilterSection(section);
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

    @And("User checks that current location is {string}")
    public void checkThatCurrentLocationIs(String location) {
        assertEquals(BasePage.getCurrentLocation(), location);
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
    }

    @And("User checks {string} in profile dropdown")
    public void checkUsernameInProfileDropdown(String username) {
        assertEquals(BasePage.getUsername(), username);
    }

    @And("Sign In is {string}")
    public void checkLoginStatus(String status) {
        assertEquals(loginPage.checkLoginStatus(), status);
    }

    @And("User checks reset password link")
    public void checkResetPasswordLink() {
        assertTrue(loginPage.checkResetPasswordLink());
    }

    @And("User clicks reset password link")
    public void clickResetPasswordLink() {
        loginPage.clickResetPasswordLink();
    }

    @And("User checks reset password button")
    public void checkResetPasswordButton() {
        assertTrue(loginPage.checkResetPasswordButton());
    }

    @And("User clicks reset password button")
    public void clickResetPasswordButton() {
        loginPage.clickResetPasswordButton();
    }

    @And("Message {string} is shown")
    public void checkMassageResetPassword(String message) {
        assertEquals(loginPage.checkResetPasswordLinkSentMessage(), message.toUpperCase(Locale.ROOT));
    }

    @And("User selects product with {string} name")
    public void selectProductWithName(String productName) {
        BasePage.selectProductWithName(productName);
        productPage = pageFactoryManager.getProductPage();
    }

    @And("User checks that product hero equals {string} product name")
    public void checkProductHero(String productName) {
        assertEquals(productPage.getProductHero(), productName);
    }

    @And("User checks that wishlist button is displayed")
    public void checkWishlistButton() {
        assertTrue(productPage.checkWishlistButton());
    }

    @And("User clicks add to wishlist button")
    public void clickWishlistButton() {
        productPage.clickWishlistButton();
    }

    @And("User clicks wishlistHeaderButton")
    public void clickWishlistHeaderButton() {
        BasePage.clickWishlistHeaderButton();
        wishlistPage = pageFactoryManager.getWishlistPage();
    }

    @And("User checks that product name in wishlist equals {string}")
    public void checkThatProductNameInWishlistEqualsProductName(String productName) {
        assertEquals(wishlistPage.getProductNameInWishlist(0), productName);
    }

    @And("User checks add to bag button")
    public void checkAddToBagButton() {
        assertTrue(productPage.checkAddToBagButton());
    }

    @And("User clicks add to bag button")
    public void clickAddToBagButton() {
        productPage.clickAddToBagButton();
    }

    @And("User checks that colour select field is {string}")
    public void checkThatColourSelectFieldIs(String colourStatus) {
        assertEquals(productPage.getColourSelectFieldStatus(), colourStatus);
    }

    @And("User checks that size select field is {string}")
    public void checkThatSizeSelectFieldIs(String sizeStatus) {
        assertEquals(productPage.getSizeSelectFieldStatus(), sizeStatus);
    }

    @And("User selects product {string} colour")
    public void selectProductColour(String colour) {
        productPage.selectColour(colour);
    }

    @And("User selects product {string} size")
    public void selectProductSize(String size) {
        productPage.selectSize(size);
    }

    @And("User opens bag page")
    public void openBagPage() {
        BasePage.openBagPage();
        bagPage = pageFactoryManager.getBagPage();
    }

    @And("Users checks that bag item name equals {string}")
    public void checkBagItemNameEquals(String productName) {
        assertTrue(bagPage.checkBagItem(productName));
    }

    @And("User checks that count of goods in wishlist is {int}")
    public void checkCountGoodsInWishlist(int count) {
        assertEquals(wishlistPage.getGoodsCountInWishlist(), count);
    }

    @And("User removes product {string} from wishlist")
    public void removeProductFromWishlistWithName(String productName) {
        wishlistPage.removeProductFromWishlistWithName(productName);
    }

    @And("User moves product {string} to bag")
    public void moveProductWithNameToBag(String productName) {
        wishlistPage.moveToBagFromWishPage(productName);
    }
}