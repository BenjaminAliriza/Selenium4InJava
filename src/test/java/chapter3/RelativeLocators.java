package chapter3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
import org.testng.annotations.*;

import java.util.List;

public class RelativeLocators {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testRelativeLocator(){
        WebElement loginPanel = driver.findElement(By.id("logInPanelHeading"));
        WebElement credentials = driver.findElement(RelativeLocator.with(By.tagName("span")).above(loginPanel));
        System.out.println(credentials.getText());
    }

    @Test
    public void testListOfElements(){
        List<WebElement> allSocialMedia = driver.findElements(with(
                By.tagName("img"))
                .near(By.id("footer")));
        for (WebElement socialMedia : allSocialMedia){
            System.out.println(socialMedia.getAttribute("alt"));
        }
    }
}
