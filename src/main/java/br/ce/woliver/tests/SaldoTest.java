package br.ce.woliver.tests;

import org.junit.Assert;
import org.junit.Test;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.pages.AddContasPage;
import br.ce.woliver.pages.HomePage;
import br.ce.woliver.pages.MenuPage;
import br.ce.woliver.pages.MovimentacaoPage;

public class SaldoTest extends BaseTest {
	
	private MovimentacaoPage movPage = new MovimentacaoPage();
	private HomePage homePage = new HomePage();
	private AddContasPage pageConta = new AddContasPage();
	private MenuPage menuPage = new MenuPage();
	
	@Test
	public void validar_saldo_conta() {
		pageConta.inserirConta("Conta T Saldo");
		movPage.inserirVariosMovimentos("REC", "desc al", "pessoa", 1000, "Conta T Saldo", "status_pago");
		menuPage.acessarHomePage();
		Assert.assertEquals(movPage.pegarTextoXpath("//tr[td='Conta T Saldo']//td[2]"), "3003.00");
		homePage.apagarContaEMovimento("Conta T Saldo");
	}
}
