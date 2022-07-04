package chapter4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class WindowManager {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
    }

    @Test
    public void testWorkingInBothWindowTabs(){
//        Automatically open & Switch to new window/tab
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());

//        Work in the new window/tab
        driver.findElement(By.id("email_create")).sendKeys("email@email.com");
        driver.findElement(By.id("SubmitCreate"));

//        Get window ID handles
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();

//        Switch & work in main window/tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.id("search_query_top")).sendKeys("shirt");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title: " + driver.getTitle());

    }


}
