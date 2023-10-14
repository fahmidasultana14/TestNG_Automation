package pages;

import config.EmployeeModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class DashboardPage
{
    @FindBy(className = "oxd-main-menu-item--name")
    List<WebElement> menuItems;

@FindBy(className = "oxd-button")
   List<WebElement> buttons;
@FindBy(className = "oxd-input")
List<WebElement> formTextFields;

@FindBy(className = "oxd-switch-input--active")
WebElement btnswitch;


@FindBy(xpath = "//input[@placeholder='Type for hints...']")
WebElement search;

WebDriver driver;

public DashboardPage(WebDriver driver){
    PageFactory.initElements(driver,this);

}

public void createUser(EmployeeModel model) throws InterruptedException {

    menuItems.get(1).click();//click PIM
    buttons.get(2).click();//click Add
    formTextFields.get(1).sendKeys(model.getFirstname());
    formTextFields.get(3).sendKeys(model.getLastname());
    //Thread.sleep(2000);
    formTextFields.get(4).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);

    formTextFields.get(4).sendKeys(model.getEmployeeid());   //employeeId

    btnswitch.click();//toggle switch
    formTextFields.get(5).sendKeys(model.getUsername());
    formTextFields.get(6).sendKeys(model.getPassword());
    formTextFields.get(7).sendKeys(model.getPassword());//confirm password
    buttons.get(1).click();



}

    public void createUserWithInvalidUsername(EmployeeModel model) throws InterruptedException {

        menuItems.get(1).click();//click PIM
        buttons.get(2).click();//click Add
        formTextFields.get(1).sendKeys(model.getFirstname());
        formTextFields.get(3).sendKeys(model.getLastname());
        Thread.sleep(2000);
        btnswitch.click();//toggle switch
        formTextFields.get(5).sendKeys(model.getUsername());
        formTextFields.get(6).sendKeys(model.getPassword());
        formTextFields.get(7).sendKeys(model.getPassword());//confirm password
        buttons.get(1).click();



    }

    public void createUserWithoutUsername(EmployeeModel model) throws InterruptedException {

        menuItems.get(1).click();//click PIM
        buttons.get(2).click();//click Add
        formTextFields.get(1).sendKeys(model.getFirstname());
        formTextFields.get(3).sendKeys(model.getLastname());
        Thread.sleep(2000);
        btnswitch.click();//toggle switch
        //formTextFields.get(5).sendKeys(model.getUsername());
        formTextFields.get(6).sendKeys(model.getPassword());
        formTextFields.get(7).sendKeys(model.getPassword());//confirm password
        buttons.get(1).click();



    }

    public void searchByEmployeeId(String employeeId){
        menuItems.get(1).click();//click PIM


        formTextFields.get(1).sendKeys(employeeId);  // employeeId
        buttons.get(1).click(); //click search

}
    public void searchByValidEmployeeName(String firstname) throws InterruptedException {
        menuItems.get(8).click();//click directory
        Actions action = new Actions(driver);
        action.click(search);
        action.sendKeys(firstname).perform();
        //Thread.sleep(5000);
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ENTER).perform();
        buttons.get(1).click();
    }




}


