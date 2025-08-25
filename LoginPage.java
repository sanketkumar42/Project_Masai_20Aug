package framework.pages;

import framework.base.BasePage;
import framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    private WaitUtils wait;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WaitUtils(driver);
    }

    public void loginAsAdmin(String user, String pass) {
        wait.waitForVisibility(username).sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }
}

