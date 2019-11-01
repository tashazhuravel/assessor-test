package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.mainPageTab.PlanningTabPage;

public class CurrentMeetingPage {

    @FindBy(xpath = "//textarea[@id='planningCommittee']")
    private WebElement informationFieldAboutSitting;

    @FindBy(xpath = "//div[@id='planningSittingState']//div[text()]")
    private WebElement statusField;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingAttributesEdit']")
    private WebElement editRequisites;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingDelete']")
    private WebElement cancelSittingButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionAdd']")
    private WebElement createNewQuestion;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionMove']")
    private WebElement addUnllocatedQuestion;

    @FindBy(xpath = "//button[@class=' x-btn-text btnAgendaOpen']")
    private WebElement agendaButton;


    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingBack']")
    private WebElement backOnListSitting;

    private WebDriver webDriver;
    private Actions actions;

    public CurrentMeetingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getTextStatusField() {
        return informationFieldAboutSitting.getAttribute("value");
    }

  /* TOdo распарсить строку статус
  public String getPartOfTextStatusField(String nameCommittee) {
        List<String> arrSplit = new ArrayList<String>(Arrays.asList(nameCommittee.split("№ ",1)));
        for (int i = 0; i < arrSplit.size(); i++) {
            System.out.println(arrSplit.get(i));
        }
        return arrSplit.get(0);
    }*/

  public PlanningTabPage clickBackOnListSitting(){
      actions.moveToElement(backOnListSitting).click().perform();
      return new PlanningTabPage(webDriver);
  }

}

