package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//div[@class='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name='Username']")
    private WebElement eMailField;

    @FindBy(xpath = "//input[@name='Password']")
    private WebElement passwordField;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkEmailField(){
        return waitElement(eMailField,ELEMENT_TIMEOUT);
    }

    public void inputEmail(String email){
        eMailField.sendKeys(email);
    }

    public boolean checkPasswordField(){
        return waitElement(passwordField,ELEMENT_TIMEOUT);
    }

    public void inputPassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickSubmitButton(){
        submitButton.click();
        BasePage.waitForCopyrightsTeg();
    }
}
