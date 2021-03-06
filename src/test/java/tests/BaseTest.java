package tests;

import config.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import pages.InnerListPage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class BaseTest {

    private final static String deviceName = "Nexus5";
    private final static String pathToFile = "app/sl.apk";

    private AndroidDriver driver;
    private AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();

    public AndroidDriver getDriver() {
        return driver;
    }

    HomePage homePage;
    InnerListPage innerListPage;


    @BeforeSuite
    public void setUp() throws IOException {
        service.start();
        prepareDeviceForLocalTesting();
        homePage = new HomePage(driver);
        innerListPage = new InnerListPage(driver);
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
        service.stop();
        //Runtime.getRuntime().exec("adb devices | grep emulator | cut -f1 | while read line; do adb -s $line emu kill; done");
    }


    @AfterMethod
    public void hideKeyboard(){
        if(driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
    }

    private void prepareDeviceForLocalTesting() throws MalformedURLException {
        File fs = new File(pathToFile);
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        dc.setCapability("avd",deviceName);
        dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
        driver = DriverManager.getInstance(1,dc);
    }
}
