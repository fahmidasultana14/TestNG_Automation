package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmployeePage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner extends Setup {

    EmployeePage employeePage;
    LoginPage loginPage;

    @Test(priority = 1,description ="Newly created user login successfully and username is shown beside profile",groups = "smoke")
    public void newUserLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONArray empList= Utils.readJsonList("./src/test/resources/employees.json");
        JSONObject empObj =(JSONObject) empList.get(empList.size()-1);
        String firstname=(String) empObj.get("firstname");
        String lastname=(String) empObj.get("lastname");
        String fullname=firstname+" "+lastname;


        String username=(String) empObj.get("username");
        String password=(String) empObj.get("password");
        loginPage.doLogin(username,password);
        String textActual=driver.findElement(By.className("oxd-userdropdown-name")).getText();
        //String textExpected="Login";
        Assert.assertEquals(textActual,fullname);

    }
    @Test(priority = 2,description = "User go to My Info")
        public void goToMyInfo(){
        employeePage=new EmployeePage(driver);
        employeePage.gotoProfile();

    }

    @Test(priority = 3,description = "User select Gender")
    public void userSelectGender() throws InterruptedException {

        employeePage=new EmployeePage(driver);
        Utils.scroll(driver,0,500);
        Thread.sleep(2000);
        employeePage.selectGender();

    }
    @Test(priority = 4,description = "User select blood group to o+")
    public void selectBlood() throws InterruptedException {
        employeePage=new EmployeePage(driver);
        Utils.scroll(driver,0,500);

        Thread.sleep(2000);
        employeePage.selectBloodGroup();


    }
    @Test(priority = 5,description = "User update blood group to AB-",groups = "smoke")
    public void updateBloodGroup() throws InterruptedException {
        employeePage=new EmployeePage(driver);
        employeePage.gotoProfile();
        Utils.scroll(driver,0,500);

        Thread.sleep(8000);
        employeePage.updateBlood();
    }

@Test(priority = 6,description = "User log out",groups ="smoke")
 public  void logoutUser(){
        loginPage=new LoginPage(driver);
        loginPage.doLogout();
 }
}
