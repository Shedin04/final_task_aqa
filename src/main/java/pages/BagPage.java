package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BagPage extends BasePage{
    @FindBy(xpath = "//p[@class='bag-item-name']")
    private List<WebElement> bagItems;

    public BagPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkBagItem(String productName){
        waitForPageLoadComplete(WAIT_ELEMENTS);
        waitForElements(bagItems,WAIT_ELEMENTS);
        return bagItems.stream().anyMatch(item -> item.getText().equals(productName));
    }
}
