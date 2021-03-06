import dataBase.AssessorServiceImp;
import dataBase.DataBaseConnection;
import log.EventHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import pages.AssessorSite;
import pages.AuthorizationPage;
import pages.attentionWindow.AttentionWindow;
import pages.mainPageTab.PlanningTabPage;
import pages.messageWindow.MessageWindow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class BaseWebDriverTest {
    private static SeleniumAssessor seleniumAssessor;
    protected static WebDriver driver;
    private boolean acceptNextAlert = true;
    private static Wait wait;
    PlanningTabPage planningTabPage;
    MessageWindow messageWindow;
    AttentionWindow attentionWindow;
    AssessorServiceImp assessorService;
    String login;
    String password;
    String fioUserAccount;
    String unallocatedQuestionsStatusField;
    String sittingPlace;
    static Properties obj;
    static AuthorizationPage authorizationPage;
    static AssessorSite assessorSite;
    static Logger log = EventHandler.LOG;

    @Parameters
    public static Collection authorizationData() {
        obj = new Properties();
        try {
            FileInputStream objFile = new FileInputStream(System.getProperty("user.dir")+"/src/resources/application.properties");
            obj.load(objFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(
                new Object[][]{
                        {obj.getProperty("login"), obj.getProperty("password"), obj.getProperty("userFIOAccount"), obj.getProperty("infoTabUnlocQuestions"), obj.getProperty("location")}
                }
        );
    }

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule();

    @Rule
    public DataBaseConnection dataBaseConnection = new DataBaseConnection(obj.getProperty("dbUrl"),obj.getProperty("user"),obj.getProperty("pass"));

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
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/resources/log4j.properties");
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
        String path = String.format("build/reports/tests/screenshots/scr_%s_%s.png", name, formattedDate);
        try {
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            log.error("???????????? ???????????????????? ??????????????????", e);
        }
    }

    @AfterClass
    public static void tearDown() {
        seleniumAssessor.closeWindow();
    }

    /**
     * ?????? dropDown ?????????????? ???????????????? ??????????????????
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

    String deleteAllWordPressSymbol(String phrase){ return  phrase.replaceAll(String.valueOf((char)160), "");}

    String deleteSpaceBetweenWords(String phrase) {
        return phrase.replace(" ", "");
    }

    String deleteAllSpaceBetweenWords(String phrase) {
        return phrase.replaceAll(" ", "");
    }

    String deleteSymbolInPhrase(String phrase) {
        return phrase.replace("???", "");
    }

    String deleteSymbolInTextContent(String question) {
        return question.replaceAll("\n", "");
    }

    String deleteSymbolInTextAgenda(String question) {
        return question.replaceAll("\t", " ");
    }

    @SuppressWarnings("unchecked")
    boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
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
    @SuppressWarnings("unchecked")
    boolean isElementHaveTitle(WebElement element) {
        try {
            wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "title"));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    boolean isCheckboxSelected(WebElement my_element) {
        try {
            wait.until(ExpectedConditions.elementToBeSelected(my_element));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    boolean isCheckboxDisabled(WebElement my_element) {
        try {
            wait.until(ExpectedConditions.attributeToBe(my_element, "disabled", "true"));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    boolean isAllCheckboxSelected(List<WebElement> my_element) {
        // WebElement element = my_element.iterator().next();
        try {
            wait.until(ExpectedConditions.attributeToBe(my_element.iterator().next(), "checked", "true"));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    boolean isAllCheckboxDisabled(List<WebElement> my_element) {
        try {
            wait.until(ExpectedConditions.attributeToBe(my_element.iterator().next(), "disabled", "true"));
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

    @SuppressWarnings("unchecked")
    boolean isButtonDisabled(WebElement my_element) {
        try {
            wait.until(ExpectedConditions.attributeToBe(my_element, "disabled", "true"));
        } catch (TimeoutException ex) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    void waitWhileElementPresent(By myElement) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(myElement));
        } catch (TimeoutException exception) {
            log.error(myElement, exception);
        }
    }

    @SuppressWarnings("unchecked")
    void waitWhileElementPresent(WebElement myElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(myElement));
        } catch (TimeoutException exception) {
            log.error(myElement, exception);
        }
    }

    @SuppressWarnings("unchecked")
    void waitToTextChanged(final WebElement webElement) {
        final String currentText = webElement.getText();
        try {
            wait.until((driver) -> !webElement.getText().equals(currentText));
        } catch (TimeoutException exception) {
            log.error(webElement, exception);
        }
    }

    @SuppressWarnings("unchecked")
    boolean isTextChanged(final WebElement element) {
        final String currentText = element.getText();
        try {
            wait.until((driver) -> !element.getText().equals(currentText));
        } catch (TimeoutException exception) {
            log.error(element, exception);
            return false;
        }
        return true;
    }

    boolean isFieldEmpty(WebElement element) {
        try {
            assertEquals(StringUtils.EMPTY, element.getText());
        } catch (Exception exception) {
            log.error(element, exception);
            return false;
        }
        return true;
    }

    //???????????????? ????????????
    void downloadFile(String file) {
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        if (listOfFiles == null) {
            log.info("?????????? ???? ??????????????. ?????????????? ?????? Download ???????????? ????????");
            return;
        }
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    log.info("File:" + " " + listOfFile.getName());
                    if (fileName.equals(file)) {
                        f = new File(fileName);
                        found = true;
                    }
                }
            }
            Assert.assertTrue("Downloaded document is not found", found);
            f.delete();
        }

    //???????????? docx ????????????
    String readDocxFile(String fileName) {
        StringBuilder fileText = new StringBuilder(StringUtils.EMPTY);
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (int i = 0; i < paragraphs.size(); i++) {
                fileText.append(paragraphs.get(i).getParagraphText());
                log.info(paragraphs.get(i).getParagraphText());
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileText.toString();
    }

    void sleepAnyTime(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
