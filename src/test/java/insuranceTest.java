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







    }


    @After
    public void afterTest() {
        driver.quit();                                                              // закрываем браузер
    }

}
