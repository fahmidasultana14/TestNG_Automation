package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name="username")
    WebElement txtUsername;
    @FindBy(name="password")
    WebElement txtPassword;
    @FindBy(tagName = "button")
    WebElement btnlogin;
    @FindBy(className = "oxd-userdropdown-icon")
    WebElement btnprofile;

    @FindBy(className = "oxd-userdropdown-link")
    List<WebElement> linkSubItems;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }

    public void doLogin(String username,String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnlogin.click();

    }

    public void doLogout(){
        btnprofile.click();
        linkSubItems.get(3).click();
    }


}
