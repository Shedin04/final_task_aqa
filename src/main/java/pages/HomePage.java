package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//picture//img")
    private WebElement pictureOnHomePage;

    @FindBy(xpath = "//a[@class='mu__cta']")
    private List<WebElement> centralShopButtons;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public boolean checkPicture(){
        return waitElement(pictureOnHomePage,ELEMENT_TIMEOUT);
    }

    public boolean checkCentralButtons(){
        return waitForElements(centralShopButtons,WAIT_ELEMENTS);
    }

    public void clickCentralShopButton(String button){
        centralShopButtons.stream().filter(name -> name.getText().equals(button)).findFirst().get().click();
    }
}