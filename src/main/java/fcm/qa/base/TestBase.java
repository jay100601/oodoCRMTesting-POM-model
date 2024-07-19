package fcm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import fcm.qa.util.Util;


public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
   

    public TestBase() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("C:\\QA\\Selanium_Workspace\\FreeCRMAutomationTest\\src\\main\\java\\fcm\\qa\\config\\config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openBrowser() {
        String browserName = prop.getProperty("browser");

        if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("Chrome")) {
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

      
       
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.page_load));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.implicit_wait));

      
        driver.get(prop.getProperty("url"));
        
    }

  
    
}
