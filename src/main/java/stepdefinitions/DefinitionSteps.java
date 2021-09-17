package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.HomePage;

import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DefinitionSteps {

    private static final long WAIT_FOR = 60;
    WebDriver driver;
    HomePage homePage;
    PageFactoryManager pageFactoryManager;

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

    @And("User clicks home page {string} button")
    public void clickHomePageCentralButtons(String button) {
        homePage.clickCentralShopButton(button);
    }

    @And("User checks image on homepage")
    public void checkImageOnHomepage() {
        assertTrue(homePage.checkPicture());
    }

    @And("Page with title {string} is displayed")
    public void pageWithTitleIsDisplayed(String pageTitle) {
        assertEquals(List.of(homePage.getTitle().split("'| ")).get(0),pageTitle);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}