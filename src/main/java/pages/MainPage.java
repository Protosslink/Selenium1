package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    @FindBy(xpath = "//button[@class='lg-menu__link']")                 //Получаем список элементов по xPath и записываем в переменную mainMenuList
    List<WebElement> mainMenuList;

    @FindBy(xpath = "//a[@class='lg-menu__sub-link' and text()]")       //Получаем список элементов по xPath и записываем в переменную subMenuList
    List<WebElement> subMenuList;

    public MainPage(WebDriver driver) {                                // конструктор класса
        PageFactory.initElements(driver, this);
    }

    // Выбор раздела в главном меню

    public void selectMainMenu(String nameBaseMenu){
        for (WebElement menuItem:mainMenuList) {
            if(menuItem.getText().equalsIgnoreCase(nameBaseMenu)){
                menuItem.click();
                menuItem.click();
                return;
            }
        }Assert.fail("Подменю с названием " + nameBaseMenu + " не найдена");
    }

    public void selectSubMenu(String nameSupMenu){
        for (WebElement menuItem : subMenuList) {
            if (menuItem.getText().equalsIgnoreCase(nameSupMenu)) {
                menuItem.click();
                return;
            }
        }Assert.fail("Подменю с названием " + nameSupMenu + " не найдена");
    }
}
