package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.Log;

import java.io.IOException;

public class BaseTest {
    public static AndroidDriver driver;
    public WebDriverWait wait;
    private AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
    static String deviceName = "Nexus5";
    public AppiumDriver getDriver() {
        return driver;
    }
    @BeforeSuite
    public void setUp() throws IOException {
        service.start();
        Runtime.getRuntime().exec("/Users/afilippov/Library/Android/sdk/tools/emulator -avd Nexus5 -netdelay none -netspeed full");
        DeviceSetUp.prepareDevice();
        wait = new WebDriverWait(driver,15);
    }
    @AfterSuite
    public void tearDown() throws IOException {
        driver.quit();
        service.stop();
        Runtime.getRuntime().exec("adb devices | grep emulator | cut -f1 | while read line; do adb -s $line emu kill; done");
    }
    @AfterMethod
    public void hideKeyboard(){
        if(driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }
}
