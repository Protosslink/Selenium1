package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TravelFormingPage {

    public TravelFormingPage(WebDriver driver) {                                // конструктор класса
        PageFactory.initElements(driver, this);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(title));
    }

    @FindBy(xpath = "//div[@class='product-title-wrapper']/div/h2[text()]")
    public WebElement title;

    @FindBy(xpath = "//div[@class='online-card-program selected']/h3[text()]")   //Получаем список выбранных программ
            List<WebElement> cardProgramSelected;

    @FindBy(xpath = "//button[text()='Оформить']")
    WebElement buttonArrange;

    @FindBy(xpath = "//span[@class='control-label']")
    List<WebElement> controlLabel;

    @FindBy(id = "surname_vzr_ins_0")
    WebElement lastName;

    @FindBy(id = "name_vzr_ins_0")
    WebElement firstName;

    @FindBy(id = "birthDate_vzr_ins_0")
    WebElement birthDate;

    @FindBy(id = "person_lastName")
    WebElement personLastName;

    @FindBy(id = "person_firstName")
    WebElement personFirstName;

    @FindBy(id = "person_middleName")
    WebElement personMiddleName;

    @FindBy(id = "person_birthDate")
    WebElement personBirthDate;

    @FindBy(id = "passportSeries")
    WebElement passportSeries;

    @FindBy(id = "passportNumber")
    WebElement passportNumber;

    @FindBy(id = "documentDate")
    WebElement passportDate;

    @FindBy(id = "documentIssue")
    WebElement documentIssue;

    public void fildField(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия":
                fieldField(lastName, value);
                break;
            case "Имя":
                fieldField(firstName, value);
                break;
            case "Дата рождения":
                fieldField(birthDate, value);

        }
    }

    public void fieldField(WebElement element, String valueString) {
        element.click();                        //Клип по полю
        element.clear();                        //Очистка поля
        element.sendKeys(valueString);          // Заполнение поля типом String

    }


    public void clickButtonArrange() {
        buttonArrange.click();
    }


    public void checkCardProgramSelected(String program) {
        for (WebElement programSelected : cardProgramSelected) {
            if (programSelected.getText().equalsIgnoreCase(program)) {
                System.out.println("Выбрана нужная программа страхования");
                return;
            }
        }
        Assert.fail("Подменю с названием " + program + " не выбрана");
    }


}
