package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishlistPage extends BasePage{
    @FindBy(xpath = "//div[contains(@class,'title')]//p")
    private List<WebElement> productNameInWishlist;

    @FindBy(xpath = "//button[@aria-label='Delete']")
    private List<WebElement> deleteFromWishlistButtons;

    @FindBy(xpath = "//button[contains(text(),'to bag')]")
    private List<WebElement> moveToBagButton;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameInWishlist(int productNumber){
        waitPageLoadingSpinner();
        return productNameInWishlist.get(productNumber).getText();
    }

    public int getGoodsCountInWishlist(){
        waitForElements(productNameInWishlist, WAIT_ELEMENTS);
        return productNameInWishlist.size();
    }

    public void removeProductFromWishlistWithName(String productName){
        waitForPageLoadComplete(WAIT_ELEMENTS);
        final int[] id = {-1};
        productNameInWishlist.forEach(prod -> {
          if (prod.getText().equals(productName)) id[0]++;
        });
        deleteFromWishlistButtons.get(id[0]).click();
    }

    public void moveToBagFromWishPage(String productName){
        waitForPageLoadComplete(WAIT_ELEMENTS);
        final int[] id = {-1};
        productNameInWishlist.forEach(prod -> {
            if (prod.getText().equals(productName)) id[0]++;
        });
        waitElement(moveToBagButton.get(id[0]),WAIT_EL);
        moveToBagButton.get(id[0]).click();
    }
}