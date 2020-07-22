import org.junit.*;
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

public class InsuranceTest extends BaseTest {

    //Переменные
    String firstName = "Дмитрий";
    String lastName = "Барабанов";
    String birthDate = "13.03.1989";
    String passportDateOfIssue = "07.04.2009";
    String passportSeries = "1111";
    String passportNumber = "111111";
    String passportIssue = "Россия";

    @Test
    @Ignore
    public void testInsurance() {
        BaseTest.driver.get(BaseTest.baseUrl);
        WebElement link = driver.findElement(By.xpath("(//A[@href='/ru/person/bank_inshure/insuranceprogram/life/travel'][text()='Страхование путешественников'][text()='Страхование путешественников'])[1]"));
        Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);


        driver.findElement(By.xpath("//span[contains(text(), 'Страхование')]")).click();                                  //Выбор меню "Страхование"
        driver.findElement(By.xpath("//span[contains(text(), 'Страхование')]")).click();                                  //Выбор меню "Страхование"

        wait.until(ExpectedConditions.visibilityOf(link)).click();                                                        //Ожидние появления пунтка "Страхование путешественников" в меню "Страхование" и кликаем по нему

        driver.findElement(By.xpath("//B[@class='kit-button__text' and text()='Оформить онлайн']")).click();                  //Клик по кнопке "Оформить онлайн"

        driver.findElement(By.xpath("//div[@class=\"online-card-program selected\"]/h3[contains(text(), 'Минимальная')]")); //Проверка выбора минимального полис

        driver.findElement(By.xpath("//*[contains(text(), 'Оформить')]")).click();                                          //Клик по кнопке "Оформить"

        driver.findElement(By.xpath("//div[@class='col-4 step-element active']/a[text()='Оформление']"));                   //Проверка активной вкладки "Оформление"

        Assert.assertEquals("Ошибка сравнения заголовка вкладки", "Страхование путешественников", driver.getTitle());

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
        Assert.assertEquals("Ошибка ввода поля Фамилия",lastName, driver.findElement(By.id("surname_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Имя",firstName, driver.findElement(By.id("name_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Дата рождения",birthDate, driver.findElement(By.id("birthDate_vzr_ins_0")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Дата рождения",birthDate, driver.findElement(By.id("person_birthDate")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Дата выдачи",passportDateOfIssue, driver.findElement(By.id("documentDate")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Серия паспорта",passportSeries, driver.findElement(By.id("passportSeries")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Номер паспарта",passportNumber, driver.findElement(By.id("passportNumber")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Фамилия",lastName, driver.findElement(By.id("person_lastName")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Имя",firstName, driver.findElement(By.id("person_firstName")).getAttribute("value"));
        Assert.assertEquals("Ошибка ввода поля Кем выдан",passportIssue, driver.findElement(By.id("documentIssue")).getAttribute("value"));


        driver.findElement(By.xpath("//label[@for=\"checkbox-person_isEmptyMiddleName\"]/span[@class=\"checkbox\"]")).click(); //Активация чек бокса "Отчество отсутствует" раздел "Страхователь"

        driver.findElement(By.xpath("//div[@class=\"btn-group\"]/label[contains(text(),\"Мужской\")]")).click();            //Клик по кнопке "Мужской"

        driver.findElement(By.xpath("//*[contains(text(), 'Продолжить')]")).click();                                        //Клик по кнопке "продолжить"

        Assert.assertEquals("Ошибка При заполнении данных произошла ошибка не появилась "
                , "При заполнении данных произошла ошибка", driver.findElement(By.xpath("//div[@class=\"alert-form alert-form-error\"]")).getText());

        Assert.assertEquals("Ошибка Поле не заполнено. не появилась ", "Поле не заполнено."
                , driver.findElement(By.xpath("//input[@id='phone']/..//span[@class='invalid-validate form-control__message']")).getText());

        Assert.assertEquals("Ошибка Поле не заполнено. не появилась ", "Поле не заполнено."
                , driver.findElement(By.xpath("//input[@id='email']/..//span[@class='invalid-validate form-control__message']")).getText());

        Assert.assertEquals("Ошибка Поле не заполнено. не появилась ", "Поле не заполнено."
                , driver.findElement(By.xpath("//input[@id='repeatEmail']/..//span[@class='invalid-validate form-control__message']")).getText());


    }
}


