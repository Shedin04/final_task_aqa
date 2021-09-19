package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsCategoryPage extends BasePage{
    @FindBy(xpath = "//div[@aria-label='Filters']//button")
    private List<WebElement> filters;

    @FindBy(xpath = "//button[@aria-expanded='true']//following-sibling::div[@data-filter-dropdown='true']//li")
    private List<WebElement> filterSections;

    @FindBy(xpath = "//h1[contains(text(),'')]")
    private WebElement pageName;

    public ProductsCategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageName(){
        waitElement(pageName,WAIT_ELEMENTS);
        return pageName.getText();
    }

    public boolean checkFilters(){
        return waitForElements(filters,WAIT_ELEMENTS);
    }

    public void clickFilter(String filter){
       actions.click(filters.stream().filter(butt -> butt.getText().substring(0,4).equals(filter)).findFirst().get()).build().perform();
    }

    public boolean checkFilterSections(){return waitForElements(filterSections,WAIT_ELEMENTS);}

    public void clickFilterSection(String section){
        actions.click(filterSections.stream().filter(sect -> sect.getText()
                .split("\\(")[0].equals(section)).findFirst().get()).perform();
    }
}
