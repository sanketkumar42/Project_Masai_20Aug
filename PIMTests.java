package tests;

import framework.base.DriverFactory;
import framework.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class PIMTests {
    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddEmployeePage addEmployeePage;
    EmployeeListPage employeeListPage;
    String createdEmpId;
    String createdEmpName;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addEmployeePage = new AddEmployeePage(driver);
        employeeListPage = new EmployeeListPage(driver);
    }

    @Test(priority = 1)
    public void testAdminLogin() {
        loginPage.loginAsAdmin("Admin", "admin123");
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));
    }

    @DataProvider(name = "employeeData")
    public Object[][] employeeData() {
        return new Object[][]{
                {"John", "Doe"},
                {"Alice", "Smith"}
        };
    }

    @Test(priority = 2, dataProvider = "employeeData")
    public void testAddMultipleEmployees(String firstName, String lastName) {
        dashboardPage.goToPIM();
        dashboardPage.openAddEmployee();
        createdEmpId = addEmployeePage.addEmployee(firstName, lastName);
        createdEmpName = firstName + " " + lastName;
        Assert.assertNotNull(createdEmpId);
    }

    @Test(priority = 3)
    public void testSearchAndValidateEmployee() {
        dashboardPage.goToPIM();
        dashboardPage.openEmployeeList();
        employeeListPage.searchById(createdEmpId);
        Assert.assertTrue(employeeListPage.isEmployeeFound(), "Employee should be found in search results");
    }

    @Test(priority = 4)
    public void testEditEmployeeInformation() {
        addEmployeePage.editMiddleName("Michael");
        Assert.assertEquals(addEmployeePage.getMiddleName(), "Michael");
    }

    @Test(priority = 5)
    public void testDeleteEmployeeRecord() {
        dashboardPage.goToPIM();
        dashboardPage.openEmployeeList();
        employeeListPage.searchById(createdEmpId);
        employeeListPage.deleteEmployee();
        employeeListPage.searchById(createdEmpId);
        Assert.assertTrue(employeeListPage.isNoRecordsFound(), "No Records Found should be displayed");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}


