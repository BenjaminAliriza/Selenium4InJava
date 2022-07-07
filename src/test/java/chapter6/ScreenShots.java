package chapter6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ScreenShots {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://applitools.com/");
    }
    
    @Test
    public void takeWebElementScreenShot() throws IOException {
        WebElement nextGenPlatform = driver.findElement(By.cssSelector("#post-8 h1"));
        File source = nextGenPlatform.getScreenshotAs(OutputType.FILE);
        File destination = new File("Next Generation Platform.png");
        FileHandler.copy(source, destination);
    }

}
