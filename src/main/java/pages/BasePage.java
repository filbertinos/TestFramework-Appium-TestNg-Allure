package pages;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.nativekey.KeyEvent;
import utilities.AndroidElementsActions;

public class BasePage{
    AndroidDriver driver;
    AndroidElementsActions androidElementsActions;

    BasePage(AndroidDriver driver) {
        this.driver = driver;
        androidElementsActions = new AndroidElementsActions(driver);
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
