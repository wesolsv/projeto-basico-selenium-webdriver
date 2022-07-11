package br.ce.woliver.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.woliver.core.DriverFactory;
import br.ce.woliver.pages.LoginPage;
import br.ce.woliver.tests.ContaTest;
import br.ce.woliver.tests.MovimentacaoTest;
import br.ce.woliver.tests.ResumoTest;
import br.ce.woliver.tests.SaldoTest;

@RunWith(Suite.class)

@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	ResumoTest.class,
	SaldoTest.class
})

public class SuiteGeral {
	private static LoginPage page = new LoginPage();
	
	@BeforeClass
	public static void iniciar() {
		page.acessar_tela_inicial();
		page.logar("wesolsv@gmail.com", "rcd7pj5j");
	}
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();	
	}
}
