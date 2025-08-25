package framework.pages;

import framework.base.BasePage;
import framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmployeeListPage extends BasePage {
    private WaitUtils wait;

    @FindBy(id = "empsearch_id")
    private WebElement searchEmpId;

    @FindBy(id = "searchBtn")
    private WebElement searchBtn;

    @FindBy(xpath = "//table[@id='resultTable']/tbody/tr")
    private List<WebElement> searchResults;

    @FindBy(id = "btnDelete")
    private WebElement deleteBtn;

    @FindBy(id = "dialogDeleteBtn")
    private WebElement confirmDeleteBtn;

    @FindBy(xpath = "//td[text()='No Records Found']")
    private WebElement noRecords;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
        wait = new WaitUtils(driver);
    }

    public void searchById(String empId) {
        wait.waitForVisibility(searchEmpId).clear();
        searchEmpId.sendKeys(empId);
        searchBtn.click();
    }

    public boolean isEmployeeFound() {
        return searchResults.size() > 0;
    }

    public void deleteEmployee() {
        if (isEmployeeFound()) {
            searchResults.get(0).findElement(org.openqa.selenium.By.name("chkSelectRow[]")).click();
            deleteBtn.click();
            confirmDeleteBtn.click();
        }
    }

    public boolean isNoRecordsFound() {
        return wait.waitForVisibility(noRecords).isDisplayed();
    }
}

