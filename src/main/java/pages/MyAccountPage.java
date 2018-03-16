package pages;

import helpermethods.CustomLoadableComponent;
import helpermethods.JavaMailReader;
import helpermethods.PageLoad;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Key;
import java.util.List;

/**
 * Created by Artem on 01.12.2016.
 */
public class MyAccountPage extends CustomLoadableComponent<AccountRegPage> {

    private WebDriver driver;

    private static By loginTabLocator = By.xpath("//*[@id='login_tab']");
    private static By myLoginLocator = By.xpath(".//*[@id='topLoginLink']/span/strong");
    private static By removeAccountTabLocator = By.xpath(".//*[@id='se_deleteAccount']");
    private static By removeAccountButtonLocator = By.xpath(".//*[@id='deleteAccount']");
    private static By deleteAccountFormLocator = By.xpath(".//*[@id='deleteAccountForm']");
    private static By deleteAccountButtonLocator = By.xpath(".//*[@id='removeInput']");
    private static By confirmTextPagLocator = By.xpath(".//*[@id='body-container']//strong");


    private final String MYACCOUNTPAGEURL="https://www.olx.ua/myaccount/";
    private final String MYACCOUNTSETTINGSPAGEURL="https://www.olx.ua/myaccount/settings/";


    public MyAccountPage(WebDriver driver) {
        //driver.get(MYACCOUNTPAGEURL);
        this.driver = driver;

    }
    public MyAccountPage openMyAccountPage(){
        driver.get(MYACCOUNTPAGEURL);
        return this;
    }

    public MyAccountPage clickTopLoginLink(){
        //new Actions(driver).keyDown(driver.findElement(By.xpath(".//*[@id='userLoginBox']")),Keys.ENTER ).build().perform();
        //System.out.println(driver.findElement(By.xpath(".//*[@id='login-box-settings']")).isDisplayed());
        driver.getPageSource();
        return this;
    }

    public MyAccountPage openMyAccountSettingsPage(){
        driver.get(MYACCOUNTSETTINGSPAGEURL);
        return this;
    }
    public MyAccountPage clickRemoveAccountTab(){
        PageLoad.myElementIsClickable(driver,removeAccountTabLocator);
        driver.findElement(removeAccountTabLocator).click();
        return this;
    }
    public MyAccountPage clickButtonRemoveAccount(){
        PageLoad.myElementIsClickable(driver,removeAccountButtonLocator);
        driver.findElement(removeAccountButtonLocator).click();
        return this;
    }

    public MyAccountPage submitDeleteMessage(){
        PageLoad.myElementIsClickable(driver,deleteAccountFormLocator);
        driver.findElement(deleteAccountButtonLocator).click();
        return this;
    }
    public MyAccountPage confirmOpenNextPage(String confirmText){
        PageLoad.myElementIsVisibility(driver,confirmTextPagLocator);
        String res =driver.findElement(confirmTextPagLocator).getText();
        Assert.assertEquals(res,confirmText);
        return this;
    }

    public MyAccountPage confirmDeleteAccountOnMail(String userMail, String password, String confirmTextRegistration){
        String RegUrl ="";
        try {
            RegUrl = JavaMailReader.getLinkRemoveAccount(userMail,password);
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

    public String getMyLogin(){
        String login = driver.findElement(myLoginLocator).getText();
        return login;
    }
    public MyAccountPage login(String userMail,String password){

        new AccountLoginPage(driver)
                .openAccountLoginPage()
                .openLoginTab()
                .typeUserEmailForLogin(userMail)
                .typeUserPassForLogin(password)
                .clickLoginButton();
        Assert.assertEquals(getMyLogin(),userMail.substring(0,userMail.indexOf("@")));



        return this;
    }
    protected void load() {     }

    protected void isLoaded() throws Error {  }
}
