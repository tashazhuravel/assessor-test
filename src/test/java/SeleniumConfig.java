import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class SeleniumConfig {
    private WebDriver webDriver;
    private DriverType driverType = DriverType.CHROME;
    private String CHROME_PATH = "C:/Install/chromedriver/chromedriver.exe";
    private String FIREFOX_PATH = "C:/Install/chromedriver/geckodriver.exe";
    private String IE_PATH = "C:/Install/chromedriver/MicrosoftWebDriver.exe";

    public SeleniumConfig() {
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
               // System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case IE:
                InternetExplorerOptions iEOptions = new InternetExplorerOptions();
                System.setProperty("webdriver.MicrosoftWebDriver.driver", IE_PATH);
                webDriver = new InternetExplorerDriver(iEOptions);
                break;
            case FIREFOX:
                FirefoxOptions fFOptions = new FirefoxOptions();
                System.setProperty("webdriver.geckodriver.driver", FIREFOX_PATH);
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
