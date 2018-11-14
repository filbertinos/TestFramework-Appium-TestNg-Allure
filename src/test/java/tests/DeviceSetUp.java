package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DeviceSetUp extends BaseTest {
    static AppiumDriver prepareDevice() throws MalformedURLException {
    File file = new File("app");
    File fs = new File(file,"sl.apk");
    DesiredCapabilities dc = new DesiredCapabilities();
    dc.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName);
    dc.setCapability(MobileCapabilityType.APP,fs.getAbsolutePath());
    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

}
