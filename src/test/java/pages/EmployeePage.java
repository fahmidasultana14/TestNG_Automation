package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {

    @FindBy(className = "oxd-main-menu-item")
    List<WebElement> menuItems;

    @FindBy(className = "oxd-radio-input")
    List<WebElement> genderButton;

    @FindBy(className = "oxd-select-text-input")
    List<WebElement> bloodSelect;

    @FindBy(className = "orangehrm-left-space")
    List<WebElement> submit;




    WebDriver driver;

    public EmployeePage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }


    public void gotoProfile(){
        menuItems.get(2).click();
    }

    public void selectGender(){
        genderButton.get(1).click();
        submit.get(0).click();

    }

    public void selectBloodGroup(){
        bloodSelect.get(2).click();
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ENTER);
        submit.get(1).click();



    }

    public void updateBlood(){
        bloodSelect.get(2).click();
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ARROW_DOWN);
        bloodSelect.get(2).sendKeys(Keys.ENTER);
        submit.get(1).click();

    }



}
