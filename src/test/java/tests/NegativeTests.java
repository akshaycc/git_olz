package tests;

import data.NegativeData;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Artem on 30.11.2016.
 */
public class NegativeTests extends TestBase{

    @Test(priority = 1,groups = { "NegativeTests" }, dataProvider = "lessSixCharacter",dataProviderClass = NegativeData.class)
    public void lessSixCharacter(String userMail,String password,String expectedResult){
        Assert.assertEquals(
           accountRegPage
                    .openAccountRegPage()
                    .get()
                    .openRegistrationTab()
                    .typeUserEmailForRegistration(userMail)
                    .clickAcceptCheckbox()
                    .typeUserPassForRegistration(password)
                    .submitRegForm()
                    .getStatusPassword()
        ,expectedResult);
    }

    @Test(priority = 1,groups = { "NegativeTests" }, dataProvider = "emptyPassword",dataProviderClass = NegativeData.class)
    public void emptyPassword(String userMail,String password,String expectedResult){
        Assert.assertEquals(
                accountRegPage
                        .openAccountRegPage()
                        .get()
                        .openRegistrationTab()
                        .typeUserEmailForRegistration(userMail)
                        .clickAcceptCheckbox()
                        .typeUserPassForRegistration(password)
                        .submitRegForm()
                        .getStatusPassword()
                ,expectedResult);
    }
}
