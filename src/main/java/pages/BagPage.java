package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BagPage extends BasePage{
    @FindBy(xpath = "//p[@class='bag-item-name']")
    private List<WebElement> bagItems;

    @FindBy(xpath = "//div[contains(@class,'total')]//div[@class='bag-total-wrapper']//p[@class='bag-total-button-holder']/a")
    private WebElement checkOutButton;

    public BagPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkBagItem(String productName){
        waitForPageLoadComplete(WAIT_ELEMENTS);
        waitForElements(bagItems,WAIT_ELEMENTS);
        waitElement(checkOutButton,WAIT_EL);
        try {
            bagItems.stream().anyMatch(item -> item.getText().equals(productName));
        }catch (Exception e) {return false;}
        return true;
    }
}
