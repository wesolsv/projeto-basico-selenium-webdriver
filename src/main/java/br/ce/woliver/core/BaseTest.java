package br.ce.woliver.core;

import static br.ce.woliver.core.DriverFactory.getDriver;
import static br.ce.woliver.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.ce.woliver.pages.LoginPage;

public class BaseTest {
	
	private LoginPage page = new LoginPage();
	
	@Rule
	public TestName testName = new TestName();
	
	@Before
	public void iniciar() {
		page.acessar_tela_inicial();
		page.logar("wesolsv@gmail.com", "rcd7pj5j");
	}
	
	@After
	public void finaliza() throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target/prints/"+testName.getMethodName() + ".jpg"));
		
		
		if(Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
		
	}
}
