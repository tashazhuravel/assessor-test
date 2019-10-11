import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class SeleniumConfig {
    public WebDriver webDriver;

    public SeleniumConfig() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        JUnitCore junit = new JUnitCore();
        junit.addListener(new ScreenShotListener((TakesScreenshot) webDriver));
        junit.addListener(new TextListener(System.out));
    }

    static {
       // System.setProperty("webdriver.chrome.driver", findFile("chromedriver.exe"));
    }

    static private String findFile(String filename) {
        String[] paths = {"", "C:/Install/chromedriver"};
        for (String path : paths) {
            if (new File(path + filename).exists())
                return path + filename;
        }
        return "";
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
