package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {

    @Test(priority = 1,description = "Admit try to login with invalid creds")
    public void doLoginWithWrongCreds(){
        LoginPage loginPage= new LoginPage(driver);

        loginPage.doLogin("Admin","wrongpass");
        String textActual=driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected="Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));


    }

    @Test(priority = 2,description = "Admin login with valid creds",groups = "smoke")
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray jsonArray= Utils.readJsonList("./src/test/resources/employees.json");
        JSONObject empObj=(JSONObject) jsonArray.get(0);
        //if(System.getProperty("usrname")!=null && System.getProperty("password")!=null)

        //{ loginPage.doLogin(System.getProperty("username"),System.getProperty("password "));}
        //else{
        loginPage.doLogin( empObj.get("username").toString(),empObj.get("password").toString());}




    @Test(priority = 3,description = "Admin log out")
    public void logout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual=driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected="Login";
        Assert.assertEquals(textActual,textExpected);

    }
}
