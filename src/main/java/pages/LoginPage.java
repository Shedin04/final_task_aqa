package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//div[@class='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement eMailField;

    @FindBy(xpath = "//input[@name='Password']")
    private WebElement passwordField;

    @FindBy(xpath = "//li[contains(text(),'email address or password were incorrect')]")
    private WebElement signupErrorMessage;

    @FindBy(xpath = "//a[contains(@class,'forgot-password')]")
    private WebElement resetPasswordLink;

    @FindBy(xpath = "//input[contains(@value,'Reset')]")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//div[contains(@class,'password-sent')]//span[contains(text,'')]")
    private WebElement resetPasswordLinkSentMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkEmailField(){
        return waitElement(eMailField, WAIT_EL);
    }

    public void inputEmail(String email){
        eMailField.sendKeys(email);
    }

    public boolean checkPasswordField(){
        return waitElement(passwordField, WAIT_EL);
    }

    public void inputPassword(String password){
        passwordField.sendKeys(password);
    }

    public boolean checkResetPasswordLink(){
        return waitElement(resetPasswordLink, WAIT_EL);
    }

    public void clickResetPasswordLink(){
        resetPasswordLink.click();
        waitForPageLoadComplete(WAIT_ELEMENTS);
    }

    public boolean checkResetPasswordButton(){
        return waitElement(resetPasswordButton, WAIT_EL);
    }

    public void clickResetPasswordButton(){
        resetPasswordButton.click();
    }

    public void clickSubmitButton(){
        submitButton.click();
        BasePage.waitForCopyrightsTeg();
    }

    public String checkLoginStatus(){
        try {
            signupErrorMessage.isDisplayed();
        }catch (Exception e){
            return "success";
        }
        return "fail";
    }

    public String checkResetPasswordLinkSentMessage(){
        return resetPasswordLinkSentMessage.getText();
    }
}
