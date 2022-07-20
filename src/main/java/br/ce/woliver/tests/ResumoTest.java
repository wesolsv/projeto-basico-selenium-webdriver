package br.ce.woliver.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.woliver.core.BaseTest;
import br.ce.woliver.core.DriverFactory;
import br.ce.woliver.pages.AddContasPage;
import br.ce.woliver.pages.MenuPage;
import br.ce.woliver.pages.MovimentacaoPage;
import br.ce.woliver.pages.ResumoPage;

public class ResumoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	private AddContasPage pageConta = new AddContasPage();
	private MovimentacaoPage movPage = new MovimentacaoPage();

	@Test
	public void remover_conta_com_movimentacao() {
		pageConta.inserirConta("Conta T Rem Con Mov");
		movPage.criarMovimento("DESP", resumoPage.obterData(), resumoPage.obterData(), "aleatoria", "Pessoa", "1000",
				"Conta T Rem Con Mov", "status_pago");
		pageConta.removerConta("Conta T Rem Con Mov");
		Assert.assertEquals(movPage.mensagemSucesso(), "Conta em uso na movimentações");
		menuPage.acessarPaginaResumo();
		resumoPage.excluirMovimentacao("aleatoria", "Conta T Rem Con Mov");
		pageConta.removerConta("Conta T Rem Con Mov");
	}

	@Test
	public void remover_movimentacao() {
		pageConta.inserirConta("Conta T Remov Movimentacao");
		movPage.criarMovimento("DESP", resumoPage.obterData(), resumoPage.obterData(), "alexa", "Pessoa", "1000",
				"Conta T Remov Movimentacao", "status_pago");
		menuPage.acessarPaginaResumo();
		resumoPage.excluirMovimentacao("alexa", "Conta T Remov Movimentacao");
		Assert.assertEquals(movPage.mensagemSucesso(), "Movimentação removida com sucesso!");
		pageConta.removerConta("Conta T Remov Movimentacao");
	}

	@Test
	public void validar_tela_resumo() {
		menuPage.acessarPaginaResumo();
		Assert.assertEquals(movPage.returnUrl(), "https://seubarriga.wcaquino.me/extrato");
		Assert.assertEquals("Seu Barriga - Extrato", DriverFactory.getDriver().getTitle());
	}

	@Test
	public void validar_tela_vazia() {
		menuPage.acessarPaginaResumo();
		resumoPage.selecionarAno("2010");
		List<WebElement> elementos	= DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		Assert.assertEquals(elementos.size(), 0);
	}
}
