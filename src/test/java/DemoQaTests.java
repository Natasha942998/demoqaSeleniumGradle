import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DemoQaTests extends BaseTest {
    WebDriver driver;

    @Test
    public void loginUserTest() {
        final String userName = "m505";
        final String password = "MiaJohns1*";

        WebElement userNameInput = driver.findElement(By.id("userName"));
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login']"));
        loginButton.click();

        WebElement userNameValue = driver.findElement(By.id("userName-value"));
        Assert.assertEquals(userNameValue.getText(), userName, "Некорректное значение userName обнаружено на странице");

        List<WebElement> possibleLogOutButtons = driver.findElements(By.xpath("//*[text()='Log out']"));
        Assert.assertFalse(possibleLogOutButtons.isEmpty(), "Кнопки 'Log out' не представлено на экране");
    }

    @Test
    public void loginIncorrectUserNameTest() {
        final String incorrectUserName = "test";
        final String password = "MiaJohns1*";

               WebElement userNameInput = driver.findElement(By.id("userName"));
        userNameInput.sendKeys(incorrectUserName);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login']"));
        loginButton.click();

        WebElement errorMsg = driver.findElement(By.xpath("//*[text()='Invalid username or password!']"));
        Assert.assertEquals(errorMsg.getText(), "Invalid username or password!");
    }

    @Test
    public void loginIncorrectPasswordTest() {
        final String userName = "m505";
        final String incorrectPassword = "test";

        WebElement userNameInput = driver.findElement(By.id("userName"));
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys(incorrectPassword);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login']"));
        loginButton.click();

        WebElement errorMsg = driver.findElement(By.xpath("//*[text()='Invalid username or password!']"));
        Assert.assertEquals(errorMsg.getText(), "Invalid username or password!");

    }

    @Test
    public void createNewUserTest() {
        final String firstName = "Mia";
        final String lastName = "Johns";
        final String userName = "m505";
        final String password = "MiaJohns1*";

        driver.get("https://demoqa.com/register");

        WebElement registrationHeader = driver.findElement(By.tagName("h4"));
        Assert.assertEquals(registrationHeader.getText(), "Register to Book Store", "Couldn't acces register page");
    }

    @Test
    public void informationAboutBooksTest() {
        final String userName = "m505";
        final String password = "MiaJohns1*";

        WebElement userNameInput = driver.findElement(By.id("userName"));
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login']"));
        loginButton.click();

        WebElement goToBookStoreButton = driver.findElement(By.cssSelector("#gotoStore"));
        goToBookStoreButton.click();

        gettingInformationAboutBooks();

    }
}
