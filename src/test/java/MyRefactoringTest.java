import org.junit.Test;
import pages.MainPage;
import pages.TravelFormingPage;
import pages.TravelPage;

public class MyRefactoringTest extends BaseTest{

    @Test
    public void newInsuranceTest(){

        MainPage mainPage = new MainPage(driver);
        mainPage.selectMainMenu("Страхование");
        mainPage.selectSubMenu("Страхование путешественников");

        TravelPage travelPage = new TravelPage(driver);
        travelPage.setSendButton("Оформить онлайн");

        TravelFormingPage travelFormingPage = new TravelFormingPage(driver);
        travelFormingPage.checkCardProgramSelected("Минимальная");
        travelFormingPage.clickButtonArrange();
        travelFormingPage.fildField("Электронная почта","123");

    }
}
