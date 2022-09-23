package br.ce.wcaquino.tasks.function;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.junit.Assert;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

public class TasksTests {
	
	public WebDriver accessarAplicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PandoraFighter\\Documents\\devops\\cursosJenkins\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.5.135:4444/wd/hub"), cap);
		//driver.get("http://www.google.com");
		driver.navigate().to("http://192.168.5.135:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	@Test
	public void testAmbiente() throws MalformedURLException {
		
		WebDriver driver = accessarAplicacao();
		
		try {
		
			// Botao Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher tarefa (Task)
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium 4");
			
			// Preencher data (DueDate)
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
			
			// Clicar no botão de salvar (Save)
			driver.findElement(By.id("saveButton")).click();
			
			// Validar resposta de Sucesso
			String message = driver.findElement(By.id("message")).getText();
			//System.out.println(message);
			Assert.assertEquals("Success!", message);
		} finally {		
			// Fechar o Browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		
		WebDriver driver = accessarAplicacao();
		
		try {
		
			// Botao Add Todo
			driver.findElement(By.id("addTodo")).click();
					
			// Preencher data (DueDate)
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
			
			// Clicar no botão de salvar (Save)
			driver.findElement(By.id("saveButton")).click();
			
			// Validar resposta de Sucesso
			String message = driver.findElement(By.id("message")).getText();
			//System.out.println(message);
			Assert.assertEquals("Fill the task description", message);
		} finally {		
			// Fechar o Browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		
		WebDriver driver = accessarAplicacao();
		
		try {
		
			// Botao Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher tarefa (Task)
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium 4");
			
			// Clicar no botão de salvar (Save)
			driver.findElement(By.id("saveButton")).click();
			
			// Validar resposta de Sucesso
			String message = driver.findElement(By.id("message")).getText();
			//System.out.println(message);
			Assert.assertEquals("Fill the due date", message);
		} finally {		
			// Fechar o Browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPAssada() throws MalformedURLException {
		
		WebDriver driver = accessarAplicacao();
		
		try {
		
			// Botao Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			// Preencher tarefa (Task)
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium 4");
			
			// Preencher data (DueDate)
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			// Clicar no botão de salvar (Save)
			driver.findElement(By.id("saveButton")).click();
			
			// Validar resposta de Sucesso
			String message = driver.findElement(By.id("message")).getText();
			//System.out.println(message);
			Assert.assertEquals("Due date must not be in past", message);
		} finally {		
			// Fechar o Browser
			driver.quit();
		}
	}
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = accessarAplicacao();
		
		try {
			
			// Inserir uma tarefa
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium Functional");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", message);
			
			// Remover uma tarefa
			//a[@class='btn btn-outline-danger btn-sm']
			((WebElement) driver.findElements(By.xpath("//a[@class='btn btn-outline-danger btn-sm']"))).click();
			message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", message);			
		} finally {		
			// Fechar o Browser
			driver.quit();
		}
	}
}
