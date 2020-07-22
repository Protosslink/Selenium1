package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TravelFormingPage {

    public TravelFormingPage(WebDriver driver) {                                // конструктор класса
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='online-card-program selected']/h3[text()]")   //Получаем список выбранных программ
    List<WebElement> cardProgramSelected;

    public void checkCardProgramSelected(String program){
        for (WebElement programSelected:cardProgramSelected) {
            if(programSelected.getText().equalsIgnoreCase(program)){
                System.out.println("Выбрана нужная программа страхования");
                return;
            }
        }
        Assert.fail("Подменю с названием " + program + " не выбрана");
    }


}
