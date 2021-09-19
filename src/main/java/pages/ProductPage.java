package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
    @FindBy(xpath = "//div[@class='product-hero']/h1[contains(text,'')]")
    private WebElement productHero;

    @FindBy(xpath = "//button[@class='save-button']")
    private WebElement wishlistButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductHero(){
        return productHero.getText();
    }

    public boolean checkWishlistButton(){
        return waitElement(wishlistButton, WAIT_EL);
    }

    public void clickWishlistButton(){
        wishlistButton.click();
        waitForPageLoadComplete(WAIT_ELEMENTS);
    }
}
