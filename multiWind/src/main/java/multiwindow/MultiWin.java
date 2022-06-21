package multiwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultiWin {
	static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\Desktop\\chromedriver.exe");
		  driver = new ChromeDriver();
			driver.get("https://opensource-demo.orangehrmlive.com/");
			
			driver.findElement(By.xpath("//*[@alt='LinkedIn OrangeHRM group']")).click();
			driver.findElement(By.xpath("//*[@alt='OrangeHRM on Facebook']")).click();
			driver.findElement(By.xpath("//*[@alt='OrangeHRM on twitter']")).click();

	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		Set<String> handles = driver.getWindowHandles();
		List<String> hList = new ArrayList<String>(handles);
		if(switchToRigthWindow("Twitter", hList)) {
			System.out.println(driver.getCurrentUrl() +  "  "  + driver.getTitle());
			
	}}
	
	public static boolean switchToRigthWindow(String windowTitle, List<String> hList) {
		for(String e : hList) {
			String title = driver.switchTo().window(e).getTitle();
			if(title.contains(windowTitle)) {
				System.out.println("found win");
				return true;
			}
			
		}
		return false;
		
		
		}
		
		
		
		
		


	
	}

	

	


