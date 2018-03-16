package tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AccountLoginPage;
import pages.AccountRegPage;
import pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.MyAccountPage;

/**
 * Created by abylozor on 10/25/2016.
 */

public class TestBase{

    protected static WebDriver webDriver;
    protected static MainPage mainPage;
    protected static AccountRegPage accountRegPage;
    protected static AccountLoginPage accountLoginPage;
    protected static MyAccountPage myAccountPage;

    @BeforeTest(groups = {"SetUP"})
    @Parameters({"test-uri","browser","sys"})
    public void setUp(@Optional("localhost:8080")String url, @Optional("CHROME")String browser, @Optional("WINDOWS")String sys) throws Exception {
        initDriver(sys,browser);
        openURI(url);
        mainPage =new MainPage(webDriver);
        accountRegPage =new AccountRegPage(webDriver);
        accountLoginPage =new AccountLoginPage(webDriver);
        myAccountPage =new MyAccountPage(webDriver);
    }

    private void initDriver(String sys,String browser) {
        if (sys.equalsIgnoreCase("MAC")){
            if (browser.equalsIgnoreCase("FF")) {
                System.setProperty("webdriver.gecko.driver", "ssrc//main//resources//selenium//geckodriver");
                webDriver = new FirefoxDriver();
            }
            if (browser.equalsIgnoreCase("CHROME")) {
                System.setProperty("webdriver.chrome.driver", "src//main//resources//selenium//chromedriver");
                webDriver = new ChromeDriver();
            }
        }//src/main/resources/selenium
        if (sys.equalsIgnoreCase("WINDOWS")){
            if (browser.equalsIgnoreCase("FF")) {
                System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\selenium\\geckodriver.exe");
                webDriver = new FirefoxDriver();
            }
            if (browser.equalsIgnoreCase("CHROME")) {
                System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\chromedriver.exe");
                webDriver = new ChromeDriver();
            }
        }
    }

    @AfterTest(groups = {"SetUP"})
    public void afterSuite(){
        webDriver.close();
    }

    protected void openURI(String uri){
        webDriver.get(uri);
        webDriver.manage().window().maximize();
    }
}

