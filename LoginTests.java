package tests;

import framework.base.DriverFactory;
import framework.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class LoginTests {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testAdminLogin() {
        loginPage.loginAsAdmin("Admin", "admin123");
        assert driver.getTitle().contains("OrangeHRM");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
