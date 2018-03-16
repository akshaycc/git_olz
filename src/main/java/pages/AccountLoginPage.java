package pages;

import helpermethods.CustomLoadableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Artem on 01.12.2016.
 */
public class AccountLoginPage extends CustomLoadableComponent<AccountRegPage> {


    private WebDriver driver;

    private static By loginTabLocator = By.xpath("//*[@id='login_tab']");
    private static By regTabLocatorLocator = By.xpath("//*[@id='register_tab']");
    private static By userEmailLoginLocator = By.xpath(".//*[@id='userEmail']");
    private static By userPassLoginLocator = By.xpath(".//*[@id='userPass']");
    private static By buttonForLoginLocator = By.xpath(".//*[@id='se_userLogin']");

    private static By forUserPassRegisterLocator = By.xpath("//*[@for='userPassRegister']");
    private static By loginFormLocator = By.xpath(".//*[@id='loginForm']");
    private static By acceptCheckboxLocator = By.xpath("//*[@for='checkbox_accept-terms']");

    private final String ACCOUNTPAGEURL="https://www.olx.ua/account/";


    public AccountLoginPage(WebDriver driver) {
        //driver.get(ACCOUNTPAGEURL);
        this.driver = driver;

    }
    public AccountLoginPage openAccountLoginPage(){
        driver.get(ACCOUNTPAGEURL);
        return this;
    }


    public AccountLoginPage openLoginTab(){
        driver.findElement(loginTabLocator).click();
        return this;
    }

    public AccountLoginPage typeUserEmailForLogin(String mail){
        driver.findElement(userEmailLoginLocator).clear();
        driver.findElement(userEmailLoginLocator).sendKeys(mail);
        return this;
    }
    public AccountLoginPage typeUserPassForLogin(String password){
        driver.findElement(userPassLoginLocator).clear();
        driver.findElement(userPassLoginLocator).sendKeys(password);
        return this;
    }

    public AccountLoginPage submitLoginForm(){
        driver.findElement(loginFormLocator).submit();
        return this;
    }
    public AccountLoginPage clickLoginButton(){
        driver.findElement(buttonForLoginLocator).click();
        return this;
    }

    public AccountLoginPage login(String user,String password){
        openAccountLoginPage();
        openLoginTab();
        typeUserEmailForLogin(user);
        typeUserPassForLogin(password);
        clickLoginButton();
        return this;
    }


    protected void load() {     }

    protected void isLoaded() throws Error {  }
}
