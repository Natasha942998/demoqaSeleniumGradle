import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class BaseTest {
    public static WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    public static void gettingInformationAboutBooks() {
        List<WebElement> tableRowsIncludingHeader = driver.findElements(By.className("rt-tr"));
        List<WebElement> filteredtableRows = tableRowsIncludingHeader.stream()
                .filter(row -> !row.getAttribute("class").contains("-radRow"))
                .toList();

        final String infoDelimimiter = " - ";

        String headerValues = filteredtableRows.get(0)
                .findElements(By.className("rt- resizable-header-content"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(infoDelimimiter));

        System.out.println(headerValues);

        filteredtableRows.stream().skip(1)
                .map(tableRows -> {
                    String imgSrc = tableRows.findElement(By.tagName("img")).getAttribute("src");

                    WebElement bookCell = tableRows.findElement(By.tagName("a"));
                    String bookLink = bookCell.getAttribute("href");
                    String bookTitle = bookCell.getText();

                    final int authorCellIndex = 2;
                    String bookAuthor = tableRows.findElements(By.className("rt-td")).get(authorCellIndex).getText();

                    final int publisherCellIndex = 3;
                    String publisherCellIndexXpath = String.format(".//div[%d]", publisherCellIndex + 1);
                    String bookPublisher = tableRows.findElement(By.xpath(publisherCellIndexXpath)).getText();

                    String tableRowInfo = String.join(infoDelimimiter, imgSrc, "(" + bookLink, bookTitle + ")", bookAuthor, bookPublisher);
                    return tableRowInfo;
                })
                .forEach((System.out::println));
    }
}

