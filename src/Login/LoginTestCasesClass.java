package Login;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

/* my class extends from another class which is ParameterClass to avoid having a lot
of line in single file */

public class LoginTestCasesClass extends ParametersClass {

	// initiate my driver - chrome
	WebDriver driver = new ChromeDriver();

	// before doing the test - accessing the website
	@BeforeTest
	public void Setup() {
		driver.get(URL);
	}

	// the test itself
	@Test
	public void Login() throws InterruptedException {

		// incase the test will fail i am asking to wait one minute maximum

		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(1));

		// define all the elements that going to use in the login page
		WebElement EmailField = driver.findElement(By.id("mat-input-0"));
		WebElement PasswordField = driver.findElement(By.id("mat-input-1"));
		WebElement LoginButton = driver.findElement(By.className("block"));

		// interact with webelement
		EmailField.sendKeys(Email);
		PasswordField.sendKeys(Password);
		LoginButton.click();

		// log in process take few second i added 5 seconds to handle the
		// synchronization
		Thread.sleep(5000);

		// get the actual msg from the website which is welcome to dashboard
		String ActualMsg = driver.findElement(By.cssSelector("div[class='content'] dashboard p")).getText();
		// this is the assertion that will let testng directly tell me if everything is
		// fine or no
		assertEquals(ActualMsg, ExpectedMsg);

		// prinintg the title as per the requirment
		System.out.println(driver.getTitle());

		// perform the log out process and the waiting time of 3 second just to let you
		// see the test itself
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("img[src='assets/images/user.svg']")).click();

		driver.findElement(By.xpath("//li[normalize-space()='Logout']")).click();

		boolean LogoutMsgIsDisplayed = driver.findElement(By.xpath("//div[@class='message']")).isDisplayed();

		// make assertion that the logout process is success
		assertEquals(LogoutMsgIsDisplayed, true);
	}

	@AfterTest

	public void PostTest() {
		driver.close();
	}

}
