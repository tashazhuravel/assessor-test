import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

public class SeleniumConfig {
    public WebDriver webDriver;
    private DriverType driverType = DriverType.CHROME;
    private String CHROME_PATH = "C:/Install/chromedriver/chromedriver.exe";
    private String FIREFOX_PATH = "";
    private String IE_PATH = "";

    public SeleniumConfig() {
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case IE:
                InternetExplorerOptions iEOptions = new InternetExplorerOptions();
                System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                webDriver = new InternetExplorerDriver(iEOptions);
                break;
            case FIREFOX:
                FirefoxOptions fFOptions = new FirefoxOptions();
                System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                webDriver = new FirefoxDriver(fFOptions);
                break;
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
