package br.ce.woliver.tests;

import org.junit.Assert;
import org.junit.Test;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.HomePage;
import br.ce.woliver.pages.MenuPage;
import br.ce.woliver.pages.MovimentacaoPage;

public class SaldoTest extends BaseTest {
	
	private MovimentacaoPage movPage = new MovimentacaoPage();
	private MenuPage menuPage = new MenuPage();
	private HomePage homePage = new HomePage();
	
	@Test
	public void validar_saldo_conta() {
		homePage.inserirConta();
		homePage.inserirMovimento();
		menuPage.acessarHomePage();
		Assert.assertEquals(movPage.pegarTextoXpath("//tr[td='Conta Movimentação']//td[2]"), "3003.00");
		homePage.apagarContaEMovimento();
	}
}
