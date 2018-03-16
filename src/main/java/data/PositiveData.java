package data;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

/**
 * Created by RtB on 05.11.16.
 */
public class PositiveData {

    private static String  userMail = "testforme502@gmail.com";
    private static String  password = "qwerty123!";

    @DataProvider(name = "dataForPositive")
    public static Object[][] dataForPositive() {
        return new Object[][]{
                {userMail, password}

        };
    }

}
