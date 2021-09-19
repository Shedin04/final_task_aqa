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
    protected final static int WAIT_EL = 10;
    protected final static int WAIT_ELEMENTS = 15;
    protected static WebDriver driver;
    protected static Actions actions;

    @FindBy(xpath = "//div[contains(@class,'headroom headroom')]//div[@data-testid='header']//a[contains(text(),'') and not(ancestor::div[@id='myaccount-dropdown'])]")
    private static List<WebElement> headerButtonsWithoutProfile;

    @FindBy(xpath = "//nav[contains(@aria-label,'products') and not (contains(@aria-hidden,'true'))]//button[contains(text,'')]")
    private static List<WebElement> headerShopMainCategories;

    @FindBy(xpath = "//button[@aria-expanded='true']//following-sibling::div[1]//a")
    private static List<WebElement> elementsInHeader;

    @FindBy(xpath = "//input[@type='search']")
    private static WebElement searchBox;

    @FindBy(xpath = "//button[@data-testid='search-button-inline']")
    private static WebElement submitSearchButton;

    @FindBy(xpath = "//p[contains(@class,'styleCount')]")
    private static WebElement countOfStyles;

    @FindBy(xpath = "//div[contains(@data-auto-id,'Description')]")
    private static List<WebElement> goodsDescription;

    @FindBy(xpath = "//div[@data-testid='country-selector']/button")
    private static List<WebElement> locationChangers;

    @FindBy(xpath = "//select[@id='country']")
    private static WebElement selectCountryField;

    @FindBy(xpath = "//select[@id='currency']")
    private static WebElement selectCurrencyField;

    @FindBy(xpath = "//select[@id='country']/option")
    private static List<WebElement> countries;

    @FindBy(xpath = "//select[@id='currency']/option")
    private static List<WebElement> currencies;

    @FindBy(xpath = "//button[contains(@data-testid,'save')]")
    private static WebElement saveLocationButton;

    @FindBy(xpath = "//div[contains(@id,'myAccount')]")
    private static WebElement profileButton;

    @FindBy(xpath = "//div[contains(@data-testid,'dropdown')]//a[contains(text,'')]")
    private static List<WebElement> linksInProfileList;

    @FindBy(xpath = "//div[contains(@data-testid,'dropdown')]//span[@class='tiqiyps']")
    private static WebElement username;

    @FindBy(xpath = "//div[contains(*,'ASOS') and contains(*,'2021')]")
    private static WebElement copyrightsTag;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public static void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitForAjaxToComplete(long timeToWait) {
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

    public static void waitForCopyrightsTeg(){
        try {
            new WebDriverWait(driver,WAIT_ELEMENTS).until(ExpectedConditions.elementToBeClickable(copyrightsTag));
        }catch (Exception ignored){}
    }

    public static String getTitle(){
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

    public static void clickWishlistHeaderButton(){
        headerButtonsWithoutProfile.get(3).click();
    }

    public static void selectShopMainCategory(String category){
        actions.moveToElement(headerShopMainCategories.stream().filter(categ -> categ.getText().equals(category)).findFirst().get()).perform();
    }

    public static void clickHeaderElement(String element){
        waitForElements(elementsInHeader, WAIT_EL);
        elementsInHeader.stream().filter(el -> el.getText().equals(element)).findFirst().get().click();
    }

    public static boolean checkSearchBox(){
        return waitElement(searchBox, WAIT_EL);
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

    public static boolean checkProductName(String productName){
        waitForElements(goodsDescription,WAIT_ELEMENTS);
        return goodsDescription.stream().anyMatch(product -> product.getText().split(" ")[0].equals(productName));
    }

    public static void selectProductWithName(String productName){
        goodsDescription.stream().filter(product -> product.getText().equals(productName)).findFirst().get().click();
    }

    public static boolean checkLocationChangers(){
       return waitForElements(locationChangers,WAIT_ELEMENTS);
    }

    public static void clickLocationChanger(int buttonPosition){
        locationChangers.get(buttonPosition).click();
        waitElement(selectCountryField, WAIT_EL);
    }

    public static void selectLocation(String location){
        selectCountryField.click();
        waitForElements(countries,WAIT_ELEMENTS);
        countries.stream().filter(cntry -> cntry.getText().equals(location)).findFirst().get().click();
        waitElement(selectCurrencyField, WAIT_EL);
    }

    public static void selectCurrency(String currency){
        selectCurrencyField.click();
        waitForElements(currencies,WAIT_ELEMENTS);
        currencies.stream().filter(curr -> curr.getText().split(" ")[1].equals(currency)).findFirst().get().click();
        waitElement(selectCountryField, WAIT_EL);
    }

    public static void clickSaveLocationButton(){
        saveLocationButton.click();
        waitForAjaxToComplete(WAIT_ELEMENTS);
    }

    public static boolean checkProfileButton(){
        return waitElement(profileButton, WAIT_EL);
    }

    public static void clickProfileButton(String linkName){
        actions.moveToElement(profileButton).perform();
        waitForElements(linksInProfileList,WAIT_ELEMENTS);
        linksInProfileList.stream().filter(link -> link.getText().equals(linkName)).findFirst().get().click();
    }

    public static String getUsername(){
        actions.moveToElement(profileButton).perform();
        waitForElements(linksInProfileList,WAIT_ELEMENTS);
        waitElement(username, WAIT_EL);
        return username.getText().replace("Hi ","");
    }
}
