package multiwindow;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MultiWin {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        // Automatically downloads and sets ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(); 

        

        driver.manage().window().maximize();

        // Implicit wait (Selenium 4 style)
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testMultiWindow() {

        driver.get("https://opensource-demo.orangehrmlive.com/");

        driver.findElement(By.xpath("//*[@alt='LinkedIn OrangeHRM group']")).click();
        driver.findElement(By.xpath("//*[@alt='OrangeHRM on Facebook']")).click();
        driver.findElement(By.xpath("//*[@alt='OrangeHRM on twitter']")).click();

        Set<String> handles = driver.getWindowHandles();
        List<String> hList = new ArrayList<>(handles);

        if (switchToRightWindow("Twitter", hList)) {
            System.out.println(driver.getCurrentUrl() + "  " + driver.getTitle());
        }
    }

    public boolean switchToRightWindow(String windowTitle, List<String> hList) {
        for (String e : hList) {
            String title = driver.switchTo().window(e).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Found window: " + windowTitle);
                return true;
            }
        }
        return false;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}