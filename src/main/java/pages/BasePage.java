package pages;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.android.nativekey.KeyEvent;


public class BasePage {
    public AndroidDriver driver;
    public WebDriverWait wait;
    //Constructor
    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
    }
    @Step("Press back button")
    public void pressBack(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

    }
    @Step("Hide keyboard")
    public void hideKeyboard(){
        if(driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }

}
