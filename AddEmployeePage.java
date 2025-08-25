package framework.pages;

import framework.base.BasePage;
import framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployeePage extends BasePage {
    private WaitUtils wait;

    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "middleName")
    private WebElement middleName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(xpath = "//label[text()='Employee Id']/../following-sibling::div/input")
    private WebElement employeeId;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
        wait = new WaitUtils(driver);
    }

    public String addEmployee(String fName, String lName) {
        wait.waitForVisibility(firstName).sendKeys(fName);
        lastName.sendKeys(lName);
        String empId = employeeId.getAttribute("value"); // Capture before saving
        saveBtn.click();
        return empId;
    }

    public void editMiddleName(String mName) {
        wait.waitForVisibility(middleName).clear();
        middleName.sendKeys(mName);
        saveBtn.click();
    }

    public String getMiddleName() {
        return wait.waitForVisibility(middleName).getAttribute("value");
    }
}
