package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    protected final static int ELEMENT_TIMEOUT = 10;
    protected final static int WAIT_ELEMENTS = 15;
    protected WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'headroom headroom')]//div[@data-testid='header']//a[contains(text(),'') and not(ancestor::div[@id='myaccount-dropdown'])]")
    protected static List<WebElement> headerButtonsWithoutProfile;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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

    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void waitForAjaxToComplete(long timeToWait) {
        new WebDriverWait(driver, timeToWait).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return window.jQuery != undefined && jQuery.active == 0;"));
    }

    public boolean waitElement(WebElement element, long timeToWait) {
        try {
            new WebDriverWait(driver, timeToWait).until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean waitForElements(List<WebElement> elements, long timeToWait) {
        try{
            new WebDriverWait(driver, timeToWait).until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
