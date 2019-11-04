import dataBase.AssesorService;
import dataBase.DataBaseConnection;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import pages.AssessorSite;
import pages.AuthorizationPage;
import pages.mainPageTab.PlanningTabPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public abstract class BaseWebDriverTest {
    protected static SeleniumAssessor seleniumAssessor;
    protected static AssessorSite assessorSite;
    protected PlanningTabPage planningTabPage;
    protected static AuthorizationPage authorizationPage;
    protected AssesorService assesorService;
    protected String login;
    protected String password;
    protected static WebDriver driver;
    protected boolean acceptNextAlert = true;
    protected String fioUserAccount;
    protected String unllocatedQuestionsStatusField;
    protected String sittingPlace;
    protected static Logger log = Logger.getLogger(BaseWebDriverTest.class.getName());
    protected static Wait wait;


    @Parameters
    public static Collection authorizationData() {
        return Arrays.asList(
                new Object[][]{
                        {"krug", "krug", "Секретарева И.О.", "Тестовая комиссия. Нераспределённые вопросы", "переговорная 1"}
                }
        );
    }

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule();

    @Rule
    public DataBaseConnection dataBaseConnection = new DataBaseConnection();

    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            log.error(description, e);
        }

        @Override
        protected void succeeded(Description description) {
            log.info(description);
        }
    };

    @BeforeClass
    public static void initWebDriver() throws Exception {
        seleniumAssessor = new SeleniumAssessor();
        driver = seleniumAssessor.getWebDriver();
        driver.get(seleniumAssessor.getUrl());
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assessorSite = PageFactory.initElements(driver, AssessorSite.class);
        wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.of(10, SECONDS))
                .pollingEvery(Duration.of(2, SECONDS))
                .ignoring(NoSuchElementException.class);
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
            log.error("Ошибка сохранения скриншота", e);
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
            String a = optionsNames.get(i);
            String b = webElements.get(i).getText();
            assertEquals("Wrong options text", optionsNames.get(i), webElements.get(i).getText());
        }
    }

    protected void verifyAutocompleteOptionsText(List<String> word, List<String> optionsNames) {
        assertEquals("Options", optionsNames.size(), word.size());
        for (int i = 0; i < optionsNames.size(); i++) {
            String a = optionsNames.get(i);
            String b = word.get(i);
            assertEquals("Wrong options text", optionsNames.get(i), word.get(i));
        }
    }

    public List<String> changeWordpressSymbol(List<WebElement> elements) {
        List<String> newParticipantFIO = elements.stream().map(element -> element.getText().trim().replace((char) 32, (char) 160)).collect(Collectors.toList());
        return newParticipantFIO;
    }

    public boolean isElementVisible(WebElement webElement) {

        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    public boolean isElementFind(By my_element) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(my_element));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
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
