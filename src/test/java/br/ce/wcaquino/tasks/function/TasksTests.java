package br.ce.wcaquino.tasks.function;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

public class TasksTests {
	
	public WebDriver accessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\PandoraFighter\\Documents\\devops\\cursosJenkins\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//driver.get("http://www.google.com");
		driver.navigate().to("http://192.168.5.135:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	@Test
	public void testAmbiente() {
		
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
	public void naoDeveSalvarTarefaSemDescricao() {
		
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
	public void naoDeveSalvarTarefaSemData() {
		
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
	public void naoDeveSalvarTarefaComDataPAssada() {
		
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
}
