import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.AssessorSite;
import pages.AuthorizationPage;
import pages.mainPageTab.PlanningTabPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public abstract class BaseWebDriverTest {
    protected static SeleniumAssessor seleniumAssessor;
    protected static AssessorSite assessorSite;
    protected PlanningTabPage planningTabPage;
    protected static AuthorizationPage authorizationPage;
    protected final static String LOGIN = "krug";
    protected final static String PASSWORD = "krug";
    protected static WebDriver driver;
    protected boolean acceptNextAlert = true;
    protected final static String FIO_USER_ACCOUNT = "Секретарева И.О.";
    protected final static String UNLLOCATED_QUESTIONS = "Тестовая комиссия. Нераспределённые вопросы";

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule();

    @BeforeClass
    public static void authorization() throws Exception {
        seleniumAssessor = new SeleniumAssessor();
        driver = seleniumAssessor.getWebDriver();
        driver.get(seleniumAssessor.getUrl());
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assessorSite = PageFactory.initElements(driver, AssessorSite.class);
        authorizationPage = assessorSite.getAuthorizationPage();
        System.out.println("Step 1: Authorization");
        authorizationPage.setLogin(LOGIN);
        authorizationPage.setPassword(PASSWORD);
        authorizationPage.clickLoginButton();
    }

    class ScreenshotRule implements MethodRule {
        public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        statement.evaluate();
                    } catch (Throwable t) {
                        takeScreenshot(frameworkMethod.getName() + "-failure");
                        throw t;
                    }
                }
            };
        }
    }

    protected void takeScreenshot(String name) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("dd_MM_yyyy").format(date);
       // String path = String.format("/work/screen/scr_%s_%s.png", name, formattedDate);
        String path = String.format("C:/Задание/AutoTestScreenshots/scr_%s_%s.png", name, formattedDate);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        seleniumAssessor.closeWindow();
    }

    /**
     * Для dropDown списков проверки элементов
     **/
    protected void verifySelectOptions(Select select, String... optionsNames) {
        List<WebElement> options = select.getOptions();
        assertEquals("Options", optionsNames.length, options.size());
        for (int i = 0; i < optionsNames.length; i++) {
            assertEquals("Wrong options text", optionsNames[i], options.get(i).getText().trim());
        }
    }

    protected void verifyAutocompleteOptions(List<WebElement> webElements, List<String> optionsNames) {
        assertEquals("Options", optionsNames.size(), webElements.size());
        for (int i = 0; i < optionsNames.size(); i++) {
            assertEquals("Wrong options text", optionsNames.get(i), webElements.get(i).getText());
        }
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }

    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;

        } finally {
            acceptNextAlert = true;
        }
    }
}
