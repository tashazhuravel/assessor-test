import dataBase.AssessorService;
import dataBase.DataBaseConnection;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.MainPage;
import pages.ProtocolPage;
import pages.attentionWindow.AttentionType;
import pages.messageWindow.MessageType;
import pages.sittingPage.CurrentMeettingPage;
import pages.window.WindowUploadFile;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProtocolPageTest extends BaseWebDriverTest {

    protected static final String STATUS = "Формируется протокол";

    public ProtocolPageTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.sittingPlace = sittingPlace;
    }

    @Test
    //@Ignore
    public void authorization() {
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    @Ignore
    public void openAndCloseProtocol() {
        log.info("Перейти на форму 'Протокол' и вернуться к текущем заседанию");
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
        protocolPage.clickCloseProtocolButton();
        assertEquals("Не осуществлен переход к форме Текущее заседание", "Список вопросов", currentMeettingPage.getHeaderQuestionListText());
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void downloadFile() {
        log.info("Протокол, проверка загрузки файла по кнопке 'Скачать данный текст'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));

        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        assertEquals("ой открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());
        protocolPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);

        downloadFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));

        protocolPage.clickCloseProtocolButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void uploadFile() {
        log.info("Протокол. Проверка помещения файла в систему по кнопке 'Поместить измененный текст'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));

        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        WindowUploadFile windowUploadFile = protocolPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile(PATH_UPLOAD_FILE);
        windowUploadFile.clickUploadFileButton();
        sleepAnyTime(5000L); //долгая загрузка файла и перезагрузка страницы
        protocolPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L); //ждем пока файл скачается
        String textBeforeUploadFile = readDocxFile(PATH_UPLOAD_FILE);
        downloadFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        String textAfterDownloadFile = readDocxFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        assertEquals("Файл не загружен", textBeforeUploadFile, textAfterDownloadFile);

        protocolPage.clickCloseProtocolButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void setStatusProtocolApproval() {
        log.info("Установить статус 'Протокол утвержден'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));
        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        sleepAnyTime(5000L);
        assertEquals("ой открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());

        if (STATUS.equals(currentMeettingPage.getTextStatusField())) {

            protocolPage.clickSetStatusProtocolUnderApprovalButton();
            waitToTextChanged(currentMeettingPage.getStatusField());

            messageWindow = assessorSite.getMessageWindow();
            assertEquals("Статус не установлен", MessageType.MEETING_STATUS_PROTOCOL_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();
            log.info(currentMeettingPage.getTextStatusField());

            protocolPage.clickSetStatusProtocolApprovalButton();
            attentionWindow = assessorSite.getAttentionWindow();
            assertEquals("", AttentionType.SET_MEETING_STATUS_PROTOCOL_APPROVED.getLabel(), attentionWindow.getTextAttention());

            attentionWindow.clickNoAttentionButton();
            assertEquals("Изменен статус заседания", "Протокол проходит согласование", currentMeettingPage.getTextStatusField());

            protocolPage.clickSetStatusProtocolApprovalButton();
            attentionWindow.clickAttentionCloseByXButton();
            assertEquals("Изменен статус заседания", "Протокол проходит согласование", currentMeettingPage.getTextStatusField());

            protocolPage.clickSetStatusProtocolApprovalButton();
            attentionWindow.clickYesAttentionButton();

            waitWhileElementPresent(messageWindow.getTextMessage());
            assertEquals("", MessageType.MEETING_STATUS_PROTOCOL_APPROVED_HAS_BEEN_SET.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();

            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус 'Протокол утвержден' не установлен", "Протокол утвержден", currentMeettingPage.getTextStatusField());
            log.info(currentMeettingPage.getStatusField());
        } else {

            protocolPage.clickSetStatusProtocolApprovalButton();
            attentionWindow = assessorSite.getAttentionWindow();
            assertEquals("", AttentionType.SET_MEETING_STATUS_PROTOCOL_APPROVED.getLabel(), attentionWindow.getTextAttention());

            protocolPage.clickSetStatusProtocolApprovalButton();
            attentionWindow.clickYesAttentionButton();

           // waitWhileElementPresent(messageWindow.getTextMessage());
            assertEquals("", MessageType.MEETING_STATUS_PROTOCOL_APPROVED_HAS_BEEN_SET.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();

            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус 'Протокол утвержден' не установлен", "Протокол утвержден", currentMeettingPage.getTextStatusField());
            log.info(currentMeettingPage.getStatusField());
        }

        protocolPage.clickCloseProtocolButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    //@Ignore
    public void reformatProtocol() {
        log.info("Протокол. Перефорировать текст протокола");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();

        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));
        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        assertEquals("Ой, открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());

        //--Скачиваем текст, который отображен в повестке при открытии формы "Протокол" и читаем его по параграфам.
        protocolPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);
        String textBeforeUpload = readDocxFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        downloadFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));


        //--Читаем документ по параграфам перед помещением в систему.Помещаем новый документ, ждем завершения, ждем пока отобразится новый текст.
        WindowUploadFile windowUploadFile = protocolPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile(PATH_UPLOAD_FILE);
        windowUploadFile.clickUploadFileButton();
        sleepAnyTime(5000L);//ждем помещение файла в систему и перезагрузку страницы

        //--Скачиваем документ с новым текстом протокола, читаем по параграфам. Сравниваем тексты документов: до загрузки и после изменений.
        protocolPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textAfterUpload = readDocxFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        downloadFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));


        assertNotEquals("Тексты совпадают, скорее всего файл не был загружен", textBeforeUpload, textAfterUpload);

        //Нажимаем Переформировать повестку дня, в появившемся алерте жмем Нет и проверяем что текст не изменился
        protocolPage.clickRefreshProtocolButton();
        attentionWindow = assessorSite.getAttentionWindow();
        assertEquals("", AttentionType.REFORMAT_PROTOCOL_TEXT.getLabel(), attentionWindow.getTextAttention());
        attentionWindow.clickNoAttentionButton();
        sleepAnyTime(5000L);//ждем переформирования текста протокола и обновление страницы
        protocolPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textBeforeReformat = readDocxFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        downloadFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        assertEquals("Текст протокола переформирован", textAfterUpload, textBeforeReformat);

        /*Нажимаем Переформировать повестку дня, в появившемся алерте жмем Да, ждем пока переформируется и отобразится текст протокола по умолчанию(загружена в админке для этой орг.ед)
        сравниваем текст до переформирования и после*/

        protocolPage.clickRefreshProtocolButton();
        assertEquals("", AttentionType.REFORMAT_PROTOCOL_TEXT.getLabel(), attentionWindow.getTextAttention());
        attentionWindow.clickYesAttentionButton();
        sleepAnyTime(5000L);//ждем переформирования текста протокола и обновление страницы
        protocolPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textAfterReformat = readDocxFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        downloadFile(String.format("ПРОТОКОЛ %s_%s.docx", deleteSymbolInPhrase(numberCommittee).trim(), dateCommittee));
        sleepAnyTime(5000L);//ждем пока удалит скачанный файл
        assertNotSame("Текст протокола не переформатирован", textBeforeReformat, textAfterReformat);

        protocolPage.clickCloseProtocolButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }


}
