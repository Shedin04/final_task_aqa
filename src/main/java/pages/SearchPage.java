package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
    @FindBy(xpath = "//div[@id='search-term-banner']/p[2]")
    private WebElement resultOfSearch;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public String getResultOfSearch(){
        return resultOfSearch.getText();
    }
}
