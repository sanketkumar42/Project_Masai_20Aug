package stepDefinitions;

import framework.base.DriverFactory;
import framework.pages.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EmployeeManagementSteps {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddEmployeePage addEmployeePage;
    EmployeeListPage employeeListPage;

    String createdEmpId;
    String createdEmpName;

    @Given("I am logged in as Admin")
    public void i_am_logged_in_as_admin() {
        driver = DriverFactory.initDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        addEmployeePage = new AddEmployeePage(driver);
        employeeListPage = new EmployeeListPage(driver);

        loginPage.loginAsAdmin("Admin", "admin123");
    }

    @Given("I have created a new employee {string} {string}")
    public void i_have_created_a_new_employee(String firstName, String lastName) {
        dashboardPage.goToPIM();
        dashboardPage.openAddEmployee();
        createdEmpId = addEmployeePage.addEmployee(firstName, lastName);
        createdEmpName = firstName + " " + lastName;
        Assert.assertNotNull(createdEmpId, "Employee ID should not be null after creation");
    }

    @When("I search for the employee by ID")
    public void i_search_for_the_employee_by_id() {
        dashboardPage.goToPIM();
        dashboardPage.openEmployeeList();
        employeeListPage.searchById(createdEmpId);
        Assert.assertTrue(employeeListPage.isEmployeeFound(), "Employee should be found in the list");
    }

    @When("I open the employee details page")
    public void i_open_the_employee_details_page() {
        employeeListPage.openEmployeeDetails(createdEmpId);
    }

    @When("I update the middle name to {string}")
    public void i_update_the_middle_name_to(String middleName) {
        addEmployeePage.editMiddleName(middleName);
    }

    @Then("the middle name should be updated to {string}")
    public void the_middle_name_should_be_updated_to(String expectedMiddleName) {
        Assert.assertEquals(addEmployeePage.getMiddleName(), expectedMiddleName);
        DriverFactory.quitDriver();
    }
}
