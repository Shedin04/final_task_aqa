package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage{
    @FindBy(xpath = "//div[@class='product-hero']/h1[contains(text,'')]")
    private WebElement productHero;

    @FindBy(xpath = "//button[@class='save-button']")
    private WebElement wishlistButton;

    @FindBy(xpath = "//span[contains(text(),'Add to')]")
    private WebElement addToBagButton;

    @FindBy(xpath = "//select[@data-id='colourSelect']")
    private WebElement colourSelectField;

    @FindBy(xpath = "//select[@data-id='colourSelect']/option[contains(text(),'')]")
    private List<WebElement> colourList;

    @FindBy(xpath = "//select[@data-id='sizeSelect']")
    private WebElement sizeSelectField;

    @FindBy(xpath = "//select[@data-id='sizeSelect']/option[contains(text(),'')]")
    private List<WebElement> sizeList;

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

    public boolean checkAddToBagButton(){
        return waitElement(addToBagButton, WAIT_EL);
    }

    public void clickAddToBagButton(){
        addToBagButton.click();
        waitForPageLoadComplete(WAIT_ELEMENTS);
    }

    public String getColourSelectFieldStatus(){
        if (colourSelectField.isDisplayed()) return "available";
        else return "unavailable";
    }

    public String getSizeSelectFieldStatus(){
        if (sizeSelectField.isDisplayed()) return "available";
        else return "unavailable";
    }

    public void selectColour(String colour){
        actions.clickAndHold(colourSelectField);
        colourList.stream().filter(col -> col.getText().equals(colour)).findFirst().get().click();
        waitElement(colourSelectField,WAIT_EL);
    }

    public void selectSize(String size){
        actions.clickAndHold(sizeSelectField).perform();
        sizeList.stream().filter(sz -> sz.getText().equals(size)).findFirst().get().click();
        waitElement(sizeSelectField,WAIT_EL);
    }
}
