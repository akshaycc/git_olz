package pages;

import helpermethods.CustomLoadableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by RtB on 05.11.16.
 */
public class MainPage extends CustomLoadableComponent<MainPage>{

    private WebDriver driver;
    private static By buttonLocator = By.xpath("//*[@id='gobutton']");



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void load() {}

    protected void isLoaded() throws Error {}

    public MainPage openAccountPage(){
        new AccountRegPage(driver);
        return this;
    }
}
