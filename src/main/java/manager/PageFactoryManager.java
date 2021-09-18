package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageFactoryManager {
    protected WebDriver driver;

    public PageFactoryManager(WebDriver driver){this.driver = driver;}

    public HomePage getHomePage(){return new HomePage(driver);}

    public GenderCategoriesPage getGenderCategoriesPage(){return new GenderCategoriesPage(driver);}

    public ProductCategoryPage getProductCategoryPage() {return new ProductCategoryPage(driver);}
}