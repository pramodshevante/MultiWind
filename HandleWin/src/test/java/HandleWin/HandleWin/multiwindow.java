package HandleWin.HandleWin;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class multiwindow {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // ❗ FIX: remove duplicate type (use global driver)
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Optional wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testMultiWindow() {

        driver.get("https://opensource-demo.orangehrmlive.com/");
        

        driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/orangehrm/mycompany/']//*[name()='svg']")).click();
        driver.findElement(By.xpath("//a[@href='https://www.facebook.com/OrangeHRM/']//*[name()='svg']")).click();
        driver.findElement(By.xpath("//a[@href='https://twitter.com/orangehrm?lang=en']//*[name()='svg']")).click();

        Set<String> handles = driver.getWindowHandles();
        List<String> hList = new ArrayList<>(handles);

        if (switchToRightWindow("facebook", hList)) {
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
       // driver.quit();
    }
}