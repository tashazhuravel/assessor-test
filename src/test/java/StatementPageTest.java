import dataBase.AssessorService;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.ProtocolPage;
import pages.StatementPage;
import pages.sittingPage.CurrentMeettingPage;
import pages.window.WindowCreateStatement;
import pages.window.WindowUploadFile;

import java.util.ArrayList;
import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StatementPageTest extends BaseWebDriverTest {
    public StatementPageTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.sittingPlace = sittingPlace;
    }

    @Test
    public void authorization() {
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    //   @Ignore
    public void createStatementFromProtocol() {
        log.info("Создание Выписки из Протокола");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));
        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        assertEquals("Открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());

        log.info("Выбор вопросов для создания Выписки из протокола");
        WindowCreateStatement windowCreateStatement = protocolPage.clickCreateStatementButton();
        assertEquals("Открыто не то окно", "Создание выписки:", windowCreateStatement.getHeaderChooseQuestions());
        windowCreateStatement.clickCheckboxSelectQuestion();

        isCheckboxSelected(windowCreateStatement.getCheckboxSelectQuestion().iterator().next());
        // String subjectQuestion = windowCreateStatement.selectedQuestion();//TODO подумать как забрать текст выбранного вопроса
        StatementPage statementPage = windowCreateStatement.clickCreateButton();
        sleepAnyTime(5000L);
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        log.info(Tabs);
        driver.switchTo().window(Tabs.get(1));
        log.info(statementPage.getHeaderStatement());

        log.info("ПРоверка скачать данный текст и поместить измененный");
        statementPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);
        String idSitting = assessorService.getIDSittingForExtract().get(0);
        downloadFile(String.format("Extract_%s_1.docx", idSitting));
        WindowUploadFile windowUploadFile = statementPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile(PATH_UPLOAD_FILE);
        windowUploadFile.clickUploadFileButton();
        sleepAnyTime(5000L); //долгая загрузка файла и перезагрузка страницы


    }


    @Test
    @Ignore
    public void downloadStatementText() {
        log.info("Создание Выписки из Протокола");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));
        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        assertEquals("Открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());

        log.info("Выбор вопросов для создания Выписки из протокола");
        WindowCreateStatement windowCreateStatement = protocolPage.clickCreateStatementButton();
        assertEquals("Открыто не то окно", "Создание выписки:", windowCreateStatement.getHeaderChooseQuestions());
        windowCreateStatement.clickCheckboxSelectQuestion();

        isCheckboxSelected(windowCreateStatement.getCheckboxSelectQuestion().iterator().next());
        StatementPage statementPage = windowCreateStatement.clickCreateButton();
        sleepAnyTime(5000L);
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        log.info(Tabs);
        driver.switchTo().window(Tabs.get(1));
        log.info(statementPage.getHeaderStatement());

        log.info("ПРоверка скачать данный текст и поместить измененный");
        statementPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);
        String idSitting = assessorService.getIDSittingForExtract().get(0);
        downloadFile(String.format("Extract_%s_1.docx", idSitting));
    }

    @Test
    public void uploadChangeText(){
        log.info("Создание Выписки из Протокола");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));
        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        assertEquals("Открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());

        log.info("Выбор вопросов для создания Выписки из протокола");
        WindowCreateStatement windowCreateStatement = protocolPage.clickCreateStatementButton();
        assertEquals("Открыто не то окно", "Создание выписки:", windowCreateStatement.getHeaderChooseQuestions());
        windowCreateStatement.clickCheckboxSelectQuestion();

        isCheckboxSelected(windowCreateStatement.getCheckboxSelectQuestion().iterator().next());
        // String subjectQuestion = windowCreateStatement.selectedQuestion();//TODO подумать как забрать текст выбранного вопроса
        StatementPage statementPage = windowCreateStatement.clickCreateButton();
        sleepAnyTime(5000L);
        ArrayList<String> Tabs = new ArrayList<String>(driver.getWindowHandles());
        log.info(Tabs);
        driver.switchTo().window(Tabs.get(1));
        log.info(statementPage.getHeaderStatement());

        log.info("ПРоверка скачать данный текст и поместить измененный");
        statementPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);
        String idSitting = assessorService.getIDSittingForExtract().get(0);
        downloadFile(String.format("Extract_%s_1.docx", idSitting));
        WindowUploadFile windowUploadFile = statementPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile(PATH_UPLOAD_FILE);
        windowUploadFile.clickUploadFileButton();
        sleepAnyTime(5000L); //долгая загрузка файла и перезагрузка страницы
        //TODO дописать


    }

}

