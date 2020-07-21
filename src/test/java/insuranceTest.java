import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class insuranceTest {

    WebDriver driver;
    String baseURL;

    //Переменные
    String firstName = "Дмитрий";
    String lastName = "Барабанов";
    String birthDate = "13.03.1989";
    String passportDateOfIssue = "07.04.2009";
    String passportSeries = "1111";
    String passportNumber = "111111";
    String passportIssue = "Россия";


    //Метод заполнения строк String
    public void fildFieldString(By locator, String valueString) {
        WebElement element = driver.findElement(locator);
        element.click();                        //Клип по полю
        element.clear();                        //Очистка поля
        element.sendKeys(valueString);          // Заполнение поля типом String

    }

    //Метод проверки строк
    public void checkString(String getString, String comparison) {
        System.out.println("Ожидаем, что строка " + getString + " равна строке " + comparison);
        if (getString.equals(comparison)) {
            System.out.println("Фактический результат: строки равны");
        } else {
            System.out.println("Фактический результат: строки не равны");
        }
    }

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        baseURL = "https://www.sberbank.ru/ru/person";                             // задали путь к сайту, который открываем
        driver = new ChromeDriver();                                               // создали экземпляр класса ChromeDriver
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);         // ожидание
        driver.manage().window().maximize();                                       // развернули окно браузера
        driver.get(baseURL);
    }

    @Test
    public void testInsurance() {
        WebElement link = driver.findElement(By.xpath("(//A[@href='/ru/person/bank_inshure/insuranceprogram/life/travel'][text()='Страхование путешественников'][text()='Страхование путешественников'])[1]"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);


        driver.findElement(By.xpath("//span[contains(text(), 'Страхование')]")).click();                                  //Выбор меню "Страхование"
        driver.findElement(By.xpath("//span[contains(text(), 'Страхование')]")).click();                                  //Выбор меню "Страхование"

        wait.until(ExpectedConditions.visibilityOf(link)).click();                                                        //Ожидние появления пунтка "Страхование путешественников" в меню "Страхование" и кликаем по нему

        checkString(driver.getTitle(), "«Сбербанк» - Страхование путешественников");                             // Проверка титла окна "«Сбербанк» - Страхование путешественников"

        driver.findElement(By.xpath("//B[@class='kit-button__text' and text()='Оформить онлайн']")).click();                  //Клик по кнопке "Оформить онлайн"

        driver.findElement(By.xpath("//div[@class=\"online-card-program selected\"]/h3[contains(text(), 'Минимальная')]")); //Проверка выбора минимального полис

        driver.findElement(By.xpath("//*[contains(text(), 'Оформить')]")).click();                                          //Клик по кнопке "Оформить"

        driver.findElement(By.xpath("//div[@class='col-4 step-element active']/a[text()='Оформление']"));                   //Проверка активной вкладки "Оформление"

        checkString(driver.getTitle(), "Страхование путешественников");

        Assert.assertEquals("Страхование путешественников", driver.getTitle());


        //Заполнение формы "Оформление"
        fildFieldString(By.id("documentDate"), passportDateOfIssue);
        fildFieldString(By.id("surname_vzr_ins_0"), lastName);
        fildFieldString(By.id("name_vzr_ins_0"), firstName);
        fildFieldString(By.id("birthDate_vzr_ins_0"), birthDate);
        fildFieldString(By.id("person_lastName"), lastName);
        fildFieldString(By.id("person_firstName"), firstName);
        fildFieldString(By.id("person_birthDate"), birthDate);
        fildFieldString(By.id("passportSeries"), passportSeries);
        fildFieldString(By.id("passportNumber"), passportNumber);
        fildFieldString(By.id("documentIssue"), passportIssue);


        //Проверка соответствия заполнения формы "Оформление"
        Assert.assertEquals(lastName, driver.findElement(By.id("surname_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals(firstName, driver.findElement(By.id("name_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals(birthDate, driver.findElement(By.id("birthDate_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals(birthDate, driver.findElement(By.id("person_birthDate")).getAttribute("value"));
        Assert.assertEquals(passportDateOfIssue, driver.findElement(By.id("documentDate")).getAttribute("value"));
        Assert.assertEquals(passportSeries, driver.findElement(By.id("passportSeries")).getAttribute("value"));
        Assert.assertEquals(passportNumber, driver.findElement(By.id("passportNumber")).getAttribute("value"));
        Assert.assertEquals(lastName, driver.findElement(By.id("person_lastName")).getAttribute("value"));
        Assert.assertEquals(firstName, driver.findElement(By.id("person_firstName")).getAttribute("value"));
        Assert.assertEquals(passportIssue, driver.findElement(By.id("documentIssue")).getAttribute("value"));


        driver.findElement(By.xpath("//label[@for=\"checkbox-person_isEmptyMiddleName\"]/span[@class=\"checkbox\"]")).click(); //Активация чек бокса "Отчество отсутствует" раздел "Страхователь"

        driver.findElement(By.xpath("//div[@class=\"btn-group\"]/label[contains(text(),\"Мужской\")]")).click();            //Клик по кнопке "Мужской"

        driver.findElement(By.xpath("//*[contains(text(), 'Продолжить')]")).click();                                        //Клик по кнопке "продолжить"

        Assert.assertEquals("При заполнении данных произошла ошибка", driver.findElement(By.xpath("//div[@class=\"alert-form alert-form-error\"]")).getText()); //Проверка сообщения "При заполнении данных произошла ошибка"
        Assert.assertEquals("Поле не заполнено.", driver.findElement(By.xpath("//input[@id='phone']/..//span[@class='invalid-validate form-control__message']")).getText());
        Assert.assertEquals("Поле не заполнено.", driver.findElement(By.xpath("//input[@id='email']/..//span[@class='invalid-validate form-control__message']")).getText());
        Assert.assertEquals("Поле не заполнено.", driver.findElement(By.xpath("//input[@id='repeatEmail']/..//span[@class='invalid-validate form-control__message']")).getText());


    }


    @After
    public void afterTest() {
        driver.quit();                                                              // закрываем браузер
    }

}
