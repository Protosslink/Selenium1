package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TravelPage {

    public TravelPage(WebDriver driver) {                                // конструктор класса
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//b[@class='kit-button__text']")
    List<WebElement> sendButton;

    public void setSendButton(String nameButton){                                       // Выбор кнопки
        for (WebElement buttonName:sendButton) {
            if(buttonName.getText().equalsIgnoreCase(nameButton)){
                buttonName.click();
                return;
            }

        }
        Assert.fail("Подменю с названием " + nameButton + " не найдена");
    }

}
