package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.Assert;

public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PandoraFighter\\Documents\\devops\\cursosJenkins\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.5.135:4444/wd/hub"), cap);
		try {
			//driver.get("http://www.google.com");
			driver.navigate().to("http://192.168.5.135:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			System.out.println(version);
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}
	}

}
