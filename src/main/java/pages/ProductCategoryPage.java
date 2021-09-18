package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductCategoryPage extends BasePage{
    @FindBy(xpath = "//div[@aria-label='Filters']//button/div")
    private List<WebElement> filters;

    @FindBy(xpath = "//p[contains(@class,'styleCount')]")
    private WebElement countOfStyles;

    @FindBy(xpath = "//h1[contains(text(),'')]")
    private WebElement pageName;

    public ProductCategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getCountOfStyles(){
        return countOfStyles.getText().split(" ")[0];
    }

    public String getPageName(){
        waitElement(pageName,WAIT_ELEMENTS);
        return pageName.getText();
    }

    public boolean checkFilters(){
        return waitForElements(filters,WAIT_ELEMENTS);
    }

    public void clickFilter(String filter){
       filters.stream().filter(butt -> butt.getText().substring(0,4).equals(filter)).findFirst().get().click();
    }
}
