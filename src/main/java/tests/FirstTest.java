package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AssessorSite;
import pages.AuthorizationPage;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    WebDriver webDriver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        webDriver = new ChromeDriver(options);
        webDriver.get("http://assessor-demo.isida.by/assessor_nbrb");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Step 1: Authorization");

        AssessorSite assessorSite = new AssessorSite(webDriver);
        AuthorizationPage authorizationPage = assessorSite.getAutorizationPage();
        authorizationPage.setLogin("krug");
        authorizationPage.setPassword("krug");
        authorizationPage.clickLogining();
    }

    @Test
    public void testUrl() {
        Assert.assertTrue(webDriver.getCurrentUrl().equals("http://assessor-demo.isida.by/assessor_nbrb/index.php?title=%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0#tab-planning"));
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }

    }
}
