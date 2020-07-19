import org.junit.After;
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
    String passportDateOfIssue = "01.01.2009":
    int passportSeries = 1111;
    int passportNumber = 111111;


    //Метод заполнения строк
    public void fildField(By locator, String value) {
        driver.findElement(locator).clear();                        //Очистка поля
        driver.findElement(locator).sendKeys(value);                // Заполнение поля типом String
    }

    //Метод проверки строк
    public void checkString(String getString, String comparison) {
        System.out.println("Ожидаем, что строка " + getString + " равна строке " + comparison);
        if (getString.equals(comparison)) {
            System.out.println("Строки равны");
        } else {
            System.out.println("Строки не равны");
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

        driver.findElement(By.xpath("//B[@class='kit-button__text'][text()='Оформить онлайн']")).click();                  //Клик по кнопке "Оформить онлайн"

        driver.findElement(By.xpath("//div[@class=\"online-card-program selected\"]/h3[contains(text(), 'Минимальная')]")); //Проверка выбора минимального полис

        driver.findElement(By.xpath("//*[contains(text(), 'Оформить')]")).click();                                          //Клик по кнопке "Оформить"

        driver.findElement(By.xpath("//div[@class='col-4 step-element active']/a[text()='Оформление']"));                   //Проверка активной вкладки "Оформление"

        fildField(By.id("surname_vzr_ins_0"), lastName);                                                                   //Заполнение поля Фамилия раздел Застрахованные
        fildField(By.id("name_vzr_ins_0"), firstName);                                                                      //Заполнение поля Имя раздел Застрахованные
        fildField(By.id("birthDate_vzr_ins_0"), birthDate);                                                                 //Заполнение поля Дата рождения Застрахованные
        fildField(By.id("person_lastName"), lastName);                                                                     //Заполнение поля Фамилия раздел Страхователь
        fildField(By.id("person_firstName"), firstName);                                                                    //Заполнение поля Имя раздел Страхователь
        fildField(By.id("person_birthDate"), birthDate);                                                                     //Заполнение поля Дата рождения Страхователь

        driver.findElement(By.xpath("//label[@for=\"checkbox-person_isEmptyMiddleName\"]/span[@class=\"checkbox\"]")).click(); //Активация чек бокса "Отчество отсутствует" раздел Страхователь

        driver.findElement(By.xpath("//div[@class=\"btn-group\"]/label[contains(text(),\"Мужской\")]")).click();            //Клик по кнопке "Мужской"



        //driver.findElement(By.xpath("//div[@class='col-4 step-element active']/a[text()='Оформление123']"));


    }


    @After
    public void afterTest() {
        driver.quit();                                                              // закрываем браузер
    }

}
