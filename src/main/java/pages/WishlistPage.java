package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends BasePage{
    @FindBy(xpath = "//div[contains(@class,'title')]//p")
    private WebElement productNameInWishlist;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameInWishlist(){
        waitElement(productNameInWishlist, WAIT_EL);
        return productNameInWishlist.getText();
    }
}
