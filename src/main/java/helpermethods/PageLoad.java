package helpermethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class PageLoad {

    private static int countElementTable=1;
	public static boolean myElementIsClickable (WebDriver driver, By by) {
		
		try
        {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(by));
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        return true;		
	}

    public static boolean myElementIsVisibility (WebDriver driver, By by) {

        try
        {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        return true;
    }

    public static boolean newRowHasAdded (WebDriver driver, final By by) {

        try
        {
            new WebDriverWait(driver, 10).until(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver driver) {
                        int currentCountElement = driver.findElements(by).size();
                        if (currentCountElement >= countElementTable) {
                            //System.out.println(currentCountElement);
                            //System.out.println(countElementTable);
                            countElementTable++;
                            return true;
                        }
                        else
                            return false;
                    }
                });
        }
        catch (WebDriverException ex)
        {
            return false;
        }
        return true;
    }
}
