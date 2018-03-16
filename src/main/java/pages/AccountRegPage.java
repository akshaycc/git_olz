package pages;

import helpermethods.CustomLoadableComponent;
import helpermethods.JavaMailReader;
import helpermethods.PageLoad;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import javax.mail.MessagingException;
import java.io.IOException;


/**
 * Created by RtB on 06.11.16.
 */
public class AccountRegPage extends CustomLoadableComponent<AccountRegPage> {

    private WebDriver driver;

    private static By loginTabLocator = By.xpath(".//*[@id='login_tab']");
    private static By regTabLocator = By.xpath(".//*[@id='register_tab']");
    private static By userEmailRegisterLocator = By.xpath("//*[@id='userEmailRegister']");
    private static By userPassRegisterLocator = By.xpath("//*[@id='userPassRegister']");
    private static By forUserPassRegisterLocator = By.xpath("//*[@for='userPassRegister']");
    private static By registerFormLocator = By.xpath("//*[@id='registerForm']");
    private static By acceptCheckboxLocator = By.xpath("//*[@for='checkbox_accept-terms']");
    private static By confirmTextPagLocator = By.xpath(".//*[@id='body-container']//strong");

    private final String ACCOUNTPAGEURL="https://www.olx.ua/account/";


    public AccountRegPage(WebDriver driver) {
        //driver.get(ACCOUNTPAGEURL);
        this.driver = driver;

    }
    public AccountRegPage openAccountRegPage(){
        driver.get(ACCOUNTPAGEURL);
        return this;
    }

    public AccountRegPage openRegistrationTab(){
        driver.findElement(regTabLocator).click();
        return this;
    }

    public AccountRegPage typeUserEmailForRegistration(String mail){
        driver.findElement(userEmailRegisterLocator).clear();
        driver.findElement(userEmailRegisterLocator).sendKeys(mail);
        return this;
    }
    public AccountRegPage typeUserPassForRegistration(String password){
        driver.findElement(userPassRegisterLocator).clear();
        driver.findElement(userPassRegisterLocator).sendKeys(password);
        return this;
    }

    public AccountRegPage submitRegForm(){
        driver.findElement(registerFormLocator).submit();
        return this;
    }
    public AccountRegPage clickAcceptCheckbox(){
        driver.findElement(acceptCheckboxLocator).click();
        return this;
        }

    public String getStatusPassword(){
        PageLoad.myElementIsVisibility(driver,forUserPassRegisterLocator);
        return driver.findElement(forUserPassRegisterLocator).getText();
    }

    public AccountRegPage confirmOpenNextPage(String confirmText){
        PageLoad.myElementIsVisibility(driver,confirmTextPagLocator);
        String res =driver.findElement(confirmTextPagLocator).getText();
        Assert.assertEquals(res,confirmText);
        return this;
    }

    public AccountRegPage confirmRegistrationOnMail(String userMail, String password, String confirmTextRegistration){
        String RegUrl ="";
        try {
            RegUrl = JavaMailReader.getRegistrationLink(userMail,password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(RegUrl);
        Assert.assertEquals(
                driver.findElement(confirmTextPagLocator).getText()
                ,confirmTextRegistration
        );
        return this;
    }

    protected void load() {}
    @Override
    protected void isLoaded() throws Error {
        if(!PageLoad.myElementIsClickable(this.driver,loginTabLocator)) {
            throw new Error("AccountRegPage was not successfully loaded");
        }
    }
}
