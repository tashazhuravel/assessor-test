import dataBase.DataBaseConnection;
import dataBase.AssessorServiceImp;
import log.EventHandler;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class BaseWebDriverTest {
    private static SeleniumAssessor seleniumAssessor;
    private static WebDriver driver;
    private boolean acceptNextAlert = true;
    private static Wait wait;
    PlanningTabPage planningTabPage;
    AssessorServiceImp assessorService;
    String login;
    String password;
    String fioUserAccount;
    String unallocatedQuestionsStatusField;
    String sittingPlace;
    static AuthorizationPage authorizationPage;
    static AssessorSite assessorSite;
    static Logger log = EventHandler.LOG;


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
        driver = seleniumAssessor.getEventDriver();
        driver.get(seleniumAssessor.getUrl());
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assessorSite = PageFactory.initElements(driver, AssessorSite.class);
        wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.of(5, SECONDS))
                .pollingEvery(Duration.of(2, SECONDS))
                .ignoring(NoSuchElementException.class);
    }

    //void initAssessorDataBase(){ assessorService = new assessorService(database.stmp);}

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

    void takeScreenshot(String name) {
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

    void verifyAutocompleteOptions(List<WebElement> webElements, List<String> optionsNames) {
        assertEquals("Options", optionsNames.size(), webElements.size());
        for (int i = 0; i < optionsNames.size(); i++) {
            String a = optionsNames.get(i);
            String b = webElements.get(i).getText();
            assertEquals("Wrong options text", optionsNames.get(i), webElements.get(i).getText());
        }
    }

    void verifyAutocompleteOptionsText(List<String> word, List<String> optionsNames) {
        assertEquals("Options", optionsNames.size(), word.size());
        for (int i = 0; i < optionsNames.size(); i++) {
            String a = optionsNames.get(i);
            String b = word.get(i);
            assertEquals("Wrong options text", optionsNames.get(i), word.get(i));
        }
    }

    List<String> changeWordPressSymbol(List<WebElement> elements) {
        return elements.stream().map(element -> element.getText().trim().replace((char) 32, (char) 160)).collect(Collectors.toList());

    }

    String deleteSpaceBetweenWords(String phrase){
        return phrase.replace(" ","");
    }

    String deleteSymbolInPhrase(String phrase){
        return phrase.replace("№","");
    }

    @SuppressWarnings("unchecked")
    boolean isElementVisible(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    boolean isElementFind(By my_element) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(my_element));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    boolean isCheckboxSelected(WebElement my_element) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(my_element));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    boolean isCheckboxDisabled(WebElement my_element) {
        try {
            wait.until(ExpectedConditions.attributeToBe(my_element, "disabled", "true"));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }


    boolean isElementPresent(By my_element) {
        try {
            driver.findElement(my_element);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    void waitWhileElementPresent(By myElement) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(myElement));
        } catch (TimeoutException exception) {
            log.error(myElement, exception);
        }
    }

    void waitWhileElementPresent(WebElement myElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(myElement));

        } catch (TimeoutException exception) {
            log.error(myElement, exception);
        }
    }

    //Загрузка файлов
    void downloadFile( String file) {
        File folder = new File(System.getProperty("user.dir"));
        //List the files on that folder
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles){
            if (listOfFile.isFile()){
                String fileName = listOfFile.getName();
                log.info("File" + listOfFile.getName());
                if (fileName.matches(file)){
                    f= new File(fileName);
                    found = true;
                }
            }
        }
        Assert.assertTrue("Downloaded document is not found",found);
        f.deleteOnExit();
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
