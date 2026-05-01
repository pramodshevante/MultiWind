package HandleWin.HandleWin;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class screenshot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

				WebDriverManager.chromedriver().setup();
				
				WebDriver driver = new ChromeDriver();
				
				driver.get("https://orangehrm.com/");
				
				File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
				File dest = new File("C:\\Users\\HP\\eclipse-workspace\\HandleWin\\Screensot\\.one.png");
				
				try {
					FileUtils.copyFile(src, dest);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.quit();
	}

}
