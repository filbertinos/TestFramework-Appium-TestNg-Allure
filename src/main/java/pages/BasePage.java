package pages;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.android.nativekey.KeyEvent;


public class BasePage{
    protected AndroidDriver driver;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


    @Step("Press back button")
    public BasePage pressBack(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        return this;

    }
    @Step("Hide keyboard")
    public BasePage hideKeyboard(){
        if(driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
        return this;
    }

}
