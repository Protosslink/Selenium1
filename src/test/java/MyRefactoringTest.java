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
        travelFormingPage.clickButton(buttonArrange);

        travelFormingPage.fieldField("Фамилия","Барабанов");
        travelFormingPage.fieldField("Имя","Дмитрий");
        travelFormingPage.fieldField("Дата рождения","13.03.1989");
        travelFormingPage.fieldField("Страхователь фамилия","Барабанов");
        travelFormingPage.fieldField("Страхователь имя","Дмитрий");
        travelFormingPage.fieldField("Страхователь отчество","Александрович");
        travelFormingPage.fieldField("Страхователь дата рождения","13.03.1989");
        travelFormingPage.fieldField("Серия паспорта","1111");
        travelFormingPage.fieldField("Номер паспотра","111111");
        travelFormingPage.fieldField("Дата выдачи","07.04.2009");
        travelFormingPage.fieldField("Кем выдан","ФМС");

        travelFormingPage.clickButtonArrange();

        travelFormingPage.checkInvalidMessageOnField("name_vzr_ins_0");
        travelFormingPage.checkInvalidMessageOnField("passportSeries");
        travelFormingPage.checkInvalidMessageOnField("passportNumber");
        travelFormingPage.checkInvalidMessageOnField("passportDate");
        travelFormingPage.checkInvalidMessageOnField("phone");
        travelFormingPage.checkInvalidMessageOnField("email");
        travelFormingPage.checkInvalidMessageOnField("repeatEmail");

        travelFormingPage.checkAlertFormError();



    }
}
