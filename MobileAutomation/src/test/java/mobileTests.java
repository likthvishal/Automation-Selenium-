import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import utils.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class mobileTests {

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        //Set desired capabilities

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"14.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_8");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        caps.setCapability(MobileCapabilityType.APP,"/Users/tfbadmin/Downloads/proverbial_android.apk");

        //Create instance of appium driver

        AppiumDriver<MobileElement> driver = null;
        try{
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),caps);

        }catch(MalformedURLException e)
        {
            e.printStackTrace();
        }

        //Perform actions

        //Click on geolocation
        MobileElement geolocationElement = driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
        geolocationElement.click();
        System.out.println("Geolocation clicked");
        Thread.sleep(1000);

        //validate find is present
        MobileElement findElement = driver.findElementById("com.lambdatest.proverbial:id/find");
        Assert.assertEquals(findElement.isDisplayed(),true,"find button is not displayed");
        System.out.println("Find element");
        Thread.sleep(1000);

        //enter website url
        MobileElement urlTextElement = driver.findElementById("com.lambdatest.proverbial:id/url");
        urlTextElement.sendKeys(JsonReader.getTestData("url"));
        System.out.println("Enter URL");
        Thread.sleep(1000);

        //click find
        findElement.click();
        System.out.println("Find clicked");
        Thread.sleep(1000);

        //validate logo
/*
        MobileElement logo = driver.findElementById("hplogo");
        Assert.assertEquals(logo.isDisplayed(),true, "Logo not displayed");
        System.out.println("Logo present");
        Thread.sleep(10000);
*/

        //Quit driver
        driver.quit();
    }
}
