package tests;

import data.PositiveData;
import org.testng.annotations.Test;

/**
 * Created by abylozor on 10/25/2016.
 */
public class PositiveTests extends TestBase{

    @Test(priority = 1,groups = { "PositiveTests","login"} , dataProvider = "dataForPositive",dataProviderClass = PositiveData.class)
    public void registrationNewAccount(String userMail,String password){

        accountRegPage.openAccountRegPage().get()
                .openRegistrationTab()
                .clickAcceptCheckbox()
                .typeUserEmailForRegistration(userMail)
                .typeUserPassForRegistration(password)
                .submitRegForm()
                .confirmOpenNextPage("Сейчас вы должны активировать ваш аккаунт!")
                .confirmRegistrationOnMail(userMail,password,"Пароль создан успешно");
    }

    @Test(priority = 2,groups = { "PositiveTests","logout"} , dataProvider = "dataForPositive",dataProviderClass = PositiveData.class)
    public void deleteAccount(String userMail,String password){

        myAccountPage.login(userMail,password)
                .clickTopLoginLink()
                .openMyAccountSettingsPage()
                .clickRemoveAccountTab()
                .clickButtonRemoveAccount()
                .submitDeleteMessage()
                .confirmOpenNextPage("E-mail был отправлен")
                .confirmDeleteAccountOnMail(userMail,password,"Спасибо");

    }
}
