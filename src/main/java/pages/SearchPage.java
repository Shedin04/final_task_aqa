package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
    @FindBy(xpath = "//div[@id='search-term-banner']/p[2][contains(text(),'')]")
    private WebElement resultOfSearch;

    @FindBy(xpath = "//div[@id='search-term-banner']/p[3]/span[contains(text(),'')]")
    private WebElement correctedRequest;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public String getResultOfSearch(){
        try {
            return resultOfSearch.getText().replace("\"", "");
        }catch (NoSuchElementException e){
            return "NOTHING MATCHES YOUR SEARCH";
        }
    }

    public String getCorrectedRequest(){
        return correctedRequest.getText().replace("'","");
    }
}