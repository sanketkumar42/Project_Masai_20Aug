//package framework.pages;
//
//import framework.base.BasePage;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.WebElement;
//
//public class DashboardPage extends BasePage {
//    @FindBy(id = "menu_pim_viewPimModule")
//    private WebElement pimMenu;
//
//    public DashboardPage(WebDriver driver) {
//        super(driver);
//    }
//
//    public void goToPIM() {
//        pimMenu.click();
//    }
//}
package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    By pimMenu = By.xpath("//span[text()='PIM']");
    By addEmployeeMenu = By.xpath("//a[contains(@href,'pim/addEmployee')]");
    By employeeListMenu = By.xpath("//a[contains(@href,'pim/viewEmployeeList')]");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void openAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeMenu)).click();
    }

    public void openEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListMenu)).click();
    }
}
