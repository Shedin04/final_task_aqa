package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GenderCategoriesPage extends BasePage{
    @FindBy(xpath = "//div[contains(@class,'feature__title') or (contains(@class,'moment__titleWrap'))]/*[contains(text(),'')]")
    private List<WebElement> goodsFeatures;

    public GenderCategoriesPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkGoodsFeatures(){
        return waitForElements(goodsFeatures,WAIT_ELEMENTS);
    }

    public void clickProductFeature(String feature){
        goodsFeatures.stream().filter(butt -> butt.getText().equals(feature)).findFirst().get().click();
    }
}
