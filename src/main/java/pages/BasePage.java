package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected final static int ELEMENT_TIMEOUT = 10;
    protected final static int WAIT_ELEMENTS = 15;
    protected static WebDriver driver;
    protected static Actions actions;

    @FindBy(xpath = "//div[contains(@class,'headroom headroom')]//div[@data-testid='header']//a[contains(text(),'') and not(ancestor::div[@id='myaccount-dropdown'])]")
    protected static List<WebElement> headerButtonsWithoutProfile;

    @FindBy(xpath = "//nav[contains(@aria-label,'products') and not (contains(@aria-hidden,'true'))]//button[contains(text,'')]")
    protected static List<WebElement> headerShopMainCategories;

    @FindBy(xpath = "//button[@aria-expanded='true']//following-sibling::div[1]//a")
    protected static List<WebElement> elementsInHeader;

    @FindBy(xpath = "//input[@type='search']")
    protected static WebElement searchBox;

    @FindBy(xpath = "//button[@data-testid='search-button-inline']")
    protected static WebElement submitSearchButton;

    @FindBy(xpath = "//p[contains(@class,'styleCount')]")
    private static WebElement countOfStyles;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public static boolean checkLogo(){
        return headerButtonsWithoutProfile.get(0).isDisplayed();
    }

    public static void clickLogo(){
        headerButtonsWithoutProfile.get(0).click();
    }

    public boolean checkHeaderButtons(){
        return waitForElements(headerButtonsWithoutProfile,WAIT_ELEMENTS);
    }

    public static void clickHeaderButtons(String button){
        if (button.length()<=5) {headerButtonsWithoutProfile.stream().filter(name -> name.getText().equals(button))
                .findFirst().get().click(); }
        else headerButtonsWithoutProfile.stream().filter(name -> {
            if (name.getAttribute("aria-label") != null) return name.getAttribute("aria-label").substring(0,3).equals(button.substring(0,3));
            return false;
        }).findFirst().get().click();
    }

    public static void selectShopMainCategory(String category){
        actions.moveToElement(headerShopMainCategories.stream().filter(categ -> categ.getText().equals(category)).findFirst().get()).perform();
    }

    public static void clickHeaderElement(String element){
        waitForElements(elementsInHeader,ELEMENT_TIMEOUT);
        elementsInHeader.stream().filter(el -> el.getText().equals(element)).findFirst().get().click();
    }

    public static boolean checkSearchBox(){
        return waitElement(searchBox,ELEMENT_TIMEOUT);
    }

    public static void sendKeysToSearchBox(String request){
        searchBox.sendKeys(request);
    }

    public static void clickSubmitSearch(){
        submitSearchButton.click();
    }

    public static String getCountOfStyles(){
        return countOfStyles.getText().split(" ")[0];
    }

    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForAjaxToComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active == 0;"));
    }

    public static boolean waitElement(WebElement element, long timeToWait) {
        try {
            new WebDriverWait(driver, timeToWait).until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean waitForElements(List<WebElement> elements, long timeToWait) {
        try{
            new WebDriverWait(driver, timeToWait).until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
