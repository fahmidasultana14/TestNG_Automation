package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

import static org.testng.Assert.*;

public class DashboardTestRunner extends Setup {
    DashboardPage dashboardPage;
    LoginPage loginPage;

    @BeforeTest(groups = "smoke")
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.doLogin("Admin", "admin123");
    }







    @Test(priority = 1,description = "Admit create user with valid credentials")
    public void createUser() throws InterruptedException, IOException, ParseException {
        dashboardPage = new DashboardPage(driver);
        Faker faker=new Faker();
        String firstname=faker.name().firstName();
        String lastname=faker.name().lastName();
        String username=faker.name().username();
        String employeeId = String.valueOf(faker.random().nextInt(1000,9999));


        String password="n"+"1"+faker.internet().password(7,10,true,true,true);

        EmployeeModel model=new EmployeeModel();
        model.setFirstname(firstname);
        model.setLastname(lastname);
        model.setEmployeeid(employeeId);
        model.setUsername(username);
        model.setPassword(password);

        Thread.sleep(7000);

        dashboardPage.createUser(model);
        String titleExpected=driver.findElement(By.xpath("//*[contains(text(),\"Personal Details\")]")).getText();
        if(titleExpected.contains("Personal Details"))
                Utils.saveEmployeeInfo(model);
    }


    @Test(priority = 2,description = "Admin create user with Invalid Username")
    public void createUserWithInvalidUsername() throws InterruptedException {
        dashboardPage = new DashboardPage(driver);

        Faker faker =new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String employeeId = String.valueOf(faker.random().nextInt(6000,7000));
        String username = "Mila";
        String password = faker.internet().password(7,10,true,true,true);

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUsername(username);
        model.setPassword(password);
        dashboardPage.createUserWithInvalidUsername(model);
        Thread.sleep(2000);
        String actual = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        String excepted = "Should be at least 5 characters";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,excepted);

        softAssert.assertAll();
}

    @Test(priority = 3,description = "Admin create user with without Username")
    public void createUserWithoutUsername() throws InterruptedException {
        dashboardPage = new DashboardPage(driver);

        Faker faker =new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        //String username = "Mila";
        String password = faker.internet().password(7,10,true,true,true);

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        //model.setUsername(username);
        model.setPassword(password);
        dashboardPage.createUserWithoutUsername(model);
        Thread.sleep(2000);
        String actual = driver.findElement(By.className("oxd-input-group__message")).getText();
        String excepted = "Required";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,excepted);

        softAssert.assertAll();
    }
    @Test(priority = 4,description = "Admin search by valid EmployeID",groups = "smoke")
    public void searchByEmployeeId() throws IOException, ParseException, InterruptedException {
        dashboardPage = new DashboardPage(driver);

        LoginPage loginPage = new LoginPage(driver);
        JSONArray empList= Utils.readJsonList("./src/test/resources/employees.json");
        JSONObject empObj =(JSONObject) empList.get(empList.size()-1);
        String employeeId=(String) empObj.get("employeeid");
        dashboardPage.searchByEmployeeId(employeeId);
        Thread.sleep(1000);
        String actualMessage = driver.findElement(By.className("orangehrm-vertical-padding")).getText();
        String exceptedMessage ="Record Found";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage,exceptedMessage);

        //softAssert.assertAll();




    }

    @Test(priority = 5,description = "Admin search by Invalid EmployeID")
    public void searchByInvalidEmployeeId() throws ParseException, InterruptedException {
        dashboardPage = new DashboardPage(driver);


        String employeeId= String.valueOf(111);
        dashboardPage.searchByEmployeeId(employeeId);
        //Thread.sleep(1000);
        String actualMessage = driver.findElement(By.className("orangehrm-vertical-padding")).getText();
        String exceptedMessage = "No Records Found";
        Thread.sleep(500);
        //SoftAssert softAssert = new SoftAssert();
        //softAssert.assertEquals(actualMessage,exceptedMessage);
        Assert.assertEquals(actualMessage,exceptedMessage);




    }

    @Test(priority = 6, description = "Admin  search user by valid employee name")
    public void searchByEmployeeName() throws IOException, ParseException, InterruptedException {
        dashboardPage = new DashboardPage(driver);

        JSONArray empList= Utils.readJsonList("./src/test/resources/employees.json");
        JSONObject empObj =(JSONObject) empList.get(empList.size()-1);
        String firstName = empObj.get("firstName").toString();
        dashboardPage.searchByValidEmployeeName(firstName);
        Thread.sleep(3000);
        String messageActual = driver.findElement(By.className("orangehrm-directory-card-header")).getText();
        Thread.sleep(2000);
        System.out.println(messageActual);
        Assert.assertTrue(messageActual.contains(firstName));

    }
@Test(priority = 7,description = "Admin logout the session",groups = "smoke")
    public void adminLogOut(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual=driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExpected="Login";
        Assert.assertEquals(textActual,textExpected);


    }

}